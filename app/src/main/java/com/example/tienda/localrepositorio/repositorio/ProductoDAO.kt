package com.example.tienda.localrepositorio.repositorio

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tienda.Clases.Categoria
import com.example.tienda.Clases.Producto
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface ProductoDAO {
    @Query("SELECT * FROM producto ORDER BY id DESC")
    fun getAllProducto(): Flowable<List<Producto>>

    @Insert
    fun insertCategoria( Producto:Producto): Completable










}