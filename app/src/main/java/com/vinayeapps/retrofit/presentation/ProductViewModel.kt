package com.vinayeapps.retrofit.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinayeapps.retrofit.data.ProductRepository
import com.vinayeapps.retrofit.data.Result
import com.vinayeapps.retrofit.data.model.Product
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


/**
 * Created by vinaye on 2024-02-21.
 * Author: Vinay Sebastian
 */
class ProductViewModel(private val productRepository: ProductRepository) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products = _products.asStateFlow()

    private val _showToastChannel = Channel<Boolean>()
    val showToastChannel = _showToastChannel.receiveAsFlow()


    init {
        viewModelScope.launch {
            productRepository.getProductList().collectLatest { result ->

                when(result){
                    is Result.Error ->
                    {
                        _showToastChannel.send(true)
                    }
                    is Result.Success -> {

                        result.data?.let {
                            products ->
                            _products.update {products  }
                        }
                    }
                }
            }
        }
    }
}