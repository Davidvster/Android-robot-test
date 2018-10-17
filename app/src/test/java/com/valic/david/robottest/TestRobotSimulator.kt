package com.valic.david.robottest

import com.valic.david.robottest.model.Robot
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
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
    fun longTest(){
        val robot = Robot()

        robot.place(3,3, robot.facing.indexOf("EAST"))
        robot.readActions(listOf("MOVE", "MOVE", "MOVE", "RIGHT"))
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
        robot.readActions(listOf("MOVE", "MOVE", "MOVE", "RIGHT", "MOVE", "RIGHT", "MOVE"))
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
