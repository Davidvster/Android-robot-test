package com.valic.david.robottest.model

import java.io.Serializable

class Command : Serializable {
    var name: String = ""
    val actions = mutableListOf<Robot.Action>()
}