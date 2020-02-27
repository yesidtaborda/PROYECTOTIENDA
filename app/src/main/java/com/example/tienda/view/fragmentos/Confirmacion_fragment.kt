package com.example.tienda.view.fragmentos


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.tienda.R
import kotlinx.android.synthetic.main.fragment_confirmacion.*

/**
 * A simple [Fragment] subclass.
 */
class Confirmacion_fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirmacion, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOClickListeners()
    }

    fun setOClickListeners() {
        buttomconfirmar.setOnClickListener {
            findNavController().navigate(R.id.action_confirmacion_to_categoria_fragment)
        }
        buttomcancelar.setOnClickListener {
            findNavController().navigate(R.id.action_confirmacion_to_productos)
        }
    }





}
