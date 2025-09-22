package com.app.submission_lab3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val et1 = view.findViewById<EditText>(R.id.number1)
        val et2 = view.findViewById<EditText>(R.id.number2)
        val btnPlus = view.findViewById<Button>(R.id.plus)
        val btnMinus = view.findViewById<Button>(R.id.minus)
        val btnMul = view.findViewById<Button>(R.id.multiply)
        val btnDiv = view.findViewById<Button>(R.id.divide)

        fun readNumbers(): Pair<Double, Double>? {
            val aText = et1.text.toString().trim()
            val bText = et2.text.toString().trim()
            if (aText.isEmpty() || bText.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill both numbers", Toast.LENGTH_SHORT).show()
                return null
            }
            val a = aText.toDoubleOrNull()
            val b = bText.toDoubleOrNull()
            if (a == null || b == null) {
                Toast.makeText(requireContext(), "Enter valid numbers", Toast.LENGTH_SHORT).show()
                return null
            }
            return Pair(a, b)
        }

        btnPlus.setOnClickListener {
            val nums = readNumbers() ?: return@setOnClickListener
            openAnswer(nums.first + nums.second)
        }

        btnMinus.setOnClickListener {
            val nums = readNumbers() ?: return@setOnClickListener
            openAnswer(nums.first - nums.second)
        }

        btnMul.setOnClickListener {
            val nums = readNumbers() ?: return@setOnClickListener
            openAnswer(nums.first * nums.second)
        }

        btnDiv.setOnClickListener {
            val nums = readNumbers() ?: return@setOnClickListener
            if (nums.second == 0.0) {
                Toast.makeText(requireContext(), "Cannot divide by zero", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            openAnswer(nums.first / nums.second)
        }
    }

    private fun openAnswer(result: Double) {
        val frag = AnswerFragment.newInstance(result)
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, frag)
            .addToBackStack(null)
            .commit()
    }
}