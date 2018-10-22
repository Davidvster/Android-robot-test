package com.valic.david.robottest

import com.valic.david.robottest.model.Board
import com.valic.david.robottest.model.Direction
import com.valic.david.robottest.model.Robot
import com.valic.david.robottest.views.RobotCanvas

class GameManager {

    private var board: Board = Board()
    private var robot: Robot = Robot()

    private var canvas: RobotCanvas? = null

    fun initBoard(gridX: Int, gridY: Int, canvas: RobotCanvas) {
        board = Board(gridX, gridY)
        board.draw(canvas)
        robot = Robot()
        this.canvas = canvas
    }

    fun actionPlace(x: Int, y: Int, direction: Direction) {
        if (board.canPlaceRobot(x, y)) {
            robot.place(x, y, direction)
            robot.draw(canvas)
        }
    }

    fun actionMove() {
        if (board.canRobotMove(robot)) {
            robot.move()
            robot.draw(canvas)
        }
    }

    fun actionLeft() {
        robot.left()
        robot.draw(canvas)
    }

    fun actionRight() {
        robot.right()
        robot.draw(canvas)
    }

    fun actionReport(): String {
        return board.report(robot)
    }
}