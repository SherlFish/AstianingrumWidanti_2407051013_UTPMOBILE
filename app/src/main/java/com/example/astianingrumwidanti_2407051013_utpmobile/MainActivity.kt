package com.example.astianingrumwidanti_2407051013_utpmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
                var selectedAnime by remember { mutableStateOf<Anime?>(null) }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color(0xFF121212)
                ) { innerPadding ->
                    if (selectedAnime == null) {
                        TamanimeScreen(
                            modifier = Modifier.padding(innerPadding),
                            onAnimeClick = { animeYangDiklik ->
                                selectedAnime = animeYangDiklik
                            }
                        )
                    } else {
                        DetailAnimeScreen(
                            anime = selectedAnime!!,
                            modifier = Modifier.padding(innerPadding),
                            onBackClick = { selectedAnime = null }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DetailAnimeScreen(anime: Anime, modifier: Modifier = Modifier, onBackClick: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = anime.judul, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
        }

        Image(
            painter = painterResource(id = anime.imageResourceId),
            contentDescription = anime.judul,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )

        Column(modifier = Modifier.padding(24.dp)) {
            Text(text = anime.judul, color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatItem(label = "Studio", value = anime.studio)
                StatItem(label = "Status", value = anime.status)
                StatItem(label = "Resolusi", value = anime.resolusi)
                StatItem(label = "Rating", value = anime.rating)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(text = "Sinopsis", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = anime.sinopsis,
                color = Color.LightGray,
                fontSize = 14.sp,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { /* Biarin Kosong */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE50914)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "Play", tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Tonton Sekarang", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun StatItem(label: String, value: String) {
    Column {
        Text(text = label, color = Color.Gray, fontSize = 12.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = value, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
    }
}
@Composable
fun TamanimeScreen(modifier: Modifier = Modifier, onAnimeClick: (Anime) -> Unit) {
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
            FeaturedAnime(anime = animeList[1], onClick = { onAnimeClick(animeList[1]) })
        }

        item { AnimeCategoryRow(title = "Lanjutkan Menonton", animes = animeList, onAnimeClick = onAnimeClick) }
        item { AnimeCategoryRow(title = "Aksi Penuh Adrenalin", animes = animeList.shuffled(), onAnimeClick = onAnimeClick) }
        item { AnimeCategoryRow(title = "Top 10 Hari Ini", animes = animeList.shuffled(), onAnimeClick = onAnimeClick) }
    }
}

@Composable
fun FeaturedAnime(anime: Anime, onClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .clickable { onClick() }
        ) {
            Image(
                painter = painterResource(id = anime.imageResourceId),
                contentDescription = anime.judul,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = anime.judul, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(text = anime.genre, color = Color.LightGray, fontSize = 14.sp)
    }
}
@Composable
fun AnimeCategoryRow(title: String, animes: List<Anime>, onAnimeClick: (Anime) -> Unit) {
    Column {
        Text(
            text = title,
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(animes) { anime ->
                AnimeCard(anime = anime, onClick = { onAnimeClick(anime) })
            }
        }
    }
}

@Composable
fun AnimeCard(anime: Anime, onClick: () -> Unit) {
    Column(modifier = Modifier.width(120.dp)) {
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .clickable { onClick() }
        ) {
            Image(
                painter = painterResource(id = anime.imageResourceId),
                contentDescription = anime.judul,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(text = anime.judul, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, maxLines = 1)
        Text(text = anime.genre, color = Color.Gray, fontSize = 12.sp, maxLines = 1)
    }
}

@Preview(showBackground = true)
@Composable
fun TamanimePreview() {
    AstianingrumWidanti_2407051013_UTPMOBILETheme {
        Scaffold(containerColor = Color(0xFF121212)) { padding ->
            TamanimeScreen(modifier = Modifier.padding(padding), onAnimeClick = {})
        }
    }
}