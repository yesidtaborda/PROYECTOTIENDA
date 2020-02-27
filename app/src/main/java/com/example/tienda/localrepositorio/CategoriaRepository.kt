package com.example.tienda.localrepositorio



import com.example.tienda.Clases.Categoria
import io.reactivex.Completable
import io.reactivex.Flowable

interface CategoriaRepository {

    fun getCategoria(): Flowable<List<Categoria>>

    fun insertCategoria(pro: Categoria): Completable

    fun updateCategoria(pro: Categoria): Completable


}