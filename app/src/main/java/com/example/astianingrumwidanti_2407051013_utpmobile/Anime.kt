package com.example.astianingrumwidanti_2407051013_utpmobile

import androidx.annotation.DrawableRes

data class Anime(
    val judul: String,
    val genre: String,
    @DrawableRes val imageResourceId: Int,
    val studio: String = "Madhouse",
    val status: String = "Ongoing",
    val resolusi: String = "Up to 4K",
    val rating: String = "9.5 / 10",
    val sinopsis: String = "Perjalanan panjang seorang Mage peri abadi bernama Frieren setelah party pahlawannya mengalahkan raja iblis bertahun-tahun yang lalu. Ia mulai memahami apa arti kehidupan sebentar manusia."
)