package com.example.calculator.calcbutton


import android.R.color.white
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorButton(digit: String, color: Color, size: Int,onclick:()->Unit) {
    Button(
        onClick = {onclick()},
        colors = ButtonDefaults.buttonColors(
            containerColor = color
        ),
        modifier = Modifier.size(size.dp).clip(RoundedCornerShape(20.dp)),
        shape = RectangleShape
    ) {
        Text(
        text=digit,
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold,
            color=White
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CalculatorButtonPreview() {
    CalculatorButton("10",Blue,100,{})
}