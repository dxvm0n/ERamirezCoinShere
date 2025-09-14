@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.coinsphere

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            .clip(RoundedCornerShape(6.dp)),
        placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
        error = painterResource(id = R.drawable.ic_launcher_foreground)
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
            Cripto("TestLogo", "$123", "https://cryptologos.cc/logos/tron-trx-logo.png?v=030")
        )
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(top = 20.dp, bottom = 24.dp)
    ) {
        item {
            Text(
                text = "Coin Sphere",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = TextMain
            )
            Spacer(Modifier.height(16.dp))
        }

        item {
            globalinfo("Global Market Cap", "$2.18T")
            Spacer(Modifier.height(12.dp))
            globalinfo("Fear & Greed", "Neutral (54)")
            Spacer(Modifier.height(12.dp))
            globalinfo("Altcoin Season", "No")
            Spacer(Modifier.height(20.dp))
        }

        item {
            Row(
                Modifier.fillMaxWidth().padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("#", color = TextDim, fontSize = 13.sp, modifier = Modifier.width(20.dp))
                Text("Name", color = TextDim, fontSize = 13.sp, modifier = Modifier.weight(1f))
                Text("Price", color = TextDim, fontSize = 13.sp, modifier = Modifier.width(140.dp),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Start)
            }

            HorizontalDivider(
                modifier = Modifier.padding(bottom = 10.dp),
                thickness = 0.7.dp,
                color = TextDim.copy(alpha = 0.5f)
            )
        }

        itemsIndexed(listaCrypto) { index, coin ->
            crypto(index + 1, coin)
            Spacer(Modifier.height(10.dp))
        }
    }
}

@Composable
fun globalinfo(titulo: String, valor: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Surface),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(Modifier.padding(15.dp)) {
            Text(titulo, color = TextDim, fontSize = 12.sp)
            Spacer(Modifier.height(8.dp))
            Text(valor, color = TextMain, fontSize = 22.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
fun crypto(posicion: Int, cripto: Cripto) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Surface),
        shape = RoundedCornerShape(15.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = posicion.toString(),
                color = TextDim,
                fontSize = 15.sp,
                modifier = Modifier.width(20.dp)
            )

            ImagenInternet(url = cripto.logoUrl)

            Spacer(Modifier.width(12.dp))

            Text(
                text = cripto.nombre,
                color = TextMain,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.weight(1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = cripto.precio,
                color = TextMain,
                fontSize = 15.sp,
                modifier = Modifier.width(120.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Start
            )
        }
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

