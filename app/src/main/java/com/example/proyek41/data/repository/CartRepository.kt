package com.example.proyek41.data.repository

import com.example.proyek41.data.local.dao.CartDao
import com.example.proyek41.data.local.entity.CartItemEntity
import kotlinx.coroutines.flow.Flow

class CartRepository(private val cartDao: CartDao) {

    fun getCartItems(): Flow<List<CartItemEntity>> {
        return cartDao.getAllCartItems()
    }

    suspend fun insertCartItem(cartItem: CartItemEntity) {
        cartDao.insertCartItem(cartItem)
    }

    suspend fun updateCartItem(cartItem: CartItemEntity) {
        cartDao.updateCartItem(cartItem)
    }

    suspend fun deleteCartItem(cartItem: CartItemEntity) {
        cartDao.deleteCartItem(cartItem)
    }

    suspend fun clearCart() {
        cartDao.clearCart()
    }

    companion object {
        @Volatile
        private var instance: CartRepository? = null

        fun getInstance(cartDao: CartDao): CartRepository =
            instance ?: synchronized(this) {
                CartRepository(cartDao).apply { instance = this }
            }
    }
}
