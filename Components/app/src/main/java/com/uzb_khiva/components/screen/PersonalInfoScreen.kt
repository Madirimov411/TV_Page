package com.uzb_khiva.components.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uzb_khiva.components.views.PersonalInfoItem
import com.uzb_khiva.components.views.ButtonCard

@Composable
fun PersonalInfoScreen() {

    val context = LocalContext.current

    var phoneNumber by remember {
        mutableStateOf("")
    }

    var firstName by remember {
        mutableStateOf("")
    }

    var lastName by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var confirmPassword by remember {
        mutableStateOf("")
    }

    var region by remember {
        mutableStateOf("")
    }

    var city by remember {
        mutableStateOf("")
    }

    var address by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colorScheme.background)
            .padding(15.dp)
    ) {

        Text(
            text = "Foydalanuvchi ma'lumotlari",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        )


        /* TODO: TELEFON RAQAMI */
        PersonalInfoItem(
            modifier = Modifier.padding(top = 16.dp),
            title = "Telefon raqami",
            text = phoneNumber,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = true,
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Done
            ),
            onChange = {
                phoneNumber = it
            }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            /* TODO: ISM */
            PersonalInfoItem(
                modifier = Modifier.weight(1f),
                title = "Ism",
                text = firstName,
                onChange = {
                    firstName = it
                }
            )

            /* TODO: FAMILIYA */
            PersonalInfoItem(
                modifier = Modifier.weight(1f),
                title = "Familiya",
                text = lastName,
                onChange = {
                    lastName = it
                }
            )
        }

        Text(
            modifier = Modifier.padding(top = 18.dp),
            text = "Foydalanuvchi hisobi ma'lumotlari",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        )

        /* TODO: EMAIL */
        PersonalInfoItem(
            title = "E-mail",
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = true,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            ),
            text = email,
            onChange = {
                email = it
            }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            /* TODO: PAROL */
            PersonalInfoItem(
                modifier = Modifier.weight(1f),
                title = "Parol",
                text = password,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = true,
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                passwordVisible = false,
                onChange = {
                    password = it
                }
            )

            /* TODO: PAROLNI TASDIQLASH */
            PersonalInfoItem(
                modifier = Modifier.weight(1f),
                title = "Parolni takrorlang",
                text = confirmPassword,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = true,
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                passwordVisible = false,
                onChange = {
                    confirmPassword = it
                }
            )

        }

        Text(
            modifier = Modifier.padding(top = 18.dp),
            text = "Yetkazib berish manzili",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            /* TODO: VILOYAT */
            PersonalInfoItem(
                modifier = Modifier.weight(1f),
                title = "Viloyat",
                text = region,
                onChange = {
                    region = it
                }
            )

            /* TODO: SHAHAR */
            PersonalInfoItem(
                modifier = Modifier.weight(1f),
                title = "Shahar",
                text = city,
                onChange = {
                    city = it
                }
            )

        }

        /* TODO: TO'LIQ MANZIL */
        PersonalInfoItem(
            title = "To'liq manzilni kiriting",
            text = address,
            onChange = {
                address = it
            }
        )

        ButtonCard(
            text = "Saqlash",
            modifier = Modifier.padding(top = 32.dp),
            onClick = {
                Toast.makeText(context, "onClick", Toast.LENGTH_SHORT).show()
            }
        )



    }


}


@Preview
@Composable
fun PersonalInfoScreenPreview() {
    PersonalInfoScreen()
}