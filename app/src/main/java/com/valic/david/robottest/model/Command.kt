package com.valic.david.robottest.model

import com.valic.david.robottest.GameManager
import java.io.Serializable

class Command : Serializable {
    var name: String = ""
    private val actions = mutableListOf<Action>()

    fun executeCommand(gameManager: GameManager): String {
        var report = ""
        actions.forEach { action ->
            when(action) {
                Action.MOVE -> gameManager.actionMove()
                Action.LEFT -> gameManager.actionLeft()
                Action.RIGHT -> gameManager.actionRight()
                Action.REPORT -> report = gameManager.actionReport()
            }
        }
        return report
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