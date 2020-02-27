package com.example.tienda.view.fragmentos


import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tienda.Clases.Categoria

import com.example.tienda.Clases.Producto
import com.example.tienda.R
import com.example.tienda.view.adaptadores.ProductoAdaptador
import com.example.tienda.viewmodels.ProductoViewModel
import com.example.tienda.viewmodels.UiState

import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.fragment_productos.*
import kotlinx.android.synthetic.main.item_categoria.*
import kotlinx.android.synthetic.main.item_producto.*
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel


class Productos_fragment : Fragment() {
    private lateinit var adapterProducto: ProductoAdaptador
  private val productoViewModel: ProductoViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_productos, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        setOClickListeners()
        setHandlers()



    }
    override fun onResume() {
        super.onResume()
        Log.e(TAG, "On create")
       productoViewModel.getProducto()
    }

   private fun setHandlers() {

           productoViewModel.getProductoLiveData().observe(viewLifecycleOwner, Observer { state ->
               when (state) {
                   is UiState.Loading -> {
                       Log.e(TAG, "Cargando...")
                   }

                   is UiState.OnSuccess<*> -> {
                       Log.e(TAG, "Pintar elementos.")
                       adapterProducto.setData((state.data) as MutableList<Producto>)
                   }

                   is UiState.OnError -> {
                       Log.e(TAG, "Somethin wrong : ${state.message}")
                   }
               }
           })


    }

    fun initUi() {
        adapterProducto = ProductoAdaptador(
            clickClosure = {
                val bundle = Bundle()
                bundle.putSerializable("producto",it)
                findNavController().navigate(R.id.action_productos_to_confirmacion, bundle)

            })
        recyclerVIewProducto.run {
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

            adapter = adapterProducto
        }

        recyclerVIewProducto.layoutManager = GridLayoutManager(activity, 1, RecyclerView.VERTICAL, false)
        recyclerVIewProducto.adapter = adapterProducto



    }
 fun setOClickListeners() {
     nuevoproducto.setOnClickListener {
            findNavController().navigate(R.id.action_productos_to_nuevo_Prodocuto_fragment)
        }
    }
    companion object {
        const val TAG = "HeroesFragment"
    }

}

