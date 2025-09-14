@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.coinsphere

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

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
                        .padding(16.dp)
                ) {
                    Home()
                }
            }
        }
    }
}

data class Cripto(val nombre: String, val precio: String, val logoUrl: String)

@Composable
fun ImagenInternet(url: String, tamano: Dp = 22.dp) {
    AsyncImage(
        model = url,
        contentDescription = null,
        modifier = Modifier
            .size(tamano)
            .clip(RoundedCornerShape(6.dp))
    )
}

@Composable
fun Home() {
    val listaCrypto = remember {
        listOf(
            Cripto("Bitcoin", "$109,797.37", "https://cryptologos.cc/logos/bitcoin-btc-logo.png?v=030"),
            Cripto("Ethereum", "$4,321.21", "https://cryptologos.cc/logos/ethereum-eth-logo.png?v=030"),
            Cripto("Pepe", "$0,00001173", "https://cryptologos.cc/logos/pepe-pepe-logo.png?v=030"),
            Cripto("Monero", "$287,30", "https://cryptologos.cc/logos/monero-xmr-logo.png?v=030"),
            Cripto("BNB", "$845.0000", "https://cryptologos.cc/logos/bnb-bnb-logo.png?v=030"),
            Cripto("Solana", "$201.8500", "https://cryptologos.cc/logos/solana-sol-logo.png?v=030"),
            Cripto("USDC", "$0.9998", "https://cryptologos.cc/logos/usd-coin-usdc-logo.png?v=030"),
            Cripto("Dogecoin", "$0.1320", "https://cryptologos.cc/logos/dogecoin-doge-logo.png?v=030"),
            Cripto("TRON", "$0.3630", "https://cryptologos.cc/logos/tron-trx-logo.png?v=030"),
        )
    }
}

@Preview(
    name = "Vista Home",
    showBackground = true,
    backgroundColor = 0xFF0B1020
)
@Composable
fun PreviewHome() {
    MaterialTheme {
        Box(
            Modifier
                .fillMaxSize()
                .background(Background)
                .padding(15.dp)
        ) {
            Home()
        }
    }
}

