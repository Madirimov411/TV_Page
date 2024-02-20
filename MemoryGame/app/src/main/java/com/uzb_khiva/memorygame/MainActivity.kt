package com.uzb_khiva.memorygame

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.util.Random

class MainActivity : AppCompatActivity() {

    private lateinit var tv_p1: TextView
    private lateinit var tv_p2: TextView
    private lateinit var iv_11: ImageView
    private lateinit var iv_12: ImageView
    private lateinit var iv_13: ImageView
    private lateinit var iv_14: ImageView
    private lateinit var iv_21: ImageView
    private lateinit var iv_22: ImageView
    private lateinit var iv_23: ImageView
    private lateinit var iv_24: ImageView
    private lateinit var iv_31: ImageView
    private lateinit var iv_32: ImageView
    private lateinit var iv_33: ImageView
    private lateinit var iv_34: ImageView

    private var cardsArray = listOf(101, 102, 103, 104, 105, 106, 201, 202, 203, 204, 205, 206)

    private var image101: Int? = null
    private var image102: Int? = null
    private var image103: Int? = null
    private var image104: Int? = null
    private var image105: Int? = null
    private var image106: Int? = null
    private var image201: Int? = null
    private var image202: Int? = null
    private var image203: Int? = null
    private var image204: Int? = null
    private var image205: Int? = null
    private var image206: Int? = null

    private var firstCard: Int? = null
    private var secondCard: Int? = null

    private var clickedFirst: Int? = null
    private var clickedSecond: Int? = null
    private var cardNumber: Int? = 1

    private var turn = 1
    private var playerPoints = 0
    private var cpuPoints = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initViews()
        cardsArray.shuffled()
        frontOfCardsResources()

        tv_p2.setTextColor(Color.GRAY)

        iv_11.setOnClickListener {
            val theCard = it.tag.toString().toInt()
            doStuffs(iv_11, theCard)
        }

        iv_12.setOnClickListener {
            val theCard = it.tag.toString().toInt()
            doStuffs(iv_12, theCard)
        }

        iv_13.setOnClickListener {
            val theCard = it.tag.toString().toInt()
            doStuffs(iv_13, theCard)
        }

        iv_14.setOnClickListener {
            val theCard = it.tag.toString().toInt()
            doStuffs(iv_14, theCard)
        }

        iv_21.setOnClickListener {
            val theCard = it.tag.toString().toInt()
            doStuffs(iv_21, theCard)
        }

        iv_22.setOnClickListener {
            val theCard = it.tag.toString().toInt()
            doStuffs(iv_22, theCard)
        }

        iv_23.setOnClickListener {
            val theCard = it.tag.toString().toInt()
            doStuffs(iv_23, theCard)
        }

        iv_24.setOnClickListener {
            val theCard = it.tag.toString().toInt()
            doStuffs(iv_24, theCard)
        }

        iv_31.setOnClickListener {
            val theCard = it.tag.toString().toInt()
            doStuffs(iv_31, theCard)
        }

        iv_32.setOnClickListener {
            val theCard = it.tag.toString().toInt()
            doStuffs(iv_32, theCard)
        }

        iv_33.setOnClickListener {
            val theCard = it.tag.toString().toInt()
            doStuffs(iv_33, theCard)
        }

