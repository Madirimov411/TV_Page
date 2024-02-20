package com.uzb_khiva.quizappinxml

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.uzb_khiva.quizappinxml.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var questions: MutableList<Question>
    private lateinit var binding: ActivityMainBinding
    var selectIndex = 0
    var correctAnswer = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        questions = getQuestions()

        pushNextQuestion()

        binding.apply {
            radioGroup.setOnCheckedChangeListener() { group, checkedId ->
                click.isEnabled = true
            }
        }

        binding.click.setOnClickListener {
            checkAnswer()
        }


    }

    private fun checkAnswer() {
        binding.apply {
            if (rbA.isChecked && questions[selectIndex].correctAnswer == 1
                || rbB.isChecked && questions[selectIndex].correctAnswer == 2
                || rbC.isChecked && questions[selectIndex].correctAnswer == 3
                || rbD.isChecked && questions[selectIndex].correctAnswer == 4
            ) {
                correctAnswer++
            } else {
                correctAnswer = correctAnswer
            }
        }
        selectIndex++
        if (selectIndex < questions.size) {
            pushNextQuestion()
        } else {
            AlertDialog.Builder(this)
                .setTitle("Tamom")
                .setMessage("Siz ${correctAnswer} ta savolni topdingiz\nYana test ishlaysizmi?")
                .setPositiveButton("Ha") { _, dialog ->
                    selectIndex = 0
                    correctAnswer = 0
                    questions.clear()
                    questions = getQuestions()
                    pushNextQuestion()
                }
                .setNegativeButton("Yo'q") { _, _ ->
                    finish()
                }
                .create()
                .show()
        }
    }

    private fun pushNextQuestion() {
        binding.apply {
            rbA.isChecked = false
            rbB.isChecked = false
            rbC.isChecked = false
            rbD.isChecked = false
            click.isEnabled = false
            savolText.text = questions[selectIndex].savol
            rbA.text = questions[selectIndex].aJavob
            rbB.text = questions[selectIndex].bJavob
            rbC.text = questions[selectIndex].cJavob
            rbD.text = questions[selectIndex].dJavob
        }
    }

    private fun getQuestions(): MutableList<Question> {
        val myList = mutableListOf<Question>()
        myList.add(
            Question(
                "Messining vatani qayer",
                "Argentina",
                "Australia",
                "Armenia",
                "Austria",
                1
            )
        )
        myList.add(
            Question(
                "O'zbekistonning poytaxti qaysi shahar?",
                "Andijon",
                "Toshkent",
                "Farg'ona",
                "Namangan",
                2
            )
        )

        myList.add(
            Question(
                "Ronalduning vatani qayer",
                "Argentina",
                "Fransiya",
                "Portugaliya",
                "Braziliya",
                3
            )
        )

        myList.add(
            Question(
                "AQSHning poytaxti qaysi shahar?",
                "Nyu-York",
                "Texas",
                "Californiya",
                "Vashington",
                4
            )
        )

        myList.add(
            Question(
                "Rossiyaning poytaxti qaysi shahar?",
                "Moskva",
                "Sankt-Peterburg",
                "Kreml",
                "Nizhniy Novgorod",
                1
            )
        )
        myList.shuffle()
        return myList
    }
}