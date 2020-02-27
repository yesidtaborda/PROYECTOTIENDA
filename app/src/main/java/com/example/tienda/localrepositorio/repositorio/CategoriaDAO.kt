package com.example.tienda.localrepositorio.repositorio

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tienda.Clases.Categoria


import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface CategoriaDAO {

    @Query("SELECT * FROM categoria ORDER BY id DESC")
    fun getAllCategoria(): Flowable<List<Categoria>>

    @Insert
    fun insertCategoria(categoria:Categoria): Completable



}