package com.example.calculator.calcUI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.calcbutton.CalculatorButton
import java.nio.file.WatchEvent

@Composable
fun CalculatorUI() {

    var input1 by remember { mutableStateOf("") }
    var input2 by remember { mutableStateOf("") }
    var operator by remember { mutableStateOf<String?>(null) }
    var result by remember { mutableStateOf("") }

    var input = "$input1 ${operator ?: ""} $input2"


    fun valueAppend(value: String) {
        if (operator == null) {
            input1 += value
        } else {
            input2 += value
        }
    }

    fun setOperator(op: String) {
        if (input1.isNotEmpty() && operator == null) {
            operator = op
        }
    }

    fun Del() {
        if (operator == null && input1.isNotEmpty()) {
            input1 = input1.dropLast(1)
        } else if (operator != null && input2.isNotEmpty()) {
            input2 = input2.dropLast(1)
        } else if (operator != null && input2.isEmpty()) {
            operator = null
        }
    }

    fun pM() {
        if (operator == null && input1.isNotEmpty()) {
            input1 = if (input1.startsWith("-")) input1.drop(1) else "-$input1"
        } else if (operator != null && input2.isNotEmpty()) {
            input2 = if (input2.startsWith("-")) input2.drop(1) else "-$input2"
        }
    }

    fun Calculate() {
        val n1 = input1.toDoubleOrNull()
        val n2 = input2.toDoubleOrNull()

        if (n1 != null && n2 != null && operator != null) {
            result = when (operator) {
                "+" -> (n1 + n2).toString()
                "-" -> (n1 - n2).toString()
                "*" -> (n1 * n2).toString()
                else -> (n1 / n2).toString()
            }
        } else {
            result = ""
        }
    }

    fun clearAll() {
        input1 = ""
        input2 = ""
        operator = null
        result = ""
    }



    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .padding(top = 50.dp, start = 2.dp, end = 2.dp)
                .height(250.dp)
                .fillMaxWidth()
                .background(Gray), contentAlignment = Alignment.CenterEnd
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 50.dp, end = 20.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {
                Text(text = input, fontSize = 50.sp, color = White, fontWeight = FontWeight.Bold)
                Divider(modifier = Modifier.wrapContentWidth())
                Text(text = result, fontSize = 100.sp, color = White,
                    fontFamily = FontFamily.Serif)
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CalculatorButton("CE", Blue, 85, { input1 = "";input2 = "";operator = null })
            CalculatorButton("C", Blue, 85, { clearAll() })
            CalculatorButton("<-", Blue, 85, { Del() })
            CalculatorButton("/", Blue, 85, { setOperator("/") })
        }

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CalculatorButton("7", Blue, 85, { valueAppend("7") })
            CalculatorButton("8", Blue, 85, { valueAppend("8") })
            CalculatorButton("9", Blue, 85, { valueAppend("9") })
            CalculatorButton("*", Blue, 85, { setOperator("*") })
        }

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CalculatorButton("4", Blue, 85, { valueAppend("4") })
            CalculatorButton("5", Blue, 85, { valueAppend("5") })
            CalculatorButton("6", Blue, 85, { valueAppend("6") })
            CalculatorButton("-", Blue, 85, { setOperator("-") })
        }

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CalculatorButton("1", Blue, 85, { valueAppend("1") })
            CalculatorButton("2", Blue, 85, { valueAppend("2") })
            CalculatorButton("3", Blue, 85, { valueAppend("3") })
            CalculatorButton("+", Blue, 85, { setOperator("+") })
        }

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CalculatorButton("+/-", Blue, 85, { pM() })
            CalculatorButton("0", Blue, 85, { valueAppend("0") })
            CalculatorButton(".", Blue, 85, { valueAppend(".") })
            CalculatorButton("=", Blue, 85, { Calculate() })
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CalculatorUIPreview() {
    CalculatorUI()
}

