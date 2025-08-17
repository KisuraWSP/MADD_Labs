package com.app.lab2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BillCalculatorActivity : AppCompatActivity() {
    lateinit var enterUnits : EditText
    lateinit var calculateBtn : Button
    lateinit var textField : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bill)
        enterUnits = findViewById(R.id.editTextText)
        calculateBtn = findViewById(R.id.button3)
        textField = findViewById(R.id.textView)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main2)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        calculateBtn.setOnClickListener {
            val unitAmount = enterUnits.toString().toInt()
            val fixedCharge = 150
            val vat = 0.15
            val unitCost = 29
            val energyCharge = unitCost * unitAmount
            val bill = fixedCharge + energyCharge + 300 + vat

            textField.text = "Electricity Bill: $bill"
        }
    }
}