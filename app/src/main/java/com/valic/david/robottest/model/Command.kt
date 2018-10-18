package com.valic.david.robottest.model

import java.io.Serializable

class Command : Serializable {
    var name: String = ""
    private val actions = mutableListOf<Action>()

    fun executeCommand(robot: Robot) {
        actions.forEach { action ->
            when(action) {
                Action.MOVE -> robot.move()
                Action.LEFT -> robot.left()
                Action.RIGHT -> robot.right()
                Action.REPORT -> robot.report()
            }
        }
    }

    fun addAction(action: Action) {
        actions.add(action)
    }

    fun removeLastAction() {
        if (actions.size > 0) {
            actions.removeAt(actions.lastIndex)
        }
    }

    fun allActions(): String {
        return actions.toString()
    }

    enum class Action {
        MOVE, LEFT, RIGHT, REPORT
    }
}