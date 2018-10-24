package com.valic.david.robottest.views

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.constraint.helper.Layer
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import com.valic.david.robottest.R

class DecExample : Layer {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, resStyle: Int) : super(context, attrs, resStyle)

    override fun updatePostLayout(container: ConstraintLayout?) {
        super.updatePostLayout(container)
        val referenceIds = referencedIds
        for (id in referenceIds) {
            val view = container?.getViewById(id)
            view?.setBackgroundColor(ContextCompat.getColor(context, R.color.primary_material_dark))
        }
    }
}