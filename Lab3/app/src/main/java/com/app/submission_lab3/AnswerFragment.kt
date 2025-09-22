package com.app.submission_lab3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.text.DecimalFormat

class AnswerFragment : Fragment() {

    companion object {
        private const val ARG_RESULT = "result"

        fun newInstance(result: Double): AnswerFragment {
            val f = AnswerFragment()
            val args = Bundle().apply { putDouble(ARG_RESULT, result) }
            f.arguments = args
            return f
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_answer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val textAnswer = view.findViewById<TextView>(R.id.textAnswer)
        val btnBack = view.findViewById<Button>(R.id.btnBack)

        val result = arguments?.getDouble(ARG_RESULT) ?: 0.0
        val df = DecimalFormat("#.######")
        textAnswer.text = "Answer: ${df.format(result)}"

        btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}