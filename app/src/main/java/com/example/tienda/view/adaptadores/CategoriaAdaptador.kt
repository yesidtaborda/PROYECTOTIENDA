package com.example.tienda.view.adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tienda.Clases.Categoria
import com.example.tienda.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detalle_compra.view.*
import kotlinx.android.synthetic.main.fragment_nueva_categoria.view.*
import kotlinx.android.synthetic.main.fragment_nuevo__prodocuto.view.*
import kotlinx.android.synthetic.main.item_categoria.view.*
import kotlinx.android.synthetic.main.item_producto.view.*



class CategoriaAdaptador(val clickClosure:(Categoria)->Unit):
    RecyclerView.Adapter<CategoriaAdaptador.ViewHolder>()  {

    private var listCategoria: MutableList<Categoria> = mutableListOf()



    fun setData(pro: MutableList<Categoria>) {
        this.listCategoria.clear()
        this.listCategoria.addAll(pro)
        notifyDataSetChanged()

    }


    override fun onCreateViewHolder
                (parent: ViewGroup,
                 viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_categoria, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {
        return this.listCategoria.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val view = this.listCategoria[position]
        holder.bind(view)
        holder.bindClick(view)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pro: Categoria) {
            itemView.NombreCategoria.text = pro.nombre
            Picasso.get()
                .load(pro.imageUrl)
                .error(R.drawable.ic_launcher_background)
                .into(itemView.imagenCategoria)
        }
        fun bindClick(pro: Categoria) {
            itemView.setOnClickListener {
                clickClosure(pro)
            }
        }

    }


}

