package com.uzb_khiva.storeapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.uzb_khiva.storeapp.ui.theme.StoreAppTheme
import com.uzb_khiva.storeapp.vm.ProductViewModel
import com.uzb_khiva.storeapp.vm.Resource
import kotlinx.coroutines.launch
@SuppressLint("CoroutineCreationDuringComposition")
class MainActivity : ComponentActivity() {

    private val productViewModel: ProductViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StoreAppTheme {
                val scope = rememberCoroutineScope()
                var text by remember {
                    mutableStateOf("")
                }

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = text)
                    scope.launch {
                        productViewModel.getStateFlow()
                            .collect {
                                when (it) {
                                    is Resource.Loading -> {
                                        text = "Loading"
                                    }

                                    is Resource.Success -> {
                                        Log.d("store_succes", "Success ${it.data}")
                                    }

                                    is Resource.Error -> {
                                        Log.d("store_succes", "Error")
                                    }
                                }
                            }

                    }
                }

            }
        }
    }
}