        iv_34.setOnClickListener {
            val theCard = it.tag.toString().toInt()
            doStuffs(iv_34, theCard)
        }


    }

    private fun doStuffs(image: ImageView, card: Int) {
        when (cardsArray[card]) {
            101 -> image.setImageResource(image101!!)
            102 -> image.setImageResource(image102!!)
            103 -> image.setImageResource(image103!!)
            104 -> image.setImageResource(image104!!)
            105 -> image.setImageResource(image105!!)
            106 -> image.setImageResource(image106!!)

            201 -> image.setImageResource(image201!!)
            202 -> image.setImageResource(image202!!)
            203 -> image.setImageResource(image203!!)
            204 -> image.setImageResource(image204!!)
            205 -> image.setImageResource(image205!!)
            206 -> image.setImageResource(image206!!)
        }

        when (cardNumber) {
            1 -> {
                firstCard = cardsArray[card]
                if (firstCard!! > 200) {
                    firstCard = firstCard!! - 100
                }
                cardNumber = 2
                image.isEnabled = false
                clickedFirst = card
            }

            2 -> {
                secondCard = cardsArray[card]
                if (secondCard!! > 200) {
                    secondCard = secondCard!! - 100
                }
                cardNumber = 1
                clickedSecond = card


                iv_11.isEnabled = false
                iv_12.isEnabled = false
                iv_13.isEnabled = false
                iv_14.isEnabled = false
                iv_21.isEnabled = false
                iv_22.isEnabled = false
                iv_23.isEnabled = false
                iv_24.isEnabled = false
                iv_31.isEnabled = false
                iv_32.isEnabled = false
                iv_33.isEnabled = false
                iv_34.isEnabled = false
                Handler().postDelayed({
                    calculate()
                }, 1000)
            }
        }
    }

    private fun calculate() {
        if (firstCard == secondCard) {
            when (clickedFirst) {
                0 -> iv_11.visibility = View.INVISIBLE
                1 -> iv_12.visibility = View.INVISIBLE
                2 -> iv_13.visibility = View.INVISIBLE
                3 -> iv_14.visibility = View.INVISIBLE

                4 -> iv_21.visibility = View.INVISIBLE
                5 -> iv_22.visibility = View.INVISIBLE
                6 -> iv_23.visibility = View.INVISIBLE
                7 -> iv_24.visibility = View.INVISIBLE

                8 -> iv_31.visibility = View.INVISIBLE
                9 -> iv_32.visibility = View.INVISIBLE
                10 -> iv_33.visibility = View.INVISIBLE
                11 -> iv_34.visibility = View.INVISIBLE
            }
            when (clickedSecond) {
                0 -> iv_11.visibility = View.INVISIBLE
                1 -> iv_12.visibility = View.INVISIBLE
                2 -> iv_13.visibility = View.INVISIBLE
                3 -> iv_14.visibility = View.INVISIBLE

                4 -> iv_21.visibility = View.INVISIBLE
                5 -> iv_22.visibility = View.INVISIBLE
                6 -> iv_23.visibility = View.INVISIBLE
                7 -> iv_24.visibility = View.INVISIBLE

                8 -> iv_31.visibility = View.INVISIBLE
                9 -> iv_32.visibility = View.INVISIBLE
                10 -> iv_33.visibility = View.INVISIBLE
                11 -> iv_34.visibility = View.INVISIBLE
            }

            if (turn == 1) {
                playerPoints++
                tv_p1.text = "P1: $playerPoints"
            } else if (turn == 2) {
                cpuPoints++
                tv_p2.text = "P2: $cpuPoints"
            }
        } else {
            iv_11.setImageResource(R.drawable.question)
            iv_12.setImageResource(R.drawable.question)
            iv_13.setImageResource(R.drawable.question)
            iv_14.setImageResource(R.drawable.question)

            iv_21.setImageResource(R.drawable.question)
            iv_22.setImageResource(R.drawable.question)
            iv_23.setImageResource(R.drawable.question)
            iv_24.setImageResource(R.drawable.question)

            iv_31.setImageResource(R.drawable.question)
            iv_32.setImageResource(R.drawable.question)
            iv_33.setImageResource(R.drawable.question)
            iv_34.setImageResource(R.drawable.question)

            if (turn == 1) {
                turn = 2
                tv_p1.setTextColor(Color.GRAY)
                tv_p2.setTextColor(Color.BLACK)
            } else if (turn == 2) {
                turn = 1
                tv_p1.setTextColor(Color.BLACK)
                tv_p2.setTextColor(Color.GRAY)
            }
        }

        iv_11.isEnabled = true
        iv_12.isEnabled = true
        iv_13.isEnabled = true
        iv_14.isEnabled = true

        iv_21.isEnabled = true
        iv_22.isEnabled = true
        iv_23.isEnabled = true
        iv_24.isEnabled = true

        iv_31.isEnabled = true
        iv_32.isEnabled = true
        iv_33.isEnabled = true
        iv_34.isEnabled = true

        checkEnd()

    }

    private fun checkEnd() {
        if (iv_11.visibility == View.INVISIBLE
            && iv_12.visibility == View.INVISIBLE
            && iv_13.visibility == View.INVISIBLE
            && iv_14.visibility == View.INVISIBLE
            && iv_21.visibility == View.INVISIBLE
            && iv_22.visibility == View.INVISIBLE
            && iv_23.visibility == View.INVISIBLE
            && iv_24.visibility == View.INVISIBLE
            && iv_31.visibility == View.INVISIBLE
            && iv_32.visibility == View.INVISIBLE
            && iv_33.visibility == View.INVISIBLE
            && iv_34.visibility == View.INVISIBLE
        ) {
            AlertDialog.Builder(this)
                .setTitle("Game Over")
                .setMessage("P1: $playerPoints \nP2: $cpuPoints")
                .setPositiveButton("NEW") { _, _ ->
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                .setNegativeButton("EXIT") { _, _ ->
                    finish()
                }
                .setCancelable(false)
                .create()
                .show()
        }
    }

    private fun frontOfCardsResources() {
        image101 = R.drawable.lion
        image102 = R.drawable.chicken
        image103 = R.drawable.cow
        image104 = R.drawable.deer
        image105 = R.drawable.elephant
        image106 = R.drawable.sheep

        image201 = R.drawable.lion
        image202 = R.drawable.chicken
        image203 = R.drawable.cow
        image204 = R.drawable.deer
        image205 = R.drawable.elephant
        image206 = R.drawable.sheep
    }

    private fun initViews() {
        tv_p1 = findViewById(R.id.tv_p1)
        tv_p2 = findViewById(R.id.tv_p2)

        iv_11 = findViewById(R.id.iv_11)
        iv_12 = findViewById(R.id.iv_12)
        iv_13 = findViewById(R.id.iv_13)
        iv_14 = findViewById(R.id.iv_14)

        iv_21 = findViewById(R.id.iv_21)
        iv_22 = findViewById(R.id.iv_22)
        iv_23 = findViewById(R.id.iv_23)
        iv_24 = findViewById(R.id.iv_24)

        iv_31 = findViewById(R.id.iv_31)
        iv_32 = findViewById(R.id.iv_32)
        iv_33 = findViewById(R.id.iv_33)
        iv_34 = findViewById(R.id.iv_34)

        iv_11.tag = "0"
        iv_12.tag = "1"
        iv_13.tag = "2"
        iv_14.tag = "3"
        iv_21.tag = "4"
        iv_22.tag = "5"
        iv_23.tag = "6"
        iv_24.tag = "7"
        iv_31.tag = "8"
        iv_32.tag = "9"
        iv_33.tag = "10"
        iv_34.tag = "11"
    }
}