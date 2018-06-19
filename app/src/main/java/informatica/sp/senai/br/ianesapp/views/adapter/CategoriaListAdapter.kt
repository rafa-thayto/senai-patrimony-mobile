package informatica.sp.senai.br.ianesapp.views.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import informatica.sp.senai.br.ianesapp.R
import informatica.sp.senai.br.ianesapp.model.Categoria
import informatica.sp.senai.br.ianesapp.views.MainActivity
import kotlinx.android.synthetic.main.lista_categorias.view.*

class CategoriaListAdapter(private var categorias: List<Categoria>,
                           private var context: Context?) : RecyclerView.Adapter<CategoriaListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.lista_categorias, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categorias.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val categoria = categorias[position]

        holder.itemView.tvRvNomeCategoria.text = categoria.nome

    }

    fun carregarLista(categorias: List<Categoria>) {
        this.categorias = categorias
        notifyDataSetChanged()

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {

            itemView.setOnClickListener {

                val intent = Intent(itemView.context, MainActivity::class.java)
                itemView.context.startActivity(intent)

            }
        }

    }
}