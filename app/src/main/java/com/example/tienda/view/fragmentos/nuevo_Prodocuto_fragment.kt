package com.example.tienda.view.fragmentos


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.tienda.Clases.Producto
import com.example.tienda.R
import com.example.tienda.view.fragmentos.Productos_fragment.Companion.TAG
import com.example.tienda.viewmodels.ProductoViewModel
import com.example.tienda.viewmodels.UiState
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_nuevo__prodocuto.*
import kotlinx.android.synthetic.main.item_producto.*
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class nuevo_Prodocuto_fragment : Fragment() {
    private val productoViewModel: ProductoViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nuevo__prodocuto, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
        setHandlers()

    }
    fun setHandlers() {
        productoViewModel.insertProductoLiveData().observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is UiState.Loading -> {
                    Log.e(TAG, "Cargando...")
                }

                is UiState.OnSuccess<*> -> {
                    Log.e(TAG, "Mensaje guardado")
                    clearFields()
                    findNavController().navigate(R.id.action_nuevo_Prodocuto_fragment_to_productos)
                }

                is UiState.OnError -> {
                    Log.e(TAG, "Error inesperado: ${state.message}")
                    clearFields()
                }
            }
        })
    }
    private fun clearFields() {
        nombrenuevoproducto.setText("")
        precionuevoproducto.setText("")
        urlnuevoproducto.setText("")
    }


    fun setupListener() {
        guardarproducto.setOnClickListener {
            if (emptyvalideFieldsFields()) {
                productoViewModel.saveProducto(
                    Producto(
                        nombre = nombrenuevoproducto.text.toString(),
                        precio = 0.0,
                        imageUrl = urlnuevoproducto.text.toString()


                    )

                )

            }

        }


    }

    private fun emptyvalideFieldsFields(): Boolean {
        var valide: Boolean

        nombrenuevoproducto.run {

            if (this.text.toString().isNullOrEmpty()) {
                nombrenuevoproducto.error = "Obligatorio"
                this.requestFocus()
                valide = false
            } else {
                nombrenuevoproducto.error = null
                valide = true
            }
        }
        urlnuevoproducto.run {
            if(this.text.toString().isNullOrEmpty()){
                urlnuevoproducto.error = "obligatorio"
                this.requestFocus()
                valide=false


            }else{
                urlnuevoproducto.error = null
                valide = true
            }

        }
        precionuevoproducto.run {
            if(this.text.toString().isNullOrEmpty()){
                precionuevoproducto.error = "obligatorio"
                this.requestFocus()
                valide=false


            }else{
                precionuevoproducto.error = null
                valide = true
            }

        }
        return valide
    }

}
