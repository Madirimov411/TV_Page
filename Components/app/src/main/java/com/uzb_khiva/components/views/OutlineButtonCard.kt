package com.uzb_khiva.components.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uzb_khiva.components.R
import com.uzb_khiva.components.ui.theme.Red600

@Composable
fun OutlineButtonCard(
    modifier: Modifier = Modifier,
    image: Int? = null,
    text: String,
    onClick: () -> Unit
) {

    Box(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick.invoke() }){
        TextButton(
            shape = RoundedCornerShape(0.dp),
            modifier = modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = Red600
                ),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color.Transparent
            ),
            onClick = {}
        ) {

            if (image != null) {
                Image(
                    painter = painterResource(id = R.drawable.ic_shopping),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(10.dp))

            }

            Text(
                text = text,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(500),
                    color = Color.Black,
                )
            )

        }
    }

}

@Preview
@Composable
fun OutlineButtonCardPreview() {
    OutlineButtonCard(text = "Bir marta bosish orqali sotib oling") {}
}