package com.valic.david.robottest

import com.valic.david.robottest.model.Direction
import com.valic.david.robottest.model.Robot
import org.junit.Test

import org.junit.Assert.*

class TestRobotSimulator {
    @Test
    fun exampleA() {
        val gameManager = GameManager()

        gameManager.actionPlace(0,0, Direction.NORTH)
        gameManager.actionMove()

        assertEquals("0,1,NORTH\n", gameManager.actionReport())
    }

    @Test
    fun exampleB() {
        val gameManager = GameManager()

        gameManager.actionPlace(0,0, Direction.NORTH)
        gameManager.actionLeft()

        assertEquals("0,0,WEST\n", gameManager.actionReport())
    }

    @Test
    fun exampleC() {
        val gameManager = GameManager()

        gameManager.actionPlace(1,2, Direction.EAST)
        gameManager.actionMove()
        gameManager.actionMove()
        gameManager.actionLeft()
        gameManager.actionMove()

        assertEquals("3,3,NORTH\n", gameManager.actionReport())
    }

    @Test
    fun longerTest(){
        val gameManager = GameManager()

        gameManager.actionPlace(3,3, Direction.EAST)
        gameManager.actionMove()
        gameManager.actionMove()
        gameManager.actionMove()
        gameManager.actionRight()
        gameManager.actionRight()
        gameManager.actionMove()
        gameManager.actionMove()
        gameManager.actionLeft()
        gameManager.actionMove()
        gameManager.actionRight()
        gameManager.actionMove()
        gameManager.actionMove()
        gameManager.actionMove()
        gameManager.actionMove()
        gameManager.actionLeft()
        gameManager.actionMove()
        gameManager.actionMove()
        gameManager.actionMove()
        gameManager.actionMove()
        gameManager.actionMove()
        gameManager.actionMove()
        gameManager.actionRight()
        gameManager.actionMove()
        gameManager.actionRight()
        gameManager.actionMove()
        gameManager.actionMove()
        gameManager.actionMove()
        gameManager.actionMove()
        gameManager.actionMove()
        gameManager.actionRight()
        gameManager.actionLeft()

        assertEquals("0,5,NORTH\n", gameManager.actionReport())
    }

//    @Test
//    fun exampleA() {
//        val robot = Robot()
//
//        robot.place(0,0, robot.facing.indexOf("NORTH"))
//        robot.move()
//        robot.report()
//
//        assertEquals("0,1,NORTH\n", robot.printReport)
//    }
//
//    @Test
//    fun exampleB() {
//        val robot = Robot()
//
//        robot.place(0,0, robot.facing.indexOf("NORTH"))
//        robot.left()
//        robot.report()
//
//        assertEquals("0,0,WEST\n", robot.printReport)
//    }
//
//    @Test
//    fun exampleC() {
//        val robot = Robot()
//
//        robot.place(1,2, robot.facing.indexOf("EAST"))
//        robot.move()
//        robot.move()
//        robot.left()
//        robot.move()
//        robot.report()
//
//        assertEquals("3,3,NORTH\n", robot.printReport)
//    }
//
//    @Test
//    fun longerTest(){
//        val robot = Robot()
//
//        robot.place(3,3, robot.facing.indexOf("EAST"))
//        robot.move()
//        robot.move()
//        robot.move()
//        robot.right()
//        robot.right()
//        robot.move()
//        robot.move()
//        robot.left()
//        robot.move()
//        robot.right()
//        robot.move()
//        robot.move()
//        robot.move()
//        robot.move()
//        robot.left()
//        robot.move()
//        robot.move()
//        robot.move()
//        robot.move()
//        robot.move()
//        robot.move()
//        robot.right()
//        robot.move()
//        robot.right()
//        robot.move()
//        robot.move()
//        robot.move()
//        robot.move()
//        robot.move()
//        robot.right()
//        robot.left()
//        robot.report()
//
//        assertEquals("0,5,NORTH\n", robot.printReport)
//    }
}
