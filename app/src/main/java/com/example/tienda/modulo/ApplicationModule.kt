package com.example.tienda.modulo

import androidx.room.Room
import com.example.tienda.localrepositorio.CategoriaRepository
import com.example.tienda.localrepositorio.         CategoriaRepositoryImpl
import com.example.tienda.localrepositorio.ProductoRepository
import com.example.tienda.localrepositorio.ProductoRepositoryImpl
import com.example.tienda.localrepositorio.repositorio.Tienda
import com.example.tienda.viewmodels.CategoriaViewModel
import com.example.tienda.viewmodels.ProductoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val applicationModule = module {
    single {
        Room.databaseBuilder(
            get(), Tienda::class.java, "tienda"
        ).build()
    }

    single {
        get<Tienda>().newInstanceCategoriaDAO()
    }
    single {
        get<Tienda>().newInstanceProductoDAO()
    }

    factory <CategoriaRepository>{
        CategoriaRepositoryImpl(get())
    }
    factory <ProductoRepository>{
        ProductoRepositoryImpl(get())
    }

    viewModel {
        CategoriaViewModel(get())
    }

    viewModel {
        ProductoViewModel(get())
    }

}