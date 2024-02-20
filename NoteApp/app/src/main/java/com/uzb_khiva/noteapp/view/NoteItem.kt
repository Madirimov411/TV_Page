package com.uzb_khiva.noteapp.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uzb_khiva.noteapp.model.Note

@Composable
fun NoteItem(
    note: Note,
    onEditNote: (Note) -> Unit,
    onDeleteNote: (Note) -> Unit
) {

    val isRead = remember {
        mutableStateOf(false)
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = note.title,
                        modifier = Modifier.weight(1f),
                        style = TextStyle(
                            fontSize = 24.sp,
                            color = Color.Black
                        )
                    )

                    Icon(
                        modifier = Modifier
                            .width(34.dp)
                            .height(24.dp)
                            .clickable {
                                isRead.value = !isRead.value
                            },
                        imageVector = if (isRead.value) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowRight,
                        contentDescription = null,
                    )
                }

                if (isRead.value) {
                    Spacer(modifier = Modifier.height(7.dp))
                    Text(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        text = note.description,
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {


                        Box(
                            modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    color = Color.Blue,
                                    shape = RoundedCornerShape(5.dp)
                                )
                                .clickable {
                                    onEditNote(note)
                                }
                        ) {
                            Icon(
                                modifier = Modifier
                                    .padding(vertical = 5.dp, horizontal = 20.dp)
                                    .width(24.dp)
                                    .height(24.dp),
                                imageVector = Icons.Default.Edit,
                                contentDescription = null,
                                tint = Color.Blue
                            )
                        }

                        Spacer(modifier = Modifier.width(35.dp))

                        Box(
                            modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    color = Color.Red,
                                    shape = RoundedCornerShape(5.dp)
                                )
                                .clickable {
                                    onDeleteNote(note)
                                }
                        ) {
                            Icon(
                                modifier = Modifier
                                    .padding(vertical = 5.dp, horizontal = 20.dp)
                                    .width(24.dp)
                                    .height(24.dp),
                                imageVector = Icons.Default.Delete,
                                contentDescription = null,
                                tint = Color.Red
                            )
                        }

                    }

                }
            }
        }

    }


}

@Preview
@Composable
fun NoteItemPreview() {
    NoteItem(
        note = Note(id = "1", title = "Title", description = "Description"),
        onEditNote = {},
        onDeleteNote = {})
}