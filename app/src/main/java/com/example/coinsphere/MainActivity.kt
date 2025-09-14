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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest

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
    val context = LocalContext.current
    val request = ImageRequest.Builder(context)
        .data(url)
        .diskCachePolicy(CachePolicy.ENABLED) // cache en disco
        .networkCachePolicy(CachePolicy.ENABLED) // permite red
        .crossfade(true)
        .build()

    AsyncImage(
        model = request,
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
            Cripto("Bitcoin", "$109,797.37", "https://assets.coingecko.com/coins/images/1/large/bitcoin.png"),
            Cripto("Ethereum", "$4,321.21", "https://assets.coingecko.com/coins/images/279/large/ethereum.png?1595348880"),
            Cripto("Floki", "$0.00019", "https://assets.coingecko.com/coins/images/16746/large/PNG_image.png?1703749547"),
            Cripto("Baby Doge Coin", "$0.0000000012", "https://assets.coingecko.com/coins/images/16125/large/Baby_Doge.png?1623044048"),
            Cripto("Bonk", "$0.000022", "https://assets.coingecko.com/coins/images/28600/large/bonk.jpg?1696527587"),
            Cripto("Solana", "$201.8500", "https://assets.coingecko.com/coins/images/4128/large/solana.png?1640133422"),
            Cripto("USDC", "$0.9998", "https://assets.coingecko.com/coins/images/6319/large/usdc.png?1578389474"),
            Cripto("Dogecoin", "$0.1320", "https://assets.coingecko.com/coins/images/5/large/dogecoin.png?1547792256"),
            Cripto("TRON", "$0.3630", "https://assets.coingecko.com/coins/images/1094/large/tron-logo.png?1593458246"),
            Cripto("Shiba Inu", "$0.000024", "https://assets.coingecko.com/coins/images/11939/large/shiba.png?1596113034")
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

