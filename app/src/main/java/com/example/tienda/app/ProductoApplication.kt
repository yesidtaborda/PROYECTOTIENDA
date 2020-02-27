package com.example.tienda.app

import android.app.Application
import com.example.tienda.modulo.ProductoModule
import com.example.tienda.modulo.applicationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ProductoApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ProductoApplication)
            androidLogger()
            modules(listOf(ProductoModule))
        }
    }





}