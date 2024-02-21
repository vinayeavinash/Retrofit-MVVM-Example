package com.vinayeapps.retrofit.data

import com.vinayeapps.retrofit.data.model.Product
import java.util.concurrent.Flow


/**
 * Created by vinay on 2024-02-16.
 * Author: Vinay Sebastian
 */

interface ProductRepository {
    suspend fun getProductList(): kotlinx.coroutines.flow.Flow<Result<List<Product>>>

}