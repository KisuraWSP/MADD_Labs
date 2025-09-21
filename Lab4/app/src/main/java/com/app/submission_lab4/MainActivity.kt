package com.app.submission_lab4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        val todoList: MutableList<String> = mutableListOf()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val todoText: EditText = findViewById(R.id.todoText)
        val addBtn: Button = findViewById(R.id.addBtn)
        val addTodoBtn: Button = findViewById(R.id.addTodoBtn)
        val viewTodosBtn: Button = findViewById(R.id.viewTodosBtn)

        addBtn.setOnClickListener {
            val text = todoText.text.toString()
            if (text.isNotBlank()) {
                todoList.add(text)
                todoText.text.clear()
                Toast.makeText(this, "Todo added!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please enter a todo", Toast.LENGTH_SHORT).show()
            }
        }

        addTodoBtn.setOnClickListener {
            addBtn.performClick()
        }

        viewTodosBtn.setOnClickListener {
            val intent = Intent(this, TodoActivity::class.java)
            startActivity(intent)
        }
    }
}