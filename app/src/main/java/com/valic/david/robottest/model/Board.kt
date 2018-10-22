package com.valic.david.robottest.model

import com.valic.david.robottest.views.RobotCanvas

class Board(private val gridX: Int = 5,
            private val gridY: Int = 5) {

    private var printReport = ""

    fun draw(canvas: RobotCanvas?) {
        canvas?.setCanvas(gridX, gridY)
    }
    fun canPlaceRobot(x: Int, y: Int): Boolean {
        if (x in 0..gridX && y in 0..gridY) {
            return true
        }
        return false
    }

    fun canRobotMove(robot: Robot): Boolean {
        if (robot.direction == Direction.NORTH && robot.y < gridY) {
            return true
        } else if (robot.direction == Direction.EAST && robot.x < gridX) {
            return true
        } else if (robot.direction == Direction.SOUTH && robot.y > 0) {
            return true
        } else if (robot.direction == Direction.WEST && robot.x > 0) {
            return true
        }
        return false
    }

    fun report(robot: Robot): String {
        printReport = "${robot.x},${robot.y},${robot.direction.name}\n$printReport"
        return printReport
    }
}