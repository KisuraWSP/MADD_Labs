package com.app.submission_lab4

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TodoActivity : AppCompatActivity() {

    private lateinit var adapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)

        val recyclerView: RecyclerView = findViewById(R.id.rc)

        adapter = TodoAdapter(
            MainActivity.todoList,
            onItemClick = { pos -> editTodo(pos) },
            onItemLongClick = { pos -> deleteTodo(pos) }
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun editTodo(position: Int) {
        val todo = MainActivity.todoList[position]
        val input = android.widget.EditText(this).apply { setText(todo) }

        AlertDialog.Builder(this)
            .setTitle("Edit Todo")
            .setView(input)
            .setPositiveButton("Save") { _, _ ->
                val newText = input.text.toString()
                if (newText.isNotBlank()) {
                    MainActivity.todoList[position] = newText
                    adapter.notifyItemChanged(position)
                } else {
                    Toast.makeText(this, "Cannot be empty", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun deleteTodo(position: Int) {
        AlertDialog.Builder(this)
            .setTitle("Delete Todo")
            .setMessage("Are you sure?")
            .setPositiveButton("Yes") { _, _ ->
                MainActivity.todoList.removeAt(position)
                adapter.notifyItemRemoved(position)
            }
            .setNegativeButton("No", null)
            .show()
    }
}
