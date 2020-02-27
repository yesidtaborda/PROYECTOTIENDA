package com.example.tienda.modulo

import androidx.room.Room
import com.example.tienda.localrepositorio.CategoriaRepository
import com.example.tienda.localrepositorio.         CategoriaRepositoryImpl
import com.example.tienda.localrepositorio.ProductoRepository
import com.example.tienda.localrepositorio.ProductoRepositoryImpl

import com.example.tienda.localrepositorio.repositorio.ProductoDataBase
import com.example.tienda.viewmodels.CategoriaViewModel
import com.example.tienda.viewmodels.ProductoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val ProductoModule = module {
    single {
        Room.databaseBuilder(
            get(), ProductoDataBase::class.java, "tienda"
        ).build()
    }

    single {
        get<ProductoDataBase>().newInstance()
    }

    factory <ProductoRepository> {
        ProductoRepositoryImpl(get())
    }

    viewModel {
        ProductoViewModel(get())

    }

}