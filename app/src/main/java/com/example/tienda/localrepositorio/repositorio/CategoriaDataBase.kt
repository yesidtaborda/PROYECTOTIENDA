package com.example.tienda.localrepositorio.repositorio

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tienda.Clases.Categoria
import com.example.tienda.Clases.Producto


@Database(entities = [Categoria::class,Producto::class],version = 1,exportSchema = false)
abstract class Tienda: RoomDatabase() {
    abstract fun newInstanceCategoriaDAO(): CategoriaDAO
    abstract fun newInstanceProductoDAO(): ProductoDAO
}