package com.bxfox.baseui.dialog

/**
 *
 * @author lig
 * @date 2022-03-21
 */
interface ILoading {

    /**
     * 隐藏加载
     * @param delay 单位：ms 延迟一段时间后消失，避免有些耗时任务完成的很快，弹窗就会闪一下
     */
    fun dismissLoading(delay: Long = 500L)

    /**
     * 显示加载
     * @param text loading显示的文字（如果子类支持的话）
     * @param isDismissOnBackPressed if true 返回时候可以取消
     * @param isDismissOnTouchOutside if true 点击弹窗外部可以取消
     */
    fun showLoading(
        text: String? = null,
        isDismissOnBackPressed: Boolean = false,
        isDismissOnTouchOutside: Boolean = false
    )


    fun isLoading(): Boolean
}

