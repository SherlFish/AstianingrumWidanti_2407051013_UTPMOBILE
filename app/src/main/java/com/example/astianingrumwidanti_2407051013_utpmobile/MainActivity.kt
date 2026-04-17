package com.example.astianingrumwidanti_2407051013_utpmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.astianingrumwidanti_2407051013_utpmobile.ui.theme.AstianingrumWidanti_2407051013_UTPMOBILETheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AstianingrumWidanti_2407051013_UTPMOBILETheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color(0xFF121212)
                ) { innerPadding ->
                    TamanimeScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun TamanimeScreen(modifier: Modifier = Modifier) {
    val animeList = AnimeSource.dummyAnime

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            Text(
                text = "TAMANIME",
                color = Color.Red,
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        item {
            FeaturedAnime(anime = animeList[1])
        }
        item {
            AnimeCategoryRow(title = "Lanjutkan Menonton", animes = animeList)
        }
        item {
            AnimeCategoryRow(title = "Aksi Penuh Adrenalin", animes = animeList.shuffled())
        }
        item {
            AnimeCategoryRow(title = "Top 10 Hari Ini", animes = animeList.shuffled())
        }
        item {
            AnimeCategoryRow(title = "Drama Penguras Air Mata", animes = animeList.shuffled())
        }
    }
}

@Composable
fun FeaturedAnime(anime: Anime) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
        ) {
            Image(
                painter = painterResource(id = anime.imageResourceId),
                contentDescription = anime.judul,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = anime.judul,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = anime.genre,
            color = Color.LightGray,
            fontSize = 14.sp
        )
    }
}
@Composable
fun AnimeCategoryRow(title: String, animes: List<Anime>) {
    Column {
        Text(
            text = title,
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(animes) { anime ->
                AnimeCard(anime = anime)
            }
        }
    }
}
@Composable
fun AnimeCard(anime: Anime) {
    Column(modifier = Modifier.width(120.dp)) {
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
        ) {
            Image(
                painter = painterResource(id = anime.imageResourceId),
                contentDescription = anime.judul,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = anime.judul,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1
        )
        Text(
            text = anime.genre,
            color = Color.Gray,
            fontSize = 12.sp,
            maxLines = 1
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TamanimePreview() {
    AstianingrumWidanti_2407051013_UTPMOBILETheme {
        Scaffold(containerColor = Color(0xFF121212)) { padding ->
            TamanimeScreen(modifier = Modifier.padding(padding))
        }
    }
}