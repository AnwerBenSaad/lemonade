package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonApp()
            }
        }
    }
}

@Composable
fun LemonApp() {
    var currentStep by remember { mutableStateOf(1) }
    var lemonPressCount by remember { mutableStateOf(0) }
    val randomPressCount = (2..4).random()

    Surface(

        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (currentStep) {
            1 -> {
                LemonTextAndImage(
                    text = stringResource(id = R.string.lemon_tree),
                    imageResId = R.drawable.lemon_tree,
                    onClick = {
                        currentStep = 2
                        lemonPressCount = randomPressCount
                    }
                )
            }
            2 -> {
                // Étape 2 : Presser le citron
                LemonTextAndImage(
                    text = stringResource(id = R.string.lemon),
                    imageResId = R.drawable.lemon_squeeze,
                    onClick = {
                        if (lemonPressCount > 0) {
                            lemonPressCount--
                        }
                        if (lemonPressCount == 0) {
                            currentStep = 3
                        }
                    }
                )
            }
            3 -> {
                LemonTextAndImage(
                    text = stringResource(id = R.string.glas_of_lemonade),
                    imageResId = R.drawable.lemon_drink,
                    onClick = {
                        currentStep = 4
                    }
                )
            }
            4 -> {
                LemonTextAndImage(
                    text = stringResource(id = R.string.empty_glass),
                    imageResId = R.drawable.lemon_restart,
                    onClick = {
                        currentStep = 1
                    }
                )
            }
        }
    }
}

@Composable
fun LemonTextAndImage(text: String, imageResId: Int, onClick: () -> Unit) {

    Column(

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .clickable { onClick() }
    ) {

        Text(
            text = text,
            fontSize = 18.sp,
            modifier = Modifier.padding(16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = null,
            modifier = Modifier
                .size(128.dp)
                .border(BorderStroke(2.dp, Color(105, 205, 216)), RoundedCornerShape(4.dp))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonApp()
    }
}
