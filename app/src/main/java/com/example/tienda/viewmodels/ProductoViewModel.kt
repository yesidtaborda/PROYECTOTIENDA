package com.example.tienda.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tienda.Clases.Producto
import com.example.tienda.localrepositorio.ProductoRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class ProductoViewModel(private val productoRepository: ProductoRepository):ViewModel() {

private val getProductoMutableLiveData:MutableLiveData<UiState> = MutableLiveData()

private val insertProductoMutableLiveData:MutableLiveData<UiState> = MutableLiveData()
    private val subscriptor = CompositeDisposable()


    fun getProductoLiveData(): LiveData<UiState> = getProductoMutableLiveData
    fun insertProductoLiveData(): LiveData<UiState> = insertProductoMutableLiveData



    fun getProducto() {
        subscriptor.add(
            productoRepository.getProducto()
                .doOnSubscribe {
                    getProductoMutableLiveData.postValue(UiState.Loading)
                }.subscribeOn(Schedulers.io())
                .subscribeBy(
                    onNext = {
                        getProductoMutableLiveData.postValue(UiState.OnSuccess(it))
                    },
                    onError = {
                        getProductoMutableLiveData.postValue(
                            UiState.OnError(
                                it.message ?: "Error inesperado"
                            )
                        )
                    },
                    onComplete = {

                    }
                )
        )
    }
    fun saveProducto(produc: Producto) {
        subscriptor.add(
            productoRepository.insertProducto(
                Producto(
                    nombre = produc.nombre,
                    precio = produc.precio,
                    imageUrl = produc.imageUrl
                )
            ).doOnSubscribe {
                insertProductoMutableLiveData.postValue(UiState.Loading)
            }.subscribeOn(Schedulers.io())
                .subscribeBy(
                    onComplete = {
                        insertProductoMutableLiveData.postValue(UiState.OnSuccess(true))
                    },
                    onError = {
                        insertProductoMutableLiveData.postValue(
                            UiState.OnError(
                                it.message ?: "Error inesperado"
                            )
                        )
                    }
                )
        )
    }




}