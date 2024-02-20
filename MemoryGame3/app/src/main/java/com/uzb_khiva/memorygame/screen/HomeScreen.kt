package com.uzb_khiva.memorygame.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.uzb_khiva.memorygame.R
import com.uzb_khiva.memorygame.ui.theme.Background

@Composable
fun HomeScreen(
    navController: NavHostController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.toolbar_background),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Card(
                modifier = Modifier
                    .width(300.dp)
                    .height(64.dp),
            ) {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Memory Game",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.SemiBold
                        ),
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(5f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .padding(15.dp)
                    .width(200.dp)
                    .height(60.dp),
                onClick = {
                    navController.navigate("game")
                }) {

                Text(
                    text = "Boshlash",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 20.sp
                    )
                )

            }
            Button(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .padding(15.dp)
                    .width(200.dp)
                    .height(60.dp),
                onClick = { }
            ) {
                Text(
                    text = "Chiqish",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 20.sp
                    )
                )
            }

        }

    }

}

@Preview
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}