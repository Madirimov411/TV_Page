package com.uzb_khiva.components.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uzb_khiva.components.R
import com.uzb_khiva.components.ui.theme.CustomGray
import com.uzb_khiva.components.views.CategoryViewSimilarCard
import com.uzb_khiva.components.views.ProductCard


@Composable
fun CategoryView() {

    var showDropdownMenu by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(10.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            //Bu yerga tanlangan kategoriya va shu
            //kategoriya bo'yicha tanlangan item yoziladi
            Text(
                text = "Noutboklar HP",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )

            //Qidiruvdan keyin chiqadigan tovarlar soni
            Text(
                modifier = Modifier.padding(start = 15.dp),
                text = "36",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = CustomGray
                )
            )

            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = "ta mahsulot",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = CustomGray
                )
            )

        }

        LazyRow(
            modifier = Modifier.padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(15) {
                CategoryViewSimilarCard(text = "HP Victus 15")
            }

        }

        Row(
            modifier = Modifier
                .height(64.dp)
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .clickable {
                        Toast
                            .makeText(context, "Filtr", Toast.LENGTH_SHORT)
                            .show()
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {

                Icon(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .size(24.dp),
                    painter = painterResource(id = R.drawable.ic_filtr),
                    contentDescription = "Filtrlash"
                )

                Text(
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                    text = "Filtr",
                    style = TextStyle(
                        fontSize = 16.sp
                    )
                )

            }

            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .clickable {
                        showDropdownMenu = !showDropdownMenu
                        Toast
                            .makeText(context, "Tartiblash", Toast.LENGTH_SHORT)
                            .show()
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {

                Icon(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .size(24.dp),
                    painter = painterResource(id = R.drawable.ic_sort),
                    contentDescription = "Tartiblash"
                )

                Text(
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                    text = "Tartiblash",
                    style = TextStyle(
                        fontSize = 16.sp
                    )
                )

                DropdownMenu(
                    modifier = Modifier.width(200.dp),
                    expanded = showDropdownMenu,
                    onDismissRequest = { showDropdownMenu = false }) {
                    DropdownMenuItem(
                        text = {
                            Text(text = "Sana bo'yicha", textAlign = TextAlign.Center)
                        },
                        onClick = {
                            showDropdownMenu = !showDropdownMenu
                            Toast.makeText(context, "Sana", Toast.LENGTH_SHORT).show()
                        }
                    )

                    DropdownMenuItem(
                        text = {
                            Text(text = "Narx bo'yicha", textAlign = TextAlign.Center)
                        },
                        onClick = {
                            showDropdownMenu = !showDropdownMenu
                            Toast.makeText(context, "Narx", Toast.LENGTH_SHORT).show()
                        }
                    )

                    DropdownMenuItem(
                        text = {
                            Text(text = "Sharhlar bo'yicha", textAlign = TextAlign.Center)
                        },
                        onClick = {
                            showDropdownMenu = !showDropdownMenu
                            Toast.makeText(context, "Sharhlar", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }


        }

        LazyVerticalGrid(
            modifier = Modifier
                .height(600.dp)
                .fillMaxHeight(),
            columns = GridCells.Fixed(2),
            content = {
                items(20) {
                    ProductCard(isNews = true, detailsPC = detailPC)
                }
            }
        )

    }

}

@Preview
@Composable
fun CategoryViewPreview() {
    CategoryView()
}