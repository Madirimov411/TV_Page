package com.uzb_khiva.components.views

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uzb_khiva.components.R
import com.uzb_khiva.components.screen.PCDetail

@SuppressLint("RememberReturnType")
@Composable
fun TitleAndPreviewImages(
    detailPC: PCDetail
) {

    val imageStar = listOf(
        R.drawable.ic_star,
        R.drawable.ic_star,
        R.drawable.ic_star,
        R.drawable.ic_star,
        R.drawable.ic_star_outline,
    )

    val context = LocalContext.current

    var selectImageIndex by remember {
        mutableIntStateOf(0)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Text(
            text = "${detailPC.brand} ${detailPC.model} ${detailPC.gpu} / ${detailPC.processor} / ${detailPC.ram} RAM",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF3C3C3C),
            )
        )

        Row(
            modifier = Modifier.padding(top = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row {
                imageStar.forEachIndexed { _, image ->
                    Image(painter = painterResource(id = image), contentDescription = null)
                }
            }

            Text(
                modifier = Modifier.padding(start = 14.dp),
                text = "4 ta sharh",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF777777)
                )
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(315.dp)
                .padding(start = 6.dp, end = 6.dp, top = 25.dp, bottom = 16.dp)
                .border(
                    width = 0.5.dp,
                    color = Color(0xFFD1D1D1),
                    shape = RoundedCornerShape(size = 10.dp)
                )
                .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 10.dp))
                .padding(start = 32.dp, top = 32.dp, end = 32.dp, bottom = 32.dp)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .width(253.dp)
                    .height(213.dp),
                painter = painterResource(id = detailPC.bigImage[selectImageIndex]),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            itemsIndexed(detailPC.smallImages) { index, image ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .width(64.dp)
                        .height(70.dp)
                        .clickable(
                            onClick = {
                                selectImageIndex = index
                            },
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        )
                        .border(
                            width = 1.dp,
                            color = if (index == selectImageIndex) Color(0xFFE81D1C) else Color(
                                0xFFD1D1D1
                            ),
                            shape = RoundedCornerShape(size = 5.dp)
                        ),

                    ) {
                    Image(
                        painter = painterResource(id = image),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .background(Color.Transparent)
                            .width(60.dp)
                            .height(45.dp)
                            .align(Alignment.Center),
                    )
                }
            }
        }

    }
}

@Preview
@Composable
fun MainDetailPreview() {
    val detailPC: PCDetail = PCDetail(
        id = 1,
        brand = "HP",
        model = "Victus 15",
        description = "144 Gts yangilanish tezligiga ega 16,1 dyuymli ekran (yuqori konfiguratsiyalarda QHD va 165 Gts piksellar soni) Nvidia'dan RTX 30 seriyali grafikalar va Intel'dan 11-avlod protsessorlari yoki AMD Ryzen 7 5800H opsiyalari. Raqamlar paneli va maxsus o'q tugmalari bilan to'liq o'lchamli klaviatura Kattaroq sensorli panel",
        price = "6800000",
        gpu = "RTX 3050",
        processor = "i5-12400F",
        ram = "8 GB",
        diagonal_ekran = "16*",
        xotira = "512 GB",
        ekran_chastotasi = "144 HZ",
        operatsion_tizim = "Windows 11 Home",
        smallImages = listOf(
            R.drawable.pc_1,
            R.drawable.pc_2,
            R.drawable.pc_3,
        ),
        bigImage = listOf(
            R.drawable.pc_1,
            R.drawable.pc_2,
            R.drawable.pc_3,
        )
    )
    TitleAndPreviewImages(detailPC)
}