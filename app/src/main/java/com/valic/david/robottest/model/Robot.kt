package com.valic.david.robottest.model

import android.widget.TextView

class Robot(private val maxX: Int = 5,
            private val maxY: Int = 5,
            private val reportView: TextView? = null) {

    var x = 0
    var y = 0
    var direction = 0

    var printReport = ""

    val facing = arrayListOf("NORTH", "EAST", "SOUTH", "WEST")

    fun place(x: Int, y: Int, direction: Int) {
        this.x = x
        this.y = y
        this.direction = direction
    }

    fun move() {
        if (direction == 0 && y < maxY) {
            y++
        } else if (direction == 1 && x < maxX) {
            x++
        } else if (direction == 2 && y > 0) {
            y--
        } else if (direction == 3 && x > 0) {
            x--
        }
    }

    fun left() {
        if (direction == 0) {
            direction = 3
        } else {
            direction--
        }
    }

    fun right() {
        if (direction == 3) {
            direction = 0
        } else {
            direction++
        }
    }

    fun report() {
        printReport = "${this.x},${this.y},${facing[this.direction]}\n" + printReport
        reportView?.text = printReport
    }

    fun readActions(actions: List<Action>){
        actions.forEach { action ->
            when(action) {
                Action.MOVE -> this.move()
                Action.LEFT -> this.left()
                Action.RIGHT -> this.right()
                Action.REPORT -> this.report()
            }
        }
    }

    enum class Action {
        MOVE, LEFT, RIGHT, REPORT
    }
}