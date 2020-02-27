package com.example.tienda.localrepositorio

import com.example.tienda.Clases.Producto
import io.reactivex.Completable
import io.reactivex.Flowable

interface ProductoRepository {
    fun getProducto(): Flowable<List<Producto>>
    fun insertProducto(product:Producto): Completable
}