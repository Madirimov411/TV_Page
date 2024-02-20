package com.example.myapplication

data class MyScreenState(
    val text: String = "",
    val namesList: MutableList<String> = mutableListOf()
)