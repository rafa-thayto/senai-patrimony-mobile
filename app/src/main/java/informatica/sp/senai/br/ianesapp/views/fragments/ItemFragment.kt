package informatica.sp.senai.br.ianesapp.views.fragments


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import informatica.sp.senai.br.ianesapp.R
import informatica.sp.senai.br.ianesapp.config.RetrofitConfig
import informatica.sp.senai.br.ianesapp.model.ItemPatrimonio
import informatica.sp.senai.br.ianesapp.utils.AppUtils
import informatica.sp.senai.br.ianesapp.views.adapter.ItemListAdapter
import retrofit2.Call
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 *
 */
class ItemFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var llm: LinearLayoutManager
    private lateinit var itemList: ArrayList<ItemPatrimonio>
    private lateinit var itemListAdapter: ItemListAdapter
    private var token: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_categorias, container, false)
        recyclerView = rootView.findViewById(R.id.rvCategorias)
        itemList = ArrayList()
        llm = LinearLayoutManager(context)

        val sharedPreferences = rootView.context.getSharedPreferences(AppUtils.SHARED_PREFERENCES(), Context.MODE_PRIVATE)
        token = sharedPreferences.getString("token", "")

        recyclerView.layoutManager = llm
        itemListAdapter = ItemListAdapter(itemList, context)
        recyclerView.adapter = itemListAdapter

        carregarItens()

        return rootView
    }

    fun carregarItens() {

        val chamadaItens = RetrofitConfig(this.token).itemService().listarItens()
        chamadaItens.enqueue(object: retrofit2.Callback<List<ItemPatrimonio>> {

            override fun onResponse(call: Call<List<ItemPatrimonio>>?, response: Response<List<ItemPatrimonio>>?) {
                val itens = response?.body()
                if (itens != null) {
                    itemListAdapter.carregarLista(itens)
                }
            }

            override fun onFailure(call: Call<List<ItemPatrimonio>>?, t: Throwable?) {
                Log.e("itens", "Erro ao carregar itens")
                Toast.makeText(context, "Verifique sua conexão de interner", Toast.LENGTH_SHORT).show()
            }

        })

    }


}
