@file:OptIn(ExperimentalMaterial3Api::class)

package com.uzb_khiva.noteapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AlertDialogScreen() {
    var showDialog by remember { mutableStateOf(false) }
    var textValue by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(onClick = { showDialog = true }) {
            Text("Show Dialog")
        }

        if (showDialog) {
            AlertDialog(
                modifier = Modifier.background(Color.White),
                onDismissRequest = {
                    // Handle dismiss
                    showDialog = false
                },
                content = {
                    Column {
                        TextField(
                            value = textValue,
                            onValueChange = {
                                textValue = it
                            },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    // Handle Done action
                                    showDialog = false
                                }
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.End
                        ) {
                            TextButton(
                                onClick = {
                                    // Handle Cancel button
                                    showDialog = false
                                }
                            ) {
                                Text("Cancel")
                            }

                            TextButton(
                                onClick = {
                                    // Handle Done button
                                    showDialog = false
                                }
                            ) {
                                Text("Done")
                            }
                        }
                    }

                }
            )
        }
    }
}

@Preview
@Composable
fun AlertDialogScreenPreview() {
    AlertDialogScreen()
}