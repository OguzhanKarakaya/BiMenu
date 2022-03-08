package com.example.bimenu2.helpers

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.example.bimenu2.R

class ToolbarComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
): RelativeLayout(context, attrs) {

    internal var toolbarTitle: String? = ""

    init {
        inflate(context, R.layout.custom_toolbar, this)

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ToolbarComponent)

        toolbarTitle = typedArray.getString(R.styleable.ToolbarComponent_toolbarTitle)

        typedArray.recycle()
    }

    fun changeToolbarTitle(toolbarTitle: String) {
       // txtToolbarTitle.text = toolbarTitle
    }
}