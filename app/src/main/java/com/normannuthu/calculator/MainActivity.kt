package com.normannuthu.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var Screen: TextView? = null
    private var AC: Button? = null
    private var Power: Button? = null
    private var Back: Button? = null
    private var Div: Button? = null
    private var Mul: Button? = null
    private var Add: Button? = null
    private var Minus: Button? = null
    private var One: Button? = null
    private var Two: Button? = null
    private var Three: Button? = null
    private var Four: Button? = null
    private var Five: Button? = null
    private var Six: Button? = null
    private var Seven: Button? = null
    private var Eight: Button? = null
    private var Nine: Button? = null
    private var Zero: Button? = null
    private var Dot: Button? = null
    private var Ans: Button? = null
    private var Equal: Button? = null
    private var input: String? = ""
    private var Answer: String? = null
    private var clearResult = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Screen = findViewById(R.id.screen)
        AC = findViewById(R.id.ac)
        Power = findViewById(R.id.power)
        Back = findViewById(R.id.back)
        Div = findViewById(R.id.div)
        Mul = findViewById(R.id.mul)
        Add = findViewById(R.id.plus)
        Minus = findViewById(R.id.minus)
        One = findViewById(R.id.one)
        Two = findViewById(R.id.two)
        Three = findViewById(R.id.three)
        Four = findViewById(R.id.four)
        Five = findViewById(R.id.five)
        Six = findViewById(R.id.six)
        Seven = findViewById(R.id.seven)
        Eight = findViewById(R.id.eight)
        Nine = findViewById(R.id.nine)
        Zero = findViewById(R.id.zero)
        Dot = findViewById(R.id.dot)
        Ans = findViewById(R.id.ans)
        Equal = findViewById(R.id.equal)
    }

    fun ButtonClick(view: View) {
        val button = view as Button
        val data = button.text.toString()
        when (data) {
            "AC" -> input = ""
            "Ans" -> {
                clearResult = false
                input += Answer
            }
            "x" -> {
                clearResult = false
                Solve()
                input += "*"
            }
            "^" -> {
                clearResult = false
                Solve()
                input += "^"
            }
            "=" -> {
                clearResult = true
                Solve()
                Answer = input
            }
            "⬅" -> if (input!!.length > 0) {
                clearResult = false
                val newText = input!!.substring(0, input!!.length - 1)
                input = newText
            }
            else -> {
                if (input == null) {
                    input = ""
                }
                if (data == "+" || data == "-" || data == "/") {
                    clearResult = false
                    Solve()
                } else if (clearResult == true) {
                    input = ""
                    clearResult = false
                }
                input += data
            }
        }
        Screen!!.text = input
    }

    fun Solve() {
        if (input!!.split("\\*".toRegex()).toTypedArray().size == 2) {
            val numbers = input!!.split("\\*".toRegex()).toTypedArray()
            try {
                val mul = numbers[0].toDouble() * numbers[1].toDouble()
                input = mul.toString() + ""
            } catch (e: Exception) {
            }
        } else if (input!!.split("/".toRegex()).toTypedArray().size == 2) {
            val numbers = input!!.split("/".toRegex()).toTypedArray()
            try {
                val div = numbers[0].toDouble() / numbers[1].toDouble()
                input = div.toString() + ""
            } catch (e: Exception) {
            }
        } else if (input!!.split("\\^".toRegex()).toTypedArray().size == 2) {
            val numbers = input!!.split("\\^".toRegex()).toTypedArray()
            try {
                val pow = Math.pow(numbers[0].toDouble(), numbers[1].toDouble())
                input = pow.toString() + ""
            } catch (e: Exception) {
            }
        } else if (input!!.split("\\+".toRegex()).toTypedArray().size == 2) {
            val numbers = input!!.split("\\+".toRegex()).toTypedArray()
            try {
                val sum = numbers[0].toDouble() + numbers[1].toDouble()
                input = sum.toString() + ""
            } catch (e: Exception) {
                //Display error
            }
        } else if (input!!.split("\\-".toRegex()).toTypedArray().size > 1) {
            val numbers = input!!.split("\\-".toRegex()).toTypedArray()
            if (numbers[0] === "" && numbers.size == 2) {
                numbers[0] = 0.toString() + ""
            }
            try {
                var sub = 0.0
                if (numbers.size == 2) {
                    sub = numbers[0].toDouble() - numbers[1].toDouble()
                } else if (numbers.size == 3) {
                    sub = -numbers[1].toDouble() - numbers[2].toDouble()
                }
                input = sub.toString() + ""
            } catch (e: Exception) {
            }
        }
        val n = input!!.split("\\.".toRegex()).toTypedArray()
        if (n.size > 1) {
            if (n[1] == "0") {
                input = n[0]
            }
        }
        Screen!!.text = input
    }
}