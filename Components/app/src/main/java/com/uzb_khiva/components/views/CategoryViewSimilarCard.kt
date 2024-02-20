package com.uzb_khiva.components.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uzb_khiva.components.ui.theme.CustomGray

@Composable
fun CategoryViewSimilarCard(
    text: String
) {

    Card(
        modifier = Modifier
            .widthIn(min = 94.dp)
            .height(32.dp),
        shape = RoundedCornerShape(7.dp)
    ) {

        Box(
            modifier = Modifier.background(Color(0xFFF0F0F0)),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 6.dp)
                    .background(Color.Transparent),
                text = text,
                style = TextStyle(
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
            )
        }


    }


}

@Preview
@Composable
fun CategoryViewSimilarCardPreview() {
    CategoryViewSimilarCard(text = "HP Victus 15")
}