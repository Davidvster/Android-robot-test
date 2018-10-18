package com.valic.david.robottest

import com.valic.david.robottest.model.Robot
import org.junit.Test

import org.junit.Assert.*

class TestRobotSimulator {
    @Test
    fun exampleA() {
        val robot = Robot()

        robot.place(0,0, robot.facing.indexOf("NORTH"))
        robot.move()
        robot.report()

        assertEquals("0,1,NORTH\n", robot.printReport)
    }

    @Test
    fun exampleB() {
        val robot = Robot()

        robot.place(0,0, robot.facing.indexOf("NORTH"))
        robot.left()
        robot.report()

        assertEquals("0,0,WEST\n", robot.printReport)
    }

    @Test
    fun exampleC() {
        val robot = Robot()

        robot.place(1,2, robot.facing.indexOf("EAST"))
        robot.move()
        robot.move()
        robot.left()
        robot.move()
        robot.report()

        assertEquals("3,3,NORTH\n", robot.printReport)
    }

    @Test
    fun longerTest(){
        val robot = Robot()

        robot.place(3,3, robot.facing.indexOf("EAST"))
        robot.move()
        robot.move()
        robot.move()
        robot.right()
        robot.right()
        robot.move()
        robot.move()
        robot.left()
        robot.move()
        robot.right()
        robot.move()
        robot.move()
        robot.move()
        robot.move()
        robot.left()
        robot.move()
        robot.move()
        robot.move()
        robot.move()
        robot.move()
        robot.move()
        robot.right()
        robot.move()
        robot.right()
        robot.move()
        robot.move()
        robot.move()
        robot.move()
        robot.move()
        robot.right()
        robot.left()
        robot.report()

        assertEquals("0,5,NORTH\n", robot.printReport)
    }
}
