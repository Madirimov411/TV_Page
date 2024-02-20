package com.uzb_khiva.components.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uzb_khiva.components.R

@Composable
fun ButtonCard(
    modifier: Modifier = Modifier,
    image: Int? = null,
    text: String,
    onClick: () -> Unit
) {


    TextButton(
        shape = RoundedCornerShape(0.dp),
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFFE81D1C)),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = Color.Transparent
        ),
        onClick = onClick
    ) {

        if (image != null) {
            Image(
                painter = painterResource(id = R.drawable.ic_shopping),
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(10.dp))

        }

        Text(text = text)
    }


}

@Preview
@Composable
fun ButtonCardPreview() {
    ButtonCard(image = R.drawable.ic_shopping, text = "Savatga qo'shish") {}
}