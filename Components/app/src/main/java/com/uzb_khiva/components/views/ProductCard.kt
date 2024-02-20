package com.uzb_khiva.components.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uzb_khiva.components.R
import com.uzb_khiva.components.screen.PCDetail
import com.uzb_khiva.components.screen.detailPC

@Composable
fun ProductCard(
    isNews: Boolean,
    detailsPC: PCDetail
) {

    var selectedIcon by remember {
        mutableStateOf(true)
    }

    val imageStar = listOf(
        R.drawable.ic_star,
        R.drawable.ic_star,
        R.drawable.ic_star,
        R.drawable.ic_star,
        R.drawable.ic_star_outline,
    )


    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()

            .height(240.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 9.dp)
                .shadow(
                    elevation = 10.dp,
                    spotColor = Color(0x1A000000),
                    ambientColor = Color(0x1A000000)
                )
                .background(color = Color(0xFFFFFFFF)),
            horizontalAlignment = Alignment.End
        ) {
            Image(
                modifier = Modifier
                    .padding(top = 12.dp, end = 12.dp)
                    .clickable {
                        selectedIcon = !selectedIcon
                    },
                painter = painterResource(id = if (selectedIcon) R.drawable.ic_favorite_border else R.drawable.ic_favorite),
                contentDescription = "Favorite",
                alignment = Alignment.Center
            )

            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .height(87.dp),
                painter = painterResource(id = detailsPC.bigImage[0]),
                contentDescription = null
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 11.dp, end = 11.dp, top = 14.dp, bottom = 6.dp),
                maxLines = 2,
                style = TextStyle(
                    fontSize = 12.sp,
                ),
                text = "${detailPC.brand} ${detailPC.model} ${detailPC.gpu} / ${detailPC.processor} / ${detailPC.ram} RAM"
            )


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 11.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row {
                    imageStar.forEachIndexed { _, image ->
                        Image(modifier = Modifier.width(10.dp).height(10.dp), painter = painterResource(id = image), contentDescription = null)
                    }
                }

                Text(
                    modifier = Modifier.padding(start = 6.dp),
                    text = "31 ta sharh",
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF777777)
                    )
                )
            }

            Text(
                text = "${detailsPC.price} UZS",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 11.dp, top = 14.dp),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF3C3C3C),
                )
            )

        }



        Text(
            text = if (isNews) "new" else "",
            modifier = Modifier
                .height(19.dp)
                .background(color = Color(0xFFE81D1C))
                .padding(horizontal = 8.dp),
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFFFFFFFF),

                )
        )


    }

}

@Preview
@Composable
fun ArticleCardPreview() {

    val isNews = true

    ProductCard(isNews = isNews, detailsPC = detailPC)
}