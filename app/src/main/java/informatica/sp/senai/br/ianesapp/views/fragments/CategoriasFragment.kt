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
import informatica.sp.senai.br.ianesapp.R
import informatica.sp.senai.br.ianesapp.config.RetrofitConfig
import informatica.sp.senai.br.ianesapp.model.Categoria
import informatica.sp.senai.br.ianesapp.utils.AppUtils
import informatica.sp.senai.br.ianesapp.views.adapter.CategoriaListAdapter
import retrofit2.Call
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 *
 */
class CategoriasFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var llm: LinearLayoutManager
    private lateinit var categoriaList: ArrayList<Categoria>
    private lateinit var categoriaListAdapter: CategoriaListAdapter
    private var token: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_categorias, container, false)
        recyclerView = rootView.findViewById(R.id.rvCategorias)
        categoriaList = ArrayList()
        llm = LinearLayoutManager(context)

        val sharedPreferences = rootView.context.getSharedPreferences(AppUtils.SHARED_PREFERENCES(), Context.MODE_PRIVATE)
        token = sharedPreferences.getString("token", "")

        recyclerView.layoutManager = llm
        categoriaListAdapter = CategoriaListAdapter(categoriaList, context)
        recyclerView.adapter = categoriaListAdapter

        carregarCategorias()

        return rootView
    }

    fun carregarCategorias() {

        val chamadaCategorias = RetrofitConfig(this.token!!).categoriaService().listarCategorias()
        chamadaCategorias.enqueue(object: retrofit2.Callback<List<Categoria>> {

            override fun onResponse(call: Call<List<Categoria>>?, response: Response<List<Categoria>>?) {
                val categorias = response?.body()
                if (categorias != null) {
                    categoriaListAdapter.carregarLista(categorias)
                }
                Log.d("categorias", categorias.toString())
            }

            override fun onFailure(call: Call<List<Categoria>>?, t: Throwable?) {
                Log.e("categorias", "Erro ao carregar categorias")
            }
        })

    }


}
