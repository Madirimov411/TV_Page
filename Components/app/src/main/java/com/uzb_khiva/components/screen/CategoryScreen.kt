package com.uzb_khiva.components.screen

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uzb_khiva.components.R
import com.uzb_khiva.components.ui.theme.CustomGray
import com.uzb_khiva.components.ui.theme.Red600

data class Category(
    val id: Int,
    val icon: Int,
    val title: String,
)

data class CategoryItem(
    val category_id: Int,
    val title: String,
    val elements: List<String>
)

val categorys = listOf(
    Category(id = 1, icon = R.drawable.ic_maishiy_texnika, title = "Maishiy texnika"),
    Category(id = 2, icon = R.drawable.ic_mobile_phone, title = "Telefonlar"),
    Category(id = 3, icon = R.drawable.ic_smarttv, title = "Televizorlar"),
    Category(id = 4, icon = R.drawable.ic_laptop, title = "Noutbooklar"),
    Category(id = 5, icon = R.drawable.ic_game, title = "O'yin qurilmalari"),
)

val categoryItem = listOf(
    CategoryItem(
        category_id = 1,
        title = "Brend Bo'yicha",
        elements = listOf("1", "HP", "Asus", "Acer", "Apple", "MSI", "Dell", "Lenovo")
    ),
    CategoryItem(
        category_id = 1,
        title = "Brend Bo'yicha",
        elements = listOf("2", "HP", "Asus", "Acer", "Apple", "MSI", "Dell", "Lenovo")
    ),
    CategoryItem(
        category_id = 1,
        title = "Brend Bo'yicha",
        elements = listOf("3", "HP", "Asus", "Acer", "Apple", "MSI", "Dell", "Lenovo")
    ),
    CategoryItem(
        category_id = 1,
        title = "Brend Bo'yicha",
        elements = listOf("4", "HP", "Asus", "Acer", "Apple", "MSI", "Dell", "Lenovo")
    ),
    CategoryItem(
        category_id = 1,
        title = "Brend Bo'yicha",
        elements = listOf("5", "HP", "Asus", "Acer", "Apple", "MSI", "Dell", "Lenovo")
    )
)

const val EXPANSION_ANIMATION_DURATION = 300


@Composable
fun CategoryScreen() {


    var selectedIndex by remember {
        mutableIntStateOf(1)
    }

    Column(
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) {
        Row(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
                .height(45.dp)
                .background(Red600)
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = null,
                modifier = Modifier
                    .size(height = 29.dp, width = 154.dp)
            )

            Icon(imageVector = Icons.Default.Close, contentDescription = null, tint = Color.White)
        }

        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = "Kategoriyalar",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(categorys) { category ->
                Category(
                    category = category,
                    isSelected = selectedIndex == category.id,
                    index = selectedIndex,
                    onClick = { id ->
                        selectedIndex = id
                    }
                )
            }
        }
    }

}


@Composable
fun Category(
    category: Category,
    isSelected: Boolean,
    index: Int,
    onClick: (id: Int) -> Unit
) {

    val transition = updateTransition(targetState = isSelected, label = "transition")

    val iconRotationDeg by transition.animateFloat(label = "icon change") { state ->
        if (state) 90f
        else 0f
    }


    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    onClick(category.id)
                },
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Icon(
                modifier = Modifier
                    .width(34.dp)
                    .height(27.dp),
                painter = painterResource(id = category.icon),
                contentDescription = null,
                tint = if (isSelected) Red600 else Color(0xFF666363)
            )

            Text(
                text = category.title, modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp),
                style = TextStyle(
                    fontSize = 16.sp,
                    color = if (isSelected) Red600 else Color(0xFF666363)
                )
            )

            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "arrow",
                modifier = Modifier
                    .rotate(iconRotationDeg)
            )
        }

        CategoryItem(categoryItem = categoryItem[index - 1], selected = isSelected)

    }

}

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    categoryItem: CategoryItem,
    selected: Boolean,
) {

    val context = LocalContext.current

    val enterTransition = remember {
        expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(EXPANSION_ANIMATION_DURATION)
        ) + fadeIn(
            initialAlpha = .3f,
            animationSpec = tween(EXPANSION_ANIMATION_DURATION)
        )
    }

    val exitTransition = remember {
        shrinkVertically(
            shrinkTowards = Alignment.Top,
            animationSpec = tween(EXPANSION_ANIMATION_DURATION)
        ) + fadeOut(animationSpec = tween(EXPANSION_ANIMATION_DURATION))
    }

    AnimatedVisibility(
        visible = selected,
        enter = enterTransition,
        exit = exitTransition
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(start = 36.dp),
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Text(
                modifier = Modifier.padding(top = 18.dp),
                text = categoryItem.title, style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )

            categoryItem.elements.forEachIndexed { _, element ->

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(32.dp)
                        .wrapContentHeight(align = Alignment.CenterVertically)
                        .clickable {
                            Toast
                                .makeText(context, element, Toast.LENGTH_SHORT)
                                .show()

                        },
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = CustomGray
                    ),
                    text = element,
                )

            }
        }
    }

}

@Preview
@Composable
fun CategoryScreenPreview() {
    CategoryScreen()

    //CategoryItem(categoryItem = categoryItem[0], selected = true)

    /*var s by remember {
        mutableStateOf(true)
    }
    Category(category = categorys[0], inSelect = s, index = 1, onClick = { s = !s })*/
}