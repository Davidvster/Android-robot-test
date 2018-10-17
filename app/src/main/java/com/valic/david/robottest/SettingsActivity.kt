package com.valic.david.robottest

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import com.valic.david.robottest.model.Command
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    private val customCommands = mutableListOf<Command>()
    private var currentCommand = Command()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        start_main.setOnClickListener {
            if (grid_x.text.toString().toInt() > 0 && grid_y.text.toString().toInt() > 0) {
                MainActivity.start(this, grid_x.text.toString().toInt(), grid_y.text.toString().toInt(), customCommands)
            }
        }

        robot_move.setOnClickListener {
            addAction("MOVE")
        }

        robot_left.setOnClickListener {
            addAction("LEFT")
        }

        robot_right.setOnClickListener {
            addAction("RIGHT")
        }

        robot_report.setOnClickListener {
            addAction("REPORT")
        }

        command_delete.setOnClickListener {
            if (currentCommand.actions.size > 0) {
                currentCommand.actions.removeAt(currentCommand.actions.lastIndex)
            }
            command_text.text = currentCommand.actions.toString()
        }

        command_add.setOnClickListener {
            if (command_name.text.isNullOrEmpty().not()) {
                currentCommand.name = command_name.text.toString()
                customCommands.add(currentCommand)
                currentCommand = Command()
                command_text.text = ""
                command_name.text = null
            }
        }
    }

    private fun addAction(action: String) {
        currentCommand.actions.add(action)
        command_text.text = currentCommand.actions.toString()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_custom_commands -> {
                if (customCommands.isNotEmpty()) {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Click a custom command to edit it")
                    builder.setItems(customCommands.map { it.name }.toTypedArray(), DialogInterface.OnClickListener { dialog, which ->
                        currentCommand = customCommands[which]
                        customCommands.remove(currentCommand)
                        command_text.text = currentCommand.actions.toString()
                        command_name.setText(currentCommand.name)
                    })
                    val dialog = builder.create()
                    dialog.show()
                }
            }
        }
        return false
    }
}
