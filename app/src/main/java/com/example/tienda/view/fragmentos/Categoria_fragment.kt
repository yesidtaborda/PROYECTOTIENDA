package com.example.tienda.view.fragmentos


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tienda.Clases.Categoria
import com.example.tienda.R
import com.example.tienda.view.adaptadores.CategoriaAdaptador
import com.example.tienda.viewmodels.CategoriaViewModel
import com.example.tienda.viewmodels.UiState
import kotlinx.android.synthetic.main.fragment_categoria.*
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * A simple [Fragment] subclass.
 */
class Categoria_fragment : Fragment() {
    private lateinit var adapterCategoria: CategoriaAdaptador
    private val categoriaViewModel: CategoriaViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categoria, container, false)
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
        categoriaViewModel.getCategoria()

    }


    private fun setHandlers() {
        categoriaViewModel.getCategoriaLiveData().observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is UiState.Loading -> {
                    Log.e(TAG, "Cargando...")
                }

                is UiState.OnSuccess<*> -> {
                    Log.e(TAG, "Pintar elementos.")
                    adapterCategoria.setData((state.data) as MutableList<Categoria>)
                }

                is UiState.OnError -> {
                    Log.e(TAG, "Somethin wrong : ${state.message}")
                }
            }
        })


    }

    fun initUi() {
        adapterCategoria = CategoriaAdaptador(
            clickClosure = {
                val bundle = Bundle()
                bundle.putSerializable("key", it)
                findNavController().navigate(R.id.action_categoria_fragment_to_productos, bundle)
            })
        recyclerViewCategoria.run {
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = adapterCategoria
        }
        recyclerViewCategoria.layoutManager = GridLayoutManager(activity, 1, RecyclerView.VERTICAL, false)
        recyclerViewCategoria.adapter = adapterCategoria

    }
    fun setOClickListeners() {
        nuevaCategoria.setOnClickListener {
            findNavController().navigate(R.id.action_categoria_fragment_to_nuevaCategoria2)
        }
    }
    companion object {
        const val TAG = "CategoriaFragment"
    }
}



