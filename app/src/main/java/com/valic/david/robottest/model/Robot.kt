package com.valic.david.robottest.model

import com.valic.david.robottest.views.RobotCanvas

class Robot(var x: Int = 0,
            var y: Int = 0,
            var direction: Direction = Direction.NORTH) {

    var oldX = 0
    var oldY = 0
    var oldDirection = Direction.NORTH

    fun place(x: Int, y: Int, direction: Direction) {
        oldX = this.x
        oldY = this.y
        oldDirection = this.direction
        this.x = x
        this.y = y
        this.direction = direction
    }

    fun move() {
        oldX = this.x
        oldY = this.y
        oldDirection = this.direction
        when (direction) {
            Direction.NORTH -> y++
            Direction.EAST -> x++
            Direction.SOUTH -> y--
            Direction.WEST -> x--
        }
    }

    fun left() {
        oldX = this.x
        oldY = this.y
        oldDirection = this.direction
        direction = when (direction) {
            Direction.NORTH -> Direction.WEST
            Direction.EAST -> Direction.NORTH
            Direction.SOUTH -> Direction.EAST
            Direction.WEST -> Direction.SOUTH
        }
    }

    fun right() {
        oldX = this.x
        oldY = this.y
        oldDirection = this.direction
        direction = when (direction) {
            Direction.NORTH -> Direction.EAST
            Direction.EAST -> Direction.SOUTH
            Direction.SOUTH -> Direction.WEST
            Direction.WEST -> Direction.NORTH
        }
    }

    fun draw(canvas: RobotCanvas?) {
        canvas?.drawRobot(Robot(oldX, oldY, oldDirection), this)
    }
}

enum class Direction(facing: String) {
    NORTH("North"), EAST("East"), SOUTH("South"), WEST("West")
}