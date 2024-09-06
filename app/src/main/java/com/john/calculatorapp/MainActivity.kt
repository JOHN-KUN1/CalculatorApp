package com.john.calculatorapp

import android.icu.text.DecimalFormat
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.john.calculatorapp.databinding.ActivityMainBinding
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding : ActivityMainBinding
    var displayText : String = ""
    var operators = arrayListOf("-","+","/","*",".","%")

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // LISTENERS

        mainBinding.buttonC.setOnClickListener {

            val animation = AnimationUtils.loadAnimation(applicationContext,R.anim.c_pressed)
            mainBinding.buttonC.isClickable = false
            mainBinding.textViewDisplay.startAnimation(animation)
            mainBinding.textViewResult.startAnimation(animation)

            val handler = Handler(Looper.getMainLooper())
            handler.postDelayed(object : Runnable {
                override fun run() {
                    mainBinding.textViewDisplay.text = ""
                    mainBinding.textViewResult.text = ""
                    displayText = ""
                    mainBinding.buttonC.isClickable = true
                }
            },401)
            enableOperatorButtons()

        }

        mainBinding.buttonDivide.setOnClickListener {
            val userValue = "/"
            displayText += userValue
            mainBinding.textViewDisplay.text = displayText
            disableOperatorButtons()
        }

        mainBinding.buttonMul.setOnClickListener {
            val userValue = "*"
            displayText += userValue
            mainBinding.textViewDisplay.text = displayText
            disableOperatorButtons()
        }

        mainBinding.buttonAdd.setOnClickListener {
            val userValue = "+"
            displayText += userValue
            mainBinding.textViewDisplay.text = displayText
            disableOperatorButtons()
        }

        mainBinding.buttonMinus.setOnClickListener {
            val userValue = "-"
            displayText += userValue
            mainBinding.textViewDisplay.text = displayText
            disableOperatorButtons()
        }

        mainBinding.buttonBackSpace.setOnClickListener {
            displayText = displayText.slice(0..displayText.length-2)
            mainBinding.textViewDisplay.text = displayText
            enableOperatorButtons()
        }

        mainBinding.button7.setOnClickListener {
            val userValue = "7"
            displayText += userValue
            mainBinding.textViewDisplay.text = displayText
            enableOperatorButtons()
        }

        mainBinding.button8.setOnClickListener {
            val userValue = "8"
            displayText += userValue
            mainBinding.textViewDisplay.text = displayText
            enableOperatorButtons()
        }

        mainBinding.button9.setOnClickListener {
            val userValue = "9"
            displayText += userValue
            mainBinding.textViewDisplay.text = displayText
            enableOperatorButtons()
        }

        mainBinding.button4.setOnClickListener {
            val userValue = "4"
            displayText += userValue
            mainBinding.textViewDisplay.text = displayText
            enableOperatorButtons()
        }

        mainBinding.button5.setOnClickListener {
            val userValue = "5"
            displayText += userValue
            mainBinding.textViewDisplay.text = displayText
            enableOperatorButtons()
        }

        mainBinding.button6.setOnClickListener {
            val userValue = "6"
            displayText += userValue
            mainBinding.textViewDisplay.text = displayText
            enableOperatorButtons()
        }

        mainBinding.button1.setOnClickListener {
            val userValue = "1"
            displayText += userValue
            mainBinding.textViewDisplay.text = displayText
            enableOperatorButtons()
        }

        mainBinding.button2.setOnClickListener {
            val userValue = "2"
            displayText += userValue
            mainBinding.textViewDisplay.text = displayText
            enableOperatorButtons()
        }

        mainBinding.button3.setOnClickListener {
            val userValue = "3"
            displayText += userValue
            mainBinding.textViewDisplay.text = displayText
            enableOperatorButtons()
        }

        mainBinding.buttonPercent.setOnClickListener {
            val userValue = "%"
            displayText += userValue
            mainBinding.textViewDisplay.text = displayText
            disableOperatorButtons()
        }

        mainBinding.button0.setOnClickListener {
            val userValue = "0"
            displayText += userValue
            mainBinding.textViewDisplay.text = displayText
        }

        mainBinding.buttonPoint.setOnClickListener {
            val userValue = "."
            displayText += userValue
            mainBinding.textViewDisplay.text = displayText
            disableOperatorButtons()
        }
        mainBinding.buttonEqual.setOnClickListener {
            if (displayText[displayText.length-1].toString() in operators ){
                mainBinding.textViewResult.text = "Invalid Expression"
            }
            else{
               evaluateExpression(mainBinding.textViewDisplay.text.toString())
            }
        }

    }

    fun evaluateExpression(userValues : String){
        var result = ExpressionBuilder(userValues).build().evaluate()
        var resultString = result.toString()
        if (resultString.length > 12){
            mainBinding.textViewResult.text = result.toString()
        }else{
            mainBinding.textViewResult.text = result.toLong().toString()
        }
    }

    fun disableOperatorButtons(){
        mainBinding.buttonMul.isClickable = false
        mainBinding.buttonAdd.isClickable = false
        mainBinding.buttonMinus.isClickable = false
        mainBinding.buttonDivide.isClickable = false
        mainBinding.buttonPercent.isClickable = false
        mainBinding.buttonPoint.isClickable = false
    }

    fun enableOperatorButtons(){
        mainBinding.buttonMul.isClickable = true
        mainBinding.buttonAdd.isClickable = true
        mainBinding.buttonMinus.isClickable = true
        mainBinding.buttonDivide.isClickable = true
        mainBinding.buttonPercent.isClickable = true
        mainBinding.buttonPoint.isClickable = true
    }


    // may be used to display numbers entered by users in real time
//    @RequiresApi(Build.VERSION_CODES.N)
//    fun formatNumbers(result : Float){
//        val numberFormat = DecimalFormat("#,###.##")
//        val formattedNumber = numberFormat.format(result)
//        mainBinding.textViewResult.text = formattedNumber.toString()
//    }

}