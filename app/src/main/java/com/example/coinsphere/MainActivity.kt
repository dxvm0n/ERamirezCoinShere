package com.example.coinsphere

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

val Background = Color(0xFF0B1020)
val Surface    = Color(0xFF151B2E)
val TextMain   = Color(0xFFE8ECF8)
val TextDim    = Color(0xFF9AA3B2)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(Background)
                ) {
                    Home()
                }
            }
        }
    }
}

@Composable
fun Home() {
}

@Preview(showBackground = true, backgroundColor = 0xFF0B1020)
@Composable
fun PreviewHome() {
    MaterialTheme {
        Box(Modifier.fillMaxSize().background(Background)) {
            Home()
        }
    }
}
