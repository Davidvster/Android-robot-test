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
import com.valic.david.robottest.model.Robot

class MainActivity : AppCompatActivity() {

    private var customCommands = listOf<Command>()
    private lateinit var robot: Robot

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val gridX = intent.getIntExtra(GRID_X_SIZE, 5)
        val gridY = intent.getIntExtra(GRID_Y_SIZE, 5)
        customCommands = intent.getSerializableExtra(CUSTOM_COMMANDS) as List<Command>

        robot_canvas.setCanvas(gridX, gridY)

        robot = Robot(gridX, gridY, robot_report_out)

        val facingAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, robot.facing)
        facingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        robot_place_facing.adapter = facingAdapter

        robot_place_action.setOnClickListener {
            if (robot_place_x.text.isNotEmpty() && robot_place_y.text.isNotEmpty() && robot_place_facing.selectedItem != null && robot_place_x.text.toString().toInt() in 0..gridX && robot_place_y.text.toString().toInt() in 0..gridY) {
                robot.place(robot_place_x.text.toString().toInt(), robot_place_y.text.toString().toInt(), robot_place_facing.selectedItemPosition)
                robot_canvas.startPosition(robot.x, robot.y, robot.direction)
            }
        }

        robot_move.setOnClickListener {
            robot.move()
            robot_canvas.move(robot.x, robot.y)
        }

        robot_left.setOnClickListener {
            robot.left()
            robot_canvas.setDirection(robot.direction)
        }

        robot_right.setOnClickListener {
            robot.right()
            robot_canvas.setDirection(robot.direction)
        }

        robot_report.setOnClickListener {
            robot.report()
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
                        robot.readActions(customCommands[which].actions)
                        robot_canvas.move(robot.x, robot.y)
                        robot_canvas.setDirection(robot.direction)
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
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(GRID_X_SIZE, x)
            intent.putExtra(GRID_Y_SIZE, y)
            intent.putExtra(CUSTOM_COMMANDS, ArrayList(commands))
            context.startActivity(intent)
        }
    }
}
