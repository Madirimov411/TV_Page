package com.uzb_khiva.storeapp.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uzb_khiva.storeapp.data.remote.ApiClient
import com.uzb_khiva.storeapp.model.Product
import com.uzb_khiva.storeapp.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


//@HiltViewModel
class ProductViewModel : ViewModel() {

    private val apiService = ApiClient.apiService
    private val stateFlow = MutableStateFlow<Resource<List<Product>>>(Resource.Loading())

    init {
        fetchProduct()
    }

    private fun fetchProduct() {

        viewModelScope.launch {
            apiService.getProducts().enqueue(object : Callback<List<Product>> {
                override fun onResponse(
                    call: Call<List<Product>>,
                    response: Response<List<Product>>
                ) {
                    stateFlow.value = Resource.Success(response.body()!!)
                }

                override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                    stateFlow.value = Resource.Error(t)
                }
            })
        }

    }


    fun getStateFlow() = stateFlow
}