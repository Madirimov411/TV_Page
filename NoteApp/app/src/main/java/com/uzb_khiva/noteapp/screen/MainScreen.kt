package com.uzb_khiva.noteapp.screen

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uzb_khiva.noteapp.data.remote.ApiClient
import com.uzb_khiva.noteapp.model.Note
import com.uzb_khiva.noteapp.view.AlertDialogItem
import com.uzb_khiva.noteapp.view.NoteItem
import kotlinx.coroutines.launch
import androidx.compose.material3.AlertDialog

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MainScreen() {

    val scope = rememberCoroutineScope()

    val isEdit = remember {
        mutableStateOf(false)
    }

    var note = remember {
        mutableStateOf(Note("", "", ""))
    }

    val context = LocalContext.current

    var showDialog by remember { mutableStateOf(false) }

    var deleteDialog by remember { mutableStateOf(false) }

    var notes by remember { mutableStateOf<List<Note>?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    scope.launch {
        val response = ApiClient.apiService.getAllNote()
        if (response.isSuccessful) {
            notes = response.body()
            isLoading = false
        } else {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "My Notes",
                style = TextStyle(fontSize = 24.sp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            if (notes != null) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(notes!!.count()) { position ->

                        NoteItem(
                            note = notes!![position],
                            onEditNote = {
                                isEdit.value = true
                                note.value = it
                                showDialog = true
                            },
                            onDeleteNote = {
                                note.value = it
                                deleteDialog = true
                            }
                        )

                    }
                }
            } else {
                Text(text = "Loading...")
            }
        }

        FloatingActionButton(
            onClick = {
                showDialog = !showDialog
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add Note")

        }

        if (showDialog) {
            AlertDialog(
                modifier = Modifier.border(
                    width = 0.dp,
                    color = Color.Transparent,
                    shape = RoundedCornerShape(10.dp)
                ),
                onDismissRequest = { },
            ) {
                AlertDialogItem(
                    id = notes!![notes!!.count() - 1].id,
                    isEdit = isEdit.value,
                    note = note.value,
                    onAddClick = {
                        scope.launch {
                            if (isEdit.value) {
                                editNote(it, context)
                            } else {
                                addNote(it, context)
                            }
                            isEdit.value = false
                            note.value = Note("", "", "")
                        }
                        showDialog = false

                    }
                ) {
                    showDialog = false
                    isEdit.value = false
                    note.value = Note("", "", "")
                }
            }
        }

        if (deleteDialog) {
            AlertDialog(
                onDismissRequest = {
                    deleteDialog = false
                },
                title = {
                    Text(text = "Title")
                },
                text = {
                    Text(text = "Turned on by default")
                },
                confirmButton = {
                    TextButton(
                        onClick = {

                            scope.launch {
                                deleteNote(note.value.id, context)
                                note.value = Note("", "", "")
                                deleteDialog = false
                            }
                        }
                    ) {
                        Text("Yes")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            deleteDialog = false
                        }
                    ) {
                        Text("No")
                    }
                }
            )
        }


    }


}

suspend fun addNote(note: Note, context: Context) {

    val response = ApiClient.apiService.addNote(note)
    if (response.isSuccessful) {
        Toast.makeText(context, "Note added", Toast.LENGTH_SHORT).show()
    } else {
        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
    }

}

suspend fun editNote(note: Note, context: Context) {

    val response = ApiClient.apiService.updateNote(note.id, note)
    if (response.isSuccessful) {
        Toast.makeText(context, "Note updated", Toast.LENGTH_SHORT).show()
    } else {
        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
    }

}


suspend fun deleteNote(id: String, context: Context) {
    val response =
        ApiClient.apiService.deleteNote(id)
    if (response.isSuccessful) {
        Toast.makeText(
            context,
            "Note deleted",
            Toast.LENGTH_SHORT
        ).show()
    } else {
        Toast.makeText(
            context,
            "$response",
            Toast.LENGTH_SHORT
        ).show()
    }

}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}