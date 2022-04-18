package com.bxfox.baselib.net

import android.util.Log
import androidx.annotation.Keep
import kotlinx.coroutines.*
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import okio.ByteString
import okio.ByteString.Companion.toByteString
import org.greenrobot.eventbus.EventBus
import java.net.Proxy

/**
 * WebSocket封装，仅单连接
 */
object NiceWs {
    var isConnected = false
        private set
    private var waitingConnect = false
    private var reconnecting = false
    private const val TAG = "GnWs"
    private var webSocketClient: WebSocket? = null
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .proxy(Proxy.NO_PROXY)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .retryOnConnectionFailure(true)
            .build()
    }

    fun connect() {
        if (isConnected || waitingConnect) {
            return
        }
        val request = Request.Builder()
            .url("ws://101.35.56.80:8080/test")
            .build()
        waitingConnect = true
        webSocketClient = okHttpClient.newWebSocket(request, webSocketListener)
    }

    private fun sendHeart() = coroutineScope.launch {
        while (isActive && isConnected) {
            sendMessage("1")
            delay(3000)
        }
    }

    /**
     * 如果已经连接，发送文字消息
     *
     * @return 是否发送成功
     */
    fun sendMessage(text: String): Boolean {
        if (isConnected) {
            return webSocketClient?.send(text) == true
        }
        return false
    }

    /**
     * 如果已经连接，发送二进制消息
     */
    fun sendMessage(byteArray: ByteArray) {
        webSocketClient?.send(byteArray.toByteString())
    }

    /**
     * 自动重连
     */
    private fun reconnect() = coroutineScope.launch {
        reconnecting = true
        while (!isConnected) {
            connect()
            delay(3000)
        }
        reconnecting = false
    }

    private val webSocketListener = object : WebSocketListener() {
        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
            isConnected = false
            super.onClosed(webSocket, code, reason)
            Log.d(TAG, "onClosed: ")
        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            super.onClosing(webSocket, code, reason)
            Log.d(TAG, "onClosing: ")
        }

        override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
            super.onMessage(webSocket, bytes)
            Log.d(TAG, "onMessage: $bytes")
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            isConnected = false
            waitingConnect = false
            super.onFailure(webSocket, t, response)
            Log.e(TAG, "onFailure: ${t.message}")
            if (!reconnecting) {
                reconnect()
            }
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            super.onMessage(webSocket, text)
            Log.d(TAG, "onMessage: $text thread ${Thread.currentThread().name}")
            EventBus.getDefault().post(MessageEvent(text))
        }

        override fun onOpen(webSocket: WebSocket, response: Response) {
            waitingConnect = false
            isConnected = true
            super.onOpen(webSocket, response)
            Log.d(TAG, "onOpen: $response")
            sendHeart()
        }
    }

    @Keep
    data class MessageEvent(val text: String)
}