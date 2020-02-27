package com.example.tienda.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tienda.Clases.Categoria
import com.example.tienda.localrepositorio.CategoriaRepository

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class CategoriaViewModel (private  val categoriaRepository: CategoriaRepository):ViewModel(){


    private val getCategoriaMutableLiveData:MutableLiveData<UiState> = MutableLiveData()

    private val saveCategoriaMutableLiveData:MutableLiveData<UiState> =MutableLiveData()

    private val updaCategoriaMutableLiveData:MutableLiveData<UiState> =MutableLiveData()

    private val subscriptor = CompositeDisposable()

    fun getCategoriaLiveData(): LiveData<UiState> = getCategoriaMutableLiveData
    fun saveCategoriaLiveData(): LiveData<UiState> = saveCategoriaMutableLiveData
    fun updateCategoriaLiveData(): LiveData<UiState> = updaCategoriaMutableLiveData


    fun getCategoria() {
        subscriptor.add(
            categoriaRepository.getCategoria()
                .doOnSubscribe {
                    getCategoriaMutableLiveData.postValue(UiState.Loading)
                }.subscribeOn(Schedulers.io())
                .subscribeBy(
                    onNext = {
                        getCategoriaMutableLiveData.postValue(UiState.OnSuccess(it))
                    },
                    onError = {
                        getCategoriaMutableLiveData.postValue(
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
    fun saveCategoria(cat: Categoria) {
        subscriptor.add(
            categoriaRepository.insertCategoria(
                Categoria(
                    nombre = cat.nombre,
                    imageUrl = cat.imageUrl
                )
            ).doOnSubscribe {
                saveCategoriaMutableLiveData.postValue(UiState.Loading)
            }.subscribeOn(Schedulers.io())
                .subscribeBy(
                    onComplete = {
                        saveCategoriaMutableLiveData.postValue(UiState.OnSuccess(true))
                    },
                    onError = {
                        saveCategoriaMutableLiveData.postValue(
                            UiState.OnError(
                                it.message ?: "Error inesperado"
                            )
                        )
                    }
                )
        )
    }


}