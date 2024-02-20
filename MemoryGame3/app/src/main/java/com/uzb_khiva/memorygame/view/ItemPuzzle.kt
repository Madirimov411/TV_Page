package com.uzb_khiva.memorygame.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uzb_khiva.memorygame.R
import com.uzb_khiva.memorygame.utils.visibility

@Composable
fun ItemPuzzle(
    modifier: Modifier = Modifier,
    visibility: Boolean = true,
    img: Int,
    tag: String,
    onClick: (String) -> Unit
) {
    Card(
        modifier = modifier
            .visibility(visible = visibility)
            .width(64.dp)
            .height(64.dp)
            .clickable {
                onClick(tag)
            },
        shape = RoundedCornerShape(5.dp)
    ) {
        Image(
            painter = painterResource(id = img),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .padding(if(img == R.drawable.ic_font) 0.dp else 10.dp)
        )
    }

}

@Preview
@Composable
fun ItemPuzzlePreview() {
    //ItemPuzzle(img = R.drawable.ic_delphin, tag = "1") {}
    ItemPuzzle(img = R.drawable.ic_font, tag = "1") {}
}