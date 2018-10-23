package com.valic.david.robottest.views

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.constraint.helper.Layer
import android.util.AttributeSet
import android.view.ViewAnimationUtils

class ExampleView : Layer {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, resStyle: Int) : super(context, attrs, resStyle)

    private var container: ConstraintLayout? = null

    override fun updatePostLayout(container: ConstraintLayout?) {
        super.updatePostLayout(container)
        if (this.container != container) {
            val rad = Math.hypot(mComputedMaxY - mComputedMinY.toDouble(), mComputedMaxX - mComputedMinX.toDouble()).toInt()
            val anim = ViewAnimationUtils.createCircularReveal(this, mComputedCenterX.toInt() - left, mComputedCenterY.toInt() - top, 0f, rad.toFloat())
            anim.duration = 2000
            anim.start()
        }
        this.container = container
    }
}