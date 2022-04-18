package com.bxfox.baseui.dialog

import android.content.Context
import com.lxj.xpopup.XPopup

/**
 *
 * @author lig
 * @date 2022-03-25
 */
class XPopupLoadingImp(context: Context) : ILoading {
    private val popupView = XPopup.Builder(context)
        .asLoading()

    override fun dismissLoading(delay: Long) {
        popupView.delayDismiss(delay)
    }

    override fun showLoading(
        text: String?,
        isDismissOnBackPressed: Boolean,
        isDismissOnTouchOutside: Boolean
    ) {
        popupView.popupInfo.isDismissOnBackPressed = isDismissOnBackPressed
        popupView.popupInfo.isDismissOnTouchOutside = isDismissOnTouchOutside
        popupView.show()
    }

    override fun isLoading(): Boolean {
        return popupView.isShown
    }

}