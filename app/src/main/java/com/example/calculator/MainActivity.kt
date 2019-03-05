package com.example.calculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var finishCalcFlag = false

    private fun inputData(data:String) {
        if(data == "+" || data == "-" || data == "*" || data == "/" || data == "=") {
            if(screen.text != "0" && screen.text.substring(screen.text.length - 1, screen.text.length).toIntOrNull() != null) {
                val oriText = screen.text
                val text = "$oriText$data"
                screen.text = text
                if(finishCalcFlag == true) {
                    finishCalcFlag = false
                }
            }
        } else {
            if(screen.text == "0" || finishCalcFlag == true){
                screen.text = data
                finishCalcFlag = false
            } else {
                val oriText = screen.text
                val text = "$oriText$data"
                screen.text = text
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        plusButton.setOnClickListener {
            inputData("+")
        }

        minusButton.setOnClickListener {
            inputData("-")
        }

        multiplyButton.setOnClickListener {
            inputData("*")
        }

        divisionButton.setOnClickListener {
            inputData("/")
        }

        oneButton.setOnClickListener {
            inputData("1")
        }

        twoButton.setOnClickListener {
            inputData("2")
        }

        threeButton.setOnClickListener {
            inputData("3")
        }

        fourButton.setOnClickListener {
            inputData("4")
        }

        fiveButton.setOnClickListener {
            inputData("5")
        }

        sixButton.setOnClickListener {
            inputData("6")
        }

        sevenButton.setOnClickListener {
            inputData("7")
        }

        eightButton.setOnClickListener {
            inputData("8")
        }

        nineButton.setOnClickListener {
            inputData("9")
        }

        zeroButton.setOnClickListener {
            inputData("0")
        }

        deleteButton.setOnClickListener {
            if(screen.text != "0") {
                val text = screen.text.substring(0, screen.text.length - 1)
                if(text.isEmpty()) {
                    screen.text = "0"
                } else{
                    screen.text = text
                }
            }
        }

        resultButton.setOnClickListener {
            val text = screen.text
            val numbers = mutableListOf<Int>()
            val operators = mutableListOf<String>()

            var index = text.length
            for (i in text.length downTo 1){
                val data = text.substring(i-1, i)
                if(data == "+" || data == "-" || data == "*" || data == "/"){
                    numbers.add(text.substring(i, index).toInt())
                    if(operators.size != 0 && (data == "+" || data == "-")){
                        for(j in operators.size downTo 1){
                            val oper = operators[operators.size-1]
                            if(oper == "+" || oper == "-"){
                                break
                            }
                            operators.removeAt(operators.size-1)
                            var sum = numbers[numbers.size-1]
                            numbers.removeAt(numbers.size-1)
                            val n = numbers[numbers.size-1]
                            numbers.removeAt(numbers.size-1)
                            when(oper){
                                "*" -> sum *= n
                                "/" -> sum /= n
                            }
                            numbers.add(sum)
                        }
                    }
                    operators.add(data)
                    index = i - 1
                }
            }
            numbers.add(text.substring(0, index).toInt())
            for(i in operators.size downTo 1) {
                val oper = operators[operators.size - 1]
                if (oper == "*" || oper == "/") {
                    operators.removeAt(operators.size - 1)
                    var sum = numbers[numbers.size - 1]
                    numbers.removeAt(numbers.size - 1)
                    val n = numbers[numbers.size - 1]
                    numbers.removeAt(numbers.size - 1)
                    when (oper) {
                        "*" -> sum *= n
                        "/" -> sum /= n
                    }
                    numbers.add(sum)
                }
            }

            for(i in operators.size downTo 1){
                val oper = operators[operators.size-1]
                operators.removeAt(operators.size-1)
                var sum = numbers[numbers.size-1]
                numbers.removeAt(numbers.size-1)
                val n = numbers[numbers.size-1]
                numbers.removeAt(numbers.size-1)

                when(oper){
                    "+" -> sum += n
                    "-" -> sum -= n
                }
                numbers.add(sum)
            }
            screen.text = numbers[numbers.size-1].toString()
            numbers.removeAt(numbers.size-1)
            finishCalcFlag = true
        }
    }
}
