package informatica.sp.senai.br.ianesapp.views.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import informatica.sp.senai.br.ianesapp.R
import informatica.sp.senai.br.ianesapp.model.ItemPatrimonio
import informatica.sp.senai.br.ianesapp.views.MainActivity
import kotlinx.android.synthetic.main.lista_itens.view.*

class ItemListAdapter(private var itens: List<ItemPatrimonio>,
                      private var context: Context?) : RecyclerView.Adapter<ItemListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.lista_itens, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itens.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itens[position]

        holder.itemView.tvRvNomeItens.text = item.patrimonio?.nome
        holder.itemView.tvRvCategoriaItens.text = item.patrimonio?.categoria?.nome
        holder.itemView.tvRvAmbienteItens.text = item.ambienteAtual?.nome

    }

    fun carregarLista(itens: List<ItemPatrimonio>) {
        this.itens = itens
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