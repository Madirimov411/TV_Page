package com.uzb_khiva.components.views

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uzb_khiva.components.ui.theme.CustomGray

@Composable
fun PersonalInfoItem(
    modifier: Modifier = Modifier,
    title: String,
    placeholder: String = "",
    passwordVisible: Boolean = true,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    text: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    onChange: (String) -> Unit
) {

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            modifier = Modifier
                .padding(top = 8.dp, bottom = 8.dp),
            style = TextStyle(
                fontSize = 14.sp,
                color = CustomGray
            )
        )
        CustomTextField(
            modifier = Modifier
                .height(40.dp)
                .border(
                    width = 1.dp,
                    color = Color(0xFFBCBCBC),
                    shape = RoundedCornerShape(5.dp)
                ),
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            placeholderText = placeholder,
            passwordVisible = passwordVisible,
            textStyle = TextStyle(
                color = CustomGray,
                fontSize = 14.sp
            ),
            keyboardOptions = keyboardOptions,
            text = text
        ) {
            onChange(it)
        }
    }
}

@Preview
@Composable
fun PersonalInfoItemPreview() {
    var phone_number by remember {
        mutableStateOf("")
    }
    PersonalInfoItem(
        title = "Telefon raqami",
        text = phone_number,
        onChange = {
            phone_number = it
        }
    )
}