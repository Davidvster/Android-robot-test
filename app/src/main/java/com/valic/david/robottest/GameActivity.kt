package com.valic.david.robottest

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.ArrayAdapter
import com.valic.david.robottest.model.Command
import com.valic.david.robottest.model.Direction

class GameActivity : AppCompatActivity() {

    private var customCommands = listOf<Command>()

    private val gameManager = GameManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val gridX = intent.getIntExtra(GRID_X_SIZE, 5)
        val gridY = intent.getIntExtra(GRID_Y_SIZE, 5)
        customCommands = intent.getSerializableExtra(CUSTOM_COMMANDS) as List<Command>

        gameManager.initBoard(gridX, gridY, robot_canvas)

        val facingAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Direction.values().map { it.name })
        facingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        robot_place_facing.adapter = facingAdapter

        robot_place_action.setOnClickListener {
            if (robot_place_x.text.isNotEmpty() && robot_place_y.text.isNotEmpty() && robot_place_facing.selectedItem != null) {
                gameManager.actionPlace(robot_place_x.text.toString().toInt(), robot_place_y.text.toString().toInt(), Direction.valueOf(robot_place_facing.selectedItem.toString()))
            }
        }

        robot_move.setOnClickListener {
            gameManager.actionMove()
        }

        robot_left.setOnClickListener {
            gameManager.actionLeft()
        }

        robot_right.setOnClickListener {
            gameManager.actionRight()
        }

        robot_report.setOnClickListener {
            robot_report_out.text = gameManager.actionReport()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                this.finish()
                return true
            }
            R.id.menu_custom_commands -> {
                if (customCommands.isNotEmpty()) {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle(resources.getString(R.string.custom_command_dialog_title))
                    builder.setItems(customCommands.map { it.name }.toTypedArray()) { _, which ->
                        customCommands[which].executeCommand(gameManager)
                    }
                    val dialog = builder.create()
                    dialog.show()
                }
            }
        }
        return false
    }

    companion object {
        const val GRID_X_SIZE = "grid.x.size.int"
        const val GRID_Y_SIZE = "grid.y.size.int"
        const val CUSTOM_COMMANDS = "custom.commands.string"

        @JvmStatic
        fun start(context: Activity, x: Int, y: Int, commands: MutableList<Command>) {
            val intent = Intent(context, GameActivity::class.java)
            intent.putExtra(GRID_X_SIZE, x)
            intent.putExtra(GRID_Y_SIZE, y)
            intent.putExtra(CUSTOM_COMMANDS, ArrayList(commands))
            context.startActivity(intent)
        }
    }
}
