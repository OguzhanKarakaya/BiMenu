package com.example.bimenu2

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.Window
import android.widget.ProgressBar

object LDSProgressDialog {
    @JvmStatic
    fun show(
        context: Context?,
        cancelListener: DialogInterface.OnCancelListener?
    ): Dialog {
        val dialogView = Dialog(context!!)
        dialogView.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        dialogView.setContentView(R.layout.dialog_progress)
        dialogView.window!!.setDimAmount(0.1f)
        dialogView.window!!.setBackgroundDrawable(null)
        if (cancelListener != null) {
            dialogView.setCancelable(true)
            dialogView.setCanceledOnTouchOutside(true)
            dialogView.setOnCancelListener(cancelListener)
        } else {
            dialogView.setCancelable(false)
            dialogView.setCanceledOnTouchOutside(false)
        }
        return dialogView
    }
}