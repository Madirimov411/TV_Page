package com.uzb_khiva.quizapp_operatsiontizimlar.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uzb_khiva.quizapp_operatsiontizimlar.model.Test
import com.uzb_khiva.quizapp_operatsiontizimlar.utils.testlar

@Composable
fun TestScreen(
    modifier: Modifier = Modifier,
    testList: List<Test>
) {

    val selectIndex = remember {
        mutableStateOf(0)
    }

    val testIndex = remember {
        mutableStateOf(0)
    }


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .border(
                    width = 2.dp,
                    color = Color.Blue,
                    shape = RoundedCornerShape(15.dp)
                )
                .padding(horizontal = 15.dp, vertical = 10.dp),
            text = testList[testIndex.value].savol,
            style = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 16.sp
            )

        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .border(
                    width = 2.dp,
                    color = Color.Blue,
                    shape = RoundedCornerShape(15.dp)
                )
                .clickable(
                    onClick = {
                        selectIndex.value = 1
                    }
                )
                .padding(horizontal = 5.dp, vertical = 5.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selectIndex.value == 1,
                onClick = {
                    selectIndex.value = 1
                }
            )
            Text(
                modifier = Modifier.weight(1f),
                text = testList[testIndex.value].variant1,
                style = TextStyle(
                    fontSize = 18.sp
                )
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .border(
                    width = 2.dp,
                    color = Color.Blue,
                    shape = RoundedCornerShape(15.dp)
                )
                .clickable(
                    onClick = {
                        selectIndex.value = 2
                    }
                )
                .padding(horizontal = 5.dp, vertical = 5.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selectIndex.value == 2,
                onClick = {
                    selectIndex.value = 2
                }
            )
            Text(
                modifier = Modifier.weight(1f),
                text = testList[testIndex.value].variant2,
                style = TextStyle(
                    fontSize = 18.sp
                )
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .border(
                    width = 2.dp,
                    color = Color.Blue,
                    shape = RoundedCornerShape(15.dp)
                )
                .clickable(
                    onClick = {
                        selectIndex.value = 3
                    }
                )
                .padding(horizontal = 5.dp, vertical = 5.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selectIndex.value == 3,
                onClick = {
                    selectIndex.value = 3
                }
            )
            Text(
                modifier = Modifier.weight(1f),
                text = testList[testIndex.value].variant3,
                style = TextStyle(
                    fontSize = 18.sp
                )
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .border(
                    width = 2.dp,
                    color = Color.Blue,
                    shape = RoundedCornerShape(15.dp)
                )
                .clickable(
                    onClick = {
                        selectIndex.value = 4
                    }
                )
                .padding(horizontal = 5.dp, vertical = 5.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selectIndex.value == 4,
                onClick = {
                    selectIndex.value = 4
                }
            )
            Text(
                modifier = Modifier.weight(1f),
                text = testList[testIndex.value].variant4,
                style = TextStyle(
                    fontSize = 18.sp
                )
            )
        }


    }


}

@Preview
@Composable
fun TestScreenPreview() {
    TestScreen(testList = testlar)
}