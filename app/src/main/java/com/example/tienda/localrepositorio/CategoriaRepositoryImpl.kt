package com.example.tienda.localrepositorio


import com.example.tienda.Clases.Categoria
import com.example.tienda.localrepositorio.repositorio.CategoriaDAO
import io.reactivex.Completable
import io.reactivex.Flowable

class CategoriaRepositoryImpl(private val categoriaDAO:CategoriaDAO):CategoriaRepository {



    override fun getCategoria(): Flowable<List<Categoria>> = categoriaDAO.getAllCategoria()

    override fun insertCategoria(pro: Categoria): Completable = categoriaDAO.insertCategoria(pro)

    override fun updateCategoria(pro: Categoria): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}