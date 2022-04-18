package com.bxfox.baseui.activity

import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.alibaba.android.arouter.launcher.ARouter
import com.bxfox.baseui.dialog.ILoading
import com.bxfox.baseui.dialog.XPopupLoadingImp

import com.gyf.immersionbar.ImmersionBar
import com.gyf.immersionbar.OnKeyboardListener
import org.greenrobot.eventbus.EventBus

/**
 * Activity基类，如果使用DataBinding
 * @see BaseDataBindingActivity
 * 如果要实例化ViewModel，参照：val model by viewModels()
 *
 * @author lig
 * @date 2022-03-21
 */
@Suppress("DEPRECATION")
abstract class BaseActivity : AppCompatActivity(), ILoading, OnKeyboardListener {
    private lateinit var iLoading: ILoading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        ARouter.getInstance().inject(this)
        iLoading = getLoadingImp()
        initView(savedInstanceState)
        if (enableEventBus() && !EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
        loadData(savedInstanceState)
    }

    /**
     * 给DataBindingActivity预留，模块外部无法访问
     */
    internal open fun initBinding() {}

    protected abstract fun initView(savedInstanceState: Bundle?)

    protected abstract fun loadData(savedInstanceState: Bundle?)

    override fun dismissLoading(delay: Long) {
        iLoading.dismissLoading(delay)
    }

    /**
     * @see ILoading
     */
    override fun showLoading(
        text: String?,
        isDismissOnBackPressed: Boolean,
        isDismissOnTouchOutside: Boolean
    ) {
        iLoading.showLoading(text, isDismissOnBackPressed, isDismissOnTouchOutside)
    }

    /**
     * 如果需要改变loading的实现请重写此函数
     */
    open fun getLoadingImp(): ILoading {
        return XPopupLoadingImp(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        if (newConfig.fontScale != 1f) {
            //非默认值
            this.resources
        }
        super.onConfigurationChanged(newConfig)
    }


    override fun getResources(): Resources? {
        val res = super.getResources()
        //非默认值
        if (res != null) {
            val config = res.configuration
            if (config != null && config.fontScale != 1.0f) {
                config.fontScale = 1.0f
                res.updateConfiguration(config, res.displayMetrics)
            }
        }
        return res
    }

    /**
     * 启用EventBus，默认不启用
     */
    open fun enableEventBus() = false

    /**
     * 沉浸式配置状态栏或导航栏
     * @param displayHomeAsUpEnable 显示Toolbar的返回按钮
     * @param titleBar 解决状态栏和布局重叠问题，任选其一
     * @param keyboardEnable 解决软键盘与底部输入框冲突问题，默认为false
     * @param statusBarDarkFont 状态栏字体是深色，不写默认为亮色
     * @param navigationBarDarkIcon 导航栏图标是深色，不写默认为亮色
     */
    fun configSystemUI(
        titleBar: View? = null,
        keyboardEnable: Boolean = false,
        statusBarDarkFont: Boolean = true,
        navigationBarDarkIcon: Boolean = true,
        displayHomeAsUpEnable: Boolean = true,
    ) {
        setActionBar(titleBar, displayHomeAsUpEnable)
        ImmersionBar.with(this)
            .keyboardEnable(keyboardEnable)
            .titleBar(titleBar)
            .setOnKeyboardListener(this)
            .statusBarDarkFont(statusBarDarkFont)
            .navigationBarDarkIcon(navigationBarDarkIcon)
            .init()
    }

    /**
     * @hide
     * 采用fitsSystemWindows方式配置状态栏或导航栏
     * @param displayHomeAsUpEnable 显示Toolbar的返回按钮
     * @param titleBar 解决状态栏和布局重叠问题，任选其一
     * @param statusBarColor 状态栏颜色，不写默认透明色
     * @param navigationBarColor 导航栏颜色，不写默认黑色
     * @param statusBarDarkFont 状态栏字体是深色，不写默认为亮色
     * @param navigationBarDarkIcon 导航栏图标是深色，不写默认为亮色
     * @param fitsSystemWindows 解决状态栏和布局重叠问题，任选其一，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
     */
    /*fun configSystemUI(
        titleBar: View? = null,
        fitsSystemWindows: Boolean = true,
        statusBarDarkFont: Boolean = true,
        navigationBarDarkIcon: Boolean = true,
        displayHomeAsUpEnable: Boolean = true,
        @ColorInt statusBarColor: Int = Color.WHITE,
        @ColorInt navigationBarColor: Int = Color.WHITE,
    ) {
        setActionBar(titleBar, displayHomeAsUpEnable)
        ImmersionBar.with(this)
            .fitsSystemWindows(fitsSystemWindows)
            .titleBar(titleBar)
            .navigationBarColorInt(navigationBarColor)
            .statusBarColorInt(statusBarColor)
            .statusBarDarkFont(statusBarDarkFont)
            .navigationBarDarkIcon(navigationBarDarkIcon)
            .init()
    }*/

    private fun setActionBar(titleBar: View?, displayHomeAsUpEnable: Boolean = true) {
        if (titleBar != null && titleBar is Toolbar) {
            setSupportActionBar(titleBar)
            supportActionBar?.let {
                it.setDisplayHomeAsUpEnabled(displayHomeAsUpEnable)
                it.setDisplayShowTitleEnabled(false)
            }
        }
    }

    /**
     * 软键盘弹出或消失需要在configSystemUI设置keyboardEnable
     */
    override fun onKeyboardChange(isPopup: Boolean, keyboardHeight: Int) {
    }

    override fun isLoading(): Boolean {
        return iLoading.isLoading()
    }

    override fun onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
        dismissLoading()
        super.onDestroy()
    }

    companion object {
        private const val TAG = "BaseActivity"
    }
}