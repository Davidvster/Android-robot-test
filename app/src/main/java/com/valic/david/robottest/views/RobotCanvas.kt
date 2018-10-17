package com.valic.david.robottest.views

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator

class RobotCanvas : View {

    private var viewWidth:Int = 0
    private var viewHeight:Int = 0

    private var currentX: Float = 0f
    private var currentY: Float = 0f

    private var xRadius = 0
    private var yRadius = 0

    private var currentDirection: Float = 0f

    private var gridX = 5
    private var gridY = 5

    private var radius = 0f

    private var linePaint = Paint()
    private var circlePaint = Paint()
    private var arcPaint = Paint()

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        linePaint.style = Paint.Style.FILL
        linePaint.isAntiAlias = true
        linePaint.color = Color.GRAY

        circlePaint.style = Paint.Style.FILL
        circlePaint.isAntiAlias = true
        circlePaint.color = Color.BLUE

        arcPaint.style = Paint.Style.FILL
        arcPaint.isAntiAlias = true
        arcPaint.color = Color.RED
    }

    constructor(context: Context, attrs: AttributeSet?, resStyle: Int) : super(context, attrs, resStyle)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(measuredWidth, measuredWidth)

        viewWidth = this.measuredWidth
        viewHeight = this.measuredHeight

        radius = Math.min(viewWidth/(gridX+1), viewHeight/(gridY+1))/2.toFloat()

        xRadius = viewWidth/(gridX+1)
        yRadius = viewHeight/(gridY+1)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        for (i in 0 until gridX+2) {
            canvas.drawLine((viewWidth/(gridX+1) * i).toFloat(),0f, (viewWidth/(gridX+1) * i).toFloat(), viewWidth.toFloat(), linePaint)
        }
        for (i in 0 until gridY+2) {
            canvas.drawLine(0f,(viewHeight/(gridY+1) * i).toFloat(), viewHeight.toFloat(), (viewHeight/(gridY+1) * i).toFloat(), linePaint)
        }

        // (0,0) is at south west corner
        canvas.drawCircle(xRadius * currentX + xRadius/2, yRadius * (gridY - currentY) + yRadius/2 , radius - radius/5, circlePaint)
        if (Build.VERSION.SDK_INT >= 21) {
            canvas.drawArc(xRadius * currentX + radius/8, yRadius * (gridY - currentY) + radius/8, xRadius * currentX + xRadius - radius/8, yRadius * (gridY - currentY) + yRadius - radius/8, (currentDirection+2f) * 90.toFloat(), 180f, true, arcPaint)
        }
    }

    fun setCanvas(x: Int, y: Int) {
        this.gridX = x
        this.gridY = y
        radius = Math.min(viewWidth/(x+1), viewHeight/(y+1))/2.toFloat()
        xRadius = viewWidth/(x+1)
        yRadius = viewHeight/(y+1)
        invalidate()
    }

    fun startPosition(startX: Int, startY: Int, startDirection: Int) {
        currentX =  startX.toFloat()
        currentY =  startY.toFloat()
        currentDirection = startDirection.toFloat()
        invalidate()
    }

    fun move(newX: Int, newY: Int) {
        val valueAnimatorX = ValueAnimator.ofFloat(currentX, newX.toFloat())
        valueAnimatorX.interpolator = AccelerateDecelerateInterpolator()
        valueAnimatorX.duration = 150
        valueAnimatorX.addUpdateListener { animation ->
            currentX = animation.animatedValue as Float
            invalidate()
        }
        valueAnimatorX.start()

        val valueAnimatorY = ValueAnimator.ofFloat(currentY, newY.toFloat())
        valueAnimatorY.interpolator = AccelerateDecelerateInterpolator()
        valueAnimatorY.duration = 150
        valueAnimatorY.addUpdateListener { animation ->
            currentY = animation.animatedValue as Float
            invalidate()
        }
        valueAnimatorY.start()
    }

    fun setDirection(direction: Int) {
        val valueAnimator = ValueAnimator.ofFloat(currentDirection, direction.toFloat())
        valueAnimator.interpolator = AccelerateDecelerateInterpolator()
        valueAnimator.duration = 150
        valueAnimator.addUpdateListener { animation ->
            currentDirection = animation.animatedValue as Float
            invalidate()
        }
        valueAnimator.start()
    }
}