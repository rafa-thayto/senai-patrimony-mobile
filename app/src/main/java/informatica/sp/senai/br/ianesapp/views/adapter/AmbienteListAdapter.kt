package informatica.sp.senai.br.ianesapp.views.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import informatica.sp.senai.br.ianesapp.R
import informatica.sp.senai.br.ianesapp.model.Ambiente
import informatica.sp.senai.br.ianesapp.views.MainActivity
import kotlinx.android.synthetic.main.lista_ambientes.view.*

class AmbienteListAdapter(private var ambientes: List<Ambiente>,
                          private var context: Context?) : RecyclerView.Adapter<AmbienteListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.lista_ambientes, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ambientes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ambiente= ambientes[position]

        holder.itemView.tvRvNomeAmbiente.text = ambiente.nome
    }

    fun carregarLista(ambientes: List<Ambiente>) {
        this.ambientes = ambientes
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