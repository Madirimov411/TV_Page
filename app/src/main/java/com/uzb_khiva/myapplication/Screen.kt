@file:OptIn(ExperimentalCoilApi::class)

package com.uzb_khiva.myapplication

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEachIndexed
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.material3.Button
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.uzb_khiva.myapplication.data.remote.ApiClient
import com.uzb_khiva.myapplication.model.Movie
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Screen() {

    var imageUrl by remember { mutableStateOf("") }

    val movie = remember { mutableStateOf<Movie?>(null) }

    val scope = rememberCoroutineScope()

    val isLoading = remember { mutableStateOf(true) }

    val context = LocalContext.current

    scope.launch {
        ApiClient.getApiService().getMovieById(1022796).enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    Log.d("responceTAG", "onCreate: ${response.body()}")
                    movie.value = response.body()
                    isLoading.value = false
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.d("responceTAG", "onCreate: ${t.message}")
            }
        })
    }

    val gradientBrush = Brush.horizontalGradient(
        colors = listOf(
            Color.Black,
            Color(0x00FFFFFF)
        ),
        startX = 600f,
        endX = 800f,
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        if (!isLoading.value) {
            imageUrl = "https://image.tmdb.org/t/p/w500${movie.value?.backdrop_path}"
        }

        Image(
            painter = rememberImagePainter(data = imageUrl),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight(.6f)
                .fillMaxWidth(.6f)
                .align(Alignment.TopEnd)
        )

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .background(brush = gradientBrush)
                .fillMaxWidth(.6f)
                .padding(start = 30.dp, top = 25.dp, end = 60.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = movie.value?.title ?: "Title",
                style = TextStyle(
                    fontSize = 36.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    fontStyle = MaterialTheme.typography.headlineSmall.fontStyle
                )
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Text(
                    text = movie.value?.vote_average.toString(),
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = Color.Green
                    )
                )

                Text(
                    text = movie.value?.release_date.toString().substring(0, 4),
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = Color.White
                    )
                )
            }

            Text(
                text = movie.value?.overview ?: "Description",
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                movie.value?.genres?.fastForEachIndexed { _, genre ->
                    Text(
                        text = genre.name,
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    )
                }
            }

            TvLazyRow{

            }


        }


    }

}

@Preview
@Composable
fun ScreenPreview() {
    Screen()
}