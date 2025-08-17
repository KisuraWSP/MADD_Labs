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
            val unitText = enterUnits.text.toString()

            if (unitText.isNotEmpty()) {
                try {
                    val unitAmount = unitText.toInt()
                    val fixedCharge = 150.0
                    val unitCost = 29.0
                    val serviceCharge = 300.0
                    val vatRate = 0.15

                    val energyCharge = unitCost * unitAmount
                    val subtotal = fixedCharge + energyCharge + serviceCharge
                    val vatAmount = subtotal * vatRate
                    val totalBill = subtotal + vatAmount

                    textField.text = """
                Fixed Charge: Rs. %.2f
                Energy Charge: Rs. %.2f
                Service Charge: Rs. %.2f
                VAT (15%%): Rs. %.2f
                
                Total Bill: Rs. %.2f
            """.trimIndent().format(fixedCharge, energyCharge, serviceCharge, vatAmount, totalBill)

                } catch (e: NumberFormatException) {
                    textField.text = "Invalid input. Please enter a valid number of units."
                }
            } else {
                textField.text = "Please enter the number of units."
            }
        }

    }
}