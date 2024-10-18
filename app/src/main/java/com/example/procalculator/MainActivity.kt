package com.example.procalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var toolbarMain: Toolbar
    private lateinit var inputNumbersET: EditText
    private lateinit var resultTV: TextView

    private lateinit var oneBTN: Button
    private lateinit var twoBTN: Button
    private lateinit var threeBTN: Button
    private lateinit var fourBTN: Button
    private lateinit var fiveBTN: Button
    private lateinit var sixBTN: Button
    private lateinit var sevenBTN: Button
    private lateinit var eightBTN: Button
    private lateinit var nineBTN: Button
    private lateinit var zeroBTN: Button

    private lateinit var sumBTN: Button
    private lateinit var diffBTN: Button
    private lateinit var multBTN: Button
    private lateinit var divBTN: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        toolbarMain = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbarMain)
        title = "Калькулятор ПРО"
        toolbarMain.setLogo(R.drawable.ic_calculate)


        inputNumbersET = findViewById(R.id.inputNumbersET)
        resultTV = findViewById(R.id.resultTV)

        oneBTN = findViewById(R.id.oneBTN)
        twoBTN = findViewById(R.id.twoBTN)
        threeBTN = findViewById(R.id.threeBTN)
        fourBTN = findViewById(R.id.fourBTN)
        fiveBTN = findViewById(R.id.fiveBTN)
        sixBTN = findViewById(R.id.sixBTN)
        sevenBTN = findViewById(R.id.sevenBTN)
        eightBTN = findViewById(R.id.eightBTN)
        nineBTN = findViewById(R.id.nineBTN)
        zeroBTN = findViewById(R.id.zeroBTN)

        sumBTN = findViewById(R.id.sumBTN)
        diffBTN = findViewById(R.id.diffBTN)
        multBTN = findViewById(R.id.multBTN)
        divBTN = findViewById(R.id.divBTN)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mainMenuExit -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.oneBTN -> inputNumbersET.append(oneBTN.text)
            R.id.twoBTN -> inputNumbersET.append(twoBTN.text)
            R.id.threeBTN -> inputNumbersET.append(threeBTN.text)
            R.id.fourBTN -> inputNumbersET.append(fourBTN.text)
            R.id.fiveBTN -> inputNumbersET.append(fiveBTN.text)
            R.id.sixBTN -> inputNumbersET.append(sixBTN.text)
            R.id.sevenBTN -> inputNumbersET.append(sevenBTN.text)
            R.id.eightBTN -> inputNumbersET.append(eightBTN.text)
            R.id.nineBTN -> inputNumbersET.append(nineBTN.text)
            R.id.zeroBTN -> inputNumbersET.append(zeroBTN.text)
            R.id.sumBTN -> inputNumbersET.append(sumBTN.text)
            R.id.diffBTN -> inputNumbersET.append(divBTN.text)
            R.id.multBTN -> inputNumbersET.append(multBTN.text)
            R.id.divBTN -> inputNumbersET.append(divBTN.text)
            R.id.resultBTN -> {
                val text = inputNumbersET.text.toString()
                val result = calculate(text)
                resultTV.text = result.toString()
            }
            R.id.resetBTN -> inputNumbersET.text.clear()
        }
    }

    private fun calculate(expression: String): Double? {
        return try {
            val numbers = mutableListOf<Double>()
            val operators = mutableListOf<Char>()

            val tokens = expression.toList().map {it.toString()}
            for (token in tokens) {
                when {
                    token.matches(Regex("\\d+(\\.\\d+)?")) -> {
                        numbers.add(token.toDouble())
                    }
                    token.length == 1 && "+-*/".contains(token[0]) -> {
                        operators.add(token[0])
                    }
                }
            }

            var result = numbers[0]
            for (i in operators.indices) {
                result = when (operators[i]) {
                    '+' -> result + numbers[i + 1]
                    '-' -> result - numbers[i + 1]
                    '*' -> result * numbers[i + 1]
                    '/' -> result / numbers[i + 1]
                    else -> result
                }
            }
            result
        } catch (e:Exception) {
            null
        }
    }
}