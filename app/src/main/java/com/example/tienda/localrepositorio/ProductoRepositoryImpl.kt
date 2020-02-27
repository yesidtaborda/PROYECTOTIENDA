package com.example.tienda.localrepositorio

import com.example.tienda.Clases.Producto
import com.example.tienda.localrepositorio.repositorio.ProductoDAO
import io.reactivex.Completable
import io.reactivex.Flowable


class ProductoRepositoryImpl(private val productoDAO: ProductoDAO):ProductoRepository {



    override fun getProducto(): Flowable<List<Producto>> = productoDAO.getAllProducto()
    override fun insertProducto(product: Producto): Completable = productoDAO.insertCategoria(product)


    //override fun insertCategoria(pro: Categoria): Completable = categoriaDAO.insertCategoria(pro)
}