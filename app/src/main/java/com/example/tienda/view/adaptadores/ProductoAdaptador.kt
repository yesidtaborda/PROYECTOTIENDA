package com.example.tienda.view.adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


import com.example.tienda.Clases.Producto
import com.example.tienda.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detalle_compra.view.*
import kotlinx.android.synthetic.main.fragment_nuevo__prodocuto.view.*
import kotlinx.android.synthetic.main.item_producto.view.*

class ProductoAdaptador(val clickClosure: (Producto) -> Unit) :
    RecyclerView.Adapter<ProductoAdaptador.ViewHolder>() {

    private var listProducto: MutableList<Producto> = mutableListOf()

    fun setData(prod: MutableList<Producto>) {

        this.listProducto.clear()
        this.listProducto.addAll(prod)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int):ViewHolder {
    val view=
        LayoutInflater.from(parent.context).inflate(R.layout.item_producto,parent,false)
        return  ViewHolder(view)
    }

    override fun getItemCount(): Int {

        return this.listProducto.count()
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        val view = this.listProducto[position]
        holder.bind(view)
        holder.bindclick(view)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(prod: Producto) {
            itemView.nombreproducto.text = prod.nombre
            itemView.precio.text = prod.precio.toString()
            Picasso.get()
                .load(prod.imageUrl)
                .error(R.drawable.ic_launcher_background)
                .into(itemView.imgproducto)
        }

        fun bindclick(prod: Producto) {

            itemView.setOnClickListener {
                clickClosure(prod)
            }

        }


    }


}