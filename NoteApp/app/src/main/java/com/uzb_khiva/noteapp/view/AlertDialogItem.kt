package com.uzb_khiva.noteapp.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uzb_khiva.noteapp.model.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDialogItem(
    modifier: Modifier = Modifier,
    id: String,
    isEdit: Boolean = false,
    note: Note = Note("", "", ""),
    onAddClick: (Note) -> Unit,
    onCancelClick: () -> Unit
) {

    var title1 by remember { mutableStateOf(note.title) }
    var desc by remember { mutableStateOf(note.description) }

    Surface(
        modifier = Modifier,
        shape = RoundedCornerShape(15.dp)
    ) {
        Column(
            modifier = modifier
                .padding(horizontal = 20.dp, vertical = 15.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = if (isEdit) "Edit Note" else "Add Note",
                style = TextStyle(fontSize = 24.sp)
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = title1,
                onValueChange = { title1 = it },
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W400,
                    color = Color.Black
                ),
                label = {
                    Text(text = "Title")
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Blue,
                    unfocusedBorderColor = Color.Black
                )
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 300.dp),
                singleLine = false,
                value = desc,
                label = {
                    Text(text = "Description")
                },
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Gray
                ),
                onValueChange = { desc = it },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Blue,
                    unfocusedBorderColor = Color.Black
                )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {

                OutlinedButton(
                    onClick = {
                        onCancelClick()
                    },
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.White,
                        containerColor = Color.Red
                    )
                ) {
                    Text(text = "Cancel")
                }
                Spacer(modifier = Modifier.width(15.dp))

                Button(
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.White,
                        containerColor = Color.Blue
                    ),
                    onClick = {
                        if (isEdit) {
                            onAddClick(Note(note.id, title1, desc))
                        } else {
                            onAddClick(Note("${id.toInt() + 1}", title1, desc))
                        }
                    }
                ) {
                    Text(text = if (isEdit) "Edit" else "Add")
                }

            }


        }
    }

}

@Preview
@Composable
fun AlertDialogItemPreview() {
    AlertDialogItem(
        id = "1",
        onAddClick = {},
        onCancelClick = {}
    )
}