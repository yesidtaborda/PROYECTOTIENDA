package com.example.tienda.view.fragmentos


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.tienda.Clases.Categoria
import com.example.tienda.R
import com.example.tienda.viewmodels.CategoriaViewModel
import com.example.tienda.viewmodels.UiState
import kotlinx.android.synthetic.main.fragment_nueva_categoria.*

import org.koin.androidx.viewmodel.ext.android.viewModel


class NuevaCategoria_fragment : Fragment() {
    private val nuevoViewModel: CategoriaViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nueva_categoria, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
        setHandlers()

    }

    fun setHandlers() {
        nuevoViewModel.saveCategoriaLiveData().observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is UiState.Loading -> {
                    Log.e(TAG, "Cargando...")
                }
                is UiState.OnSuccess<*> -> {
                    Log.e(TAG, "Mensaje guardado")
                    clearFields()
                    findNavController().navigate(R.id.action_nuevaCategoria2_to_categoria_fragment)
                }
                is UiState.OnError -> {
                    Log.e(TAG, "Error inesperado: ${state.message}")
                    clearFields()
                }
            }
        })
    }

    private fun clearFields() {
        nombrenuevacategoria.setText("")
        urlImagenEditext.setText("")
    }


    fun setupListener() {
        guardarcategoria.setOnClickListener {
            if (emptyvalideFieldsFields()) {
                nuevoViewModel.saveCategoria(
                    Categoria(
                        nombre = nombrenuevacategoria.text.toString(),
                        imageUrl = urlImagenEditext.text.toString()


                    )

                )

            }

        }


    }

    private fun emptyvalideFieldsFields(): Boolean {
        var valide: Boolean

        nombrenuevacategoria.run {

            if (this.text.toString().isNullOrEmpty()) {
                nombrenuevacategoria.error = "Obligatorio"
                this.requestFocus()
                valide = false
            } else {
                nombrenuevacategoria.error = null
                valide = true
            }
        }
        urlImagenEditext.run {
            if (this.text.toString().isNullOrEmpty()) {
                urlImagenEditext.error = "obligatorio"
                this.requestFocus()
                valide = false


            } else {
                urlImagenEditext.error = null
                valide = true
            }

        }
        return valide
    }
    companion object {
        const val TAG = "NuevaCategoriaFragment"
    }
}
