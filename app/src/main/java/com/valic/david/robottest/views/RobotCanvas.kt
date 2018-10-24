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
import com.valic.david.robottest.model.Robot

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
            val offsetX = (((xRadius * currentX + xRadius) - (xRadius * currentX)) - radius)/2
            val offsetY = (((yRadius * (gridY - currentY) + yRadius) - (yRadius * (gridY - currentY))) - radius)/2
            canvas.drawArc(xRadius * currentX + offsetX - radius/8, yRadius * (gridY - currentY) + offsetY - radius/8, xRadius * currentX + xRadius - offsetX + radius/8, yRadius * (gridY - currentY) + yRadius - offsetY + radius/8, currentDirection * 90.toFloat(), -180f, true, arcPaint)
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

    fun drawRobot(oldRobot: Robot, newRobot: Robot) {
        val valueAnimatorX = ValueAnimator.ofFloat(oldRobot.x.toFloat(), newRobot.x.toFloat())
        valueAnimatorX.interpolator = AccelerateDecelerateInterpolator()
        valueAnimatorX.duration = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()
        valueAnimatorX.addUpdateListener { animation ->
            currentX = animation.animatedValue as Float
            invalidate()
        }
        valueAnimatorX.start()

        val valueAnimatorY = ValueAnimator.ofFloat(oldRobot.y.toFloat(), newRobot.y.toFloat())
        valueAnimatorY.interpolator = AccelerateDecelerateInterpolator()
        valueAnimatorY.duration = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()
        valueAnimatorY.addUpdateListener { animation ->
            currentY = animation.animatedValue as Float
            invalidate()
        }
        valueAnimatorY.start()

        val valueAnimatorD = ValueAnimator.ofFloat(oldRobot.direction.ordinal.toFloat(), newRobot.direction.ordinal.toFloat())
        valueAnimatorD.interpolator = AccelerateDecelerateInterpolator()
        valueAnimatorD.duration = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()
        valueAnimatorD.addUpdateListener { animation ->
            currentDirection = animation.animatedValue as Float
            invalidate()
        }
        valueAnimatorD.start()
    }

//    fun move(newX: Int, newY: Int) {
//        val valueAnimatorX = ValueAnimator.ofFloat(currentX, newX.toFloat())
//        valueAnimatorX.interpolator = AccelerateDecelerateInterpolator()
//        valueAnimatorX.duration = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()
//        valueAnimatorX.addUpdateListener { animation ->
//            currentX = animation.animatedValue as Float
//            invalidate()
//        }
//        valueAnimatorX.start()
//
//        val valueAnimatorY = ValueAnimator.ofFloat(currentY, newY.toFloat())
//        valueAnimatorY.interpolator = AccelerateDecelerateInterpolator()
//        valueAnimatorY.duration = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()
//        valueAnimatorY.addUpdateListener { animation ->
//            currentY = animation.animatedValue as Float
//            invalidate()
//        }
//        valueAnimatorY.start()
//    }

//    fun setDirection(direction: Direction) {
//        val valueAnimator = ValueAnimator.ofFloat(currentDirection, direction.ordinal.toFloat())
//        valueAnimator.interpolator = AccelerateDecelerateInterpolator()
//        valueAnimator.duration = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()
//        valueAnimator.addUpdateListener { animation ->
//            currentDirection = animation.animatedValue as Float
//            invalidate()
//        }
//        valueAnimator.start()
//    }
}