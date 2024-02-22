package com.vinayeapps.retrofit.data

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.vinayeapps.retrofit.data.model.Product
import com.vinayeapps.retrofit.network.Api
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.lang.Exception


/**
 * Created by vinaye on 2024-02-16.
 * Author: Vinay Sebastian
 */
class ProductRepositoryImpl(private val api: Api) : ProductRepository {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getProductList(): Flow<Result<List<Product>>> {
        return flow {
            val productFromApi = try {
            api.getProductList()
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error loading products"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error loading products"))
                return@flow
            }  catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Error(message = "Error loading products"))
                return@flow
            }

            emit(Result.Success(productFromApi.products))
        }

    }

}