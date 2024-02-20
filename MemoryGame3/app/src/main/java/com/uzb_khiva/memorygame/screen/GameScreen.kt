package com.uzb_khiva.memorygame.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.uzb_khiva.memorygame.R
import com.uzb_khiva.memorygame.model.Cards
import com.uzb_khiva.memorygame.ui.theme.Background
import com.uzb_khiva.memorygame.view.CardItem
import com.uzb_khiva.memorygame.view.ItemPuzzle

val list = listOf(
    R.drawable.ic_lion,
    R.drawable.ic_delphin,
    R.drawable.ic_emoji,
    R.drawable.ic_globus,
    R.drawable.ic_kuchuk,
    R.drawable.ic_kungaboqar,
    R.drawable.ic_nishon,
    R.drawable.ic_olcha,
    R.drawable.ic_olov,
    R.drawable.ic_pianino,
    R.drawable.ic_qulf,
    R.drawable.ic_pitsa,
    R.drawable.ic_shlyapa,
    R.drawable.ic_sovga,
    R.drawable.ic_soyabon,
)


@Composable
fun GameScreen(
    navController: NavController
) {

    val context= LocalContext.current

    val count = remember {
        mutableStateOf(0)
    }

    val cardsArray = remember {
        mutableStateOf<List<Cards>>(listOf())
    }

    cardsArray.value = refreshList()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                count.value++
            }
            .background(Background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            CardItem(
                modifier = Modifier.height(40.dp),
                icon = Icons.Default.Star,
                count = "${count.value}"
            )

            Icon(
                imageVector = Icons.Default.ExitToApp,
                modifier = Modifier
                    .size(45.dp)
                    .clickable {
                        navController.navigate("home")
                    },
                contentDescription = "image",
            )
        }

        Spacer(modifier = Modifier.height(100.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(25.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

               ItemPuzzle(
                    visibility = cardsArray.value[0].delete,
                    img = if (cardsArray.value[0].onClick) cardsArray.value[0].selectImg else cardsArray.value[0].unSelectImg,
                    tag = "0",
                    onClick = { tag ->
                        cardsArray.value[tag.toInt()].onClick = true
                    }
                )

                ItemPuzzle(
                    visibility = cardsArray.value[1].delete,
                    img = if (cardsArray.value[1].onClick) cardsArray.value[1].selectImg else cardsArray.value[1].unSelectImg,
                    tag = "1",
                    onClick = { tag ->
                        cardsArray.value[tag.toInt()].onClick = true
                    }
                )

                ItemPuzzle(
                    visibility = cardsArray.value[2].delete,
                    img = if (cardsArray.value[2].onClick) cardsArray.value[2].selectImg else cardsArray.value[0].unSelectImg,
                    tag = "2",
                    onClick = { tag ->
                        cardsArray.value[tag.toInt()].onClick = true
                    }
                )

                ItemPuzzle(
                    visibility = cardsArray.value[3].delete,
                    img = if (cardsArray.value[3].onClick) cardsArray.value[3].selectImg else cardsArray.value[1].unSelectImg,
                    tag = "3",
                    onClick = { tag ->
                        cardsArray.value[tag.toInt()].onClick = true
                    }
                )


            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                ItemPuzzle(
                    visibility = cardsArray.value[4].delete,
                    img = if (cardsArray.value[4].onClick) cardsArray.value[4].selectImg else cardsArray.value[2].unSelectImg,
                    tag = "4",
                    onClick = { tag ->
                        cardsArray.value[tag.toInt()].onClick = true
                    }
                )


                ItemPuzzle(
                    visibility = cardsArray.value[5].delete,
                    img = if (cardsArray.value[5].onClick) cardsArray.value[5].selectImg else cardsArray.value[3].unSelectImg,
                    tag = "5",
                    onClick = { tag ->
                        cardsArray.value[tag.toInt()].onClick = true
                    }
                )

                ItemPuzzle(
                    visibility = cardsArray.value[6].delete,
                    img = if (cardsArray.value[6].onClick) cardsArray.value[6].selectImg else cardsArray.value[4].unSelectImg,
                    tag = "6",
                    onClick = { tag ->
                        cardsArray.value[tag.toInt()].onClick = true
                    }
                )

                ItemPuzzle(
                    visibility = cardsArray.value[7].delete,
                    img = if (cardsArray.value[7].onClick) cardsArray.value[7].selectImg else cardsArray.value[5].unSelectImg,
                    tag = "7",
                    onClick = { tag ->
                        cardsArray.value[tag.toInt()].onClick = true
                    }
                )

            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                ItemPuzzle(
                    visibility = cardsArray.value[8].delete,
                    img = if (cardsArray.value[8].onClick) cardsArray.value[8].selectImg else cardsArray.value[6].unSelectImg,
                    tag = "8",
                    onClick = { tag ->
                        cardsArray.value[tag.toInt()].onClick = true
                    }
                )

                ItemPuzzle(
                    visibility = cardsArray.value[9].delete,
                    img = if (cardsArray.value[9].onClick) cardsArray.value[9].selectImg else cardsArray.value[7].unSelectImg,
                    tag = "9",
                    onClick = { tag ->
                        cardsArray.value[tag.toInt()].onClick = true
                    }
                )

                ItemPuzzle(
                    visibility = cardsArray.value[10].delete,
                    img = if (cardsArray.value[10].onClick) cardsArray.value[10].selectImg else cardsArray.value[8].unSelectImg,
                    tag = "10",
                    onClick = { tag ->
                        cardsArray.value[tag.toInt()].onClick = true
                    }
                )

                ItemPuzzle(
                    visibility = cardsArray.value[11].delete,
                    img = if (cardsArray.value[11].onClick) cardsArray.value[11].selectImg else cardsArray.value[9].unSelectImg,
                    tag = "11",
                    onClick = { tag ->
                        cardsArray.value[tag.toInt()].onClick = true
                    }
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                ItemPuzzle(
                    visibility = cardsArray.value[12].delete,
                    img = if (cardsArray.value[12].onClick) cardsArray.value[12].selectImg else cardsArray.value[10].unSelectImg,
                    tag = "12",
                    onClick = { tag ->
                        cardsArray.value[tag.toInt()].onClick = true
                    }
                )

                ItemPuzzle(
                    visibility = cardsArray.value[13].delete,
                    img = if (cardsArray.value[13].onClick) cardsArray.value[13].selectImg else cardsArray.value[11].unSelectImg,
                    tag = "13",
                    onClick = { tag ->
                        cardsArray.value[tag.toInt()].onClick = true
                    }
                )

                ItemPuzzle(
                    visibility = cardsArray.value[14].delete,
                    img = if (cardsArray.value[14].onClick) cardsArray.value[14].selectImg else cardsArray.value[12].unSelectImg,
                    tag = "14",
                    onClick = { tag ->
                        cardsArray.value[tag.toInt()].onClick = true
                    }
                )

                ItemPuzzle(
                    visibility = cardsArray.value[15].delete,
                    img = if (cardsArray.value[15].onClick) cardsArray.value[15].selectImg else cardsArray.value[13].unSelectImg,
                    tag = "15",
                    onClick = { tag ->
                        cardsArray.value[tag.toInt()].onClick = true
                    }
                )
            }


        }

        Spacer(modifier = Modifier.height(100.dp))

    }


}

fun refreshList(): List<Cards> {
    val returnList = mutableListOf<Cards>()
    while (returnList.size < 16) {
        val random = list.random()
        if (returnList.any { it.selectImg == random }) {
            continue
        } else {
            returnList.add(
                Cards(
                    selectImg = random,
                    unSelectImg = R.drawable.ic_font,
                    onClick = true,
                    delete = true
                )
            )

            returnList.add(
                Cards(
                    selectImg = random,
                    unSelectImg = R.drawable.ic_font,
                    onClick = true,
                    delete = true
                )
            )
        }
    }
    return returnList.shuffled()

}

@Preview
@Composable
fun GameScreenPreview() {
    val navController = rememberNavController()
    GameScreen(navController)
}