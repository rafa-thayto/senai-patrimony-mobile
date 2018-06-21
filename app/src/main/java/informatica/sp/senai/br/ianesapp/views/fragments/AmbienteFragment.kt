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
import informatica.sp.senai.br.ianesapp.model.Ambiente
import informatica.sp.senai.br.ianesapp.utils.AppUtils
import informatica.sp.senai.br.ianesapp.views.adapter.AmbienteListAdapter
import retrofit2.Call
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 *
 */
class AmbienteFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var llm: LinearLayoutManager
    private lateinit var ambienteList: ArrayList<Ambiente>
    private lateinit var ambienteListAdapter: AmbienteListAdapter
    private var token: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_ambiente, container, false)
        recyclerView = rootView.findViewById(R.id.rvAmbientes)
        ambienteList = ArrayList()
        llm = LinearLayoutManager(context)

        val sharedPreferences = rootView.context.getSharedPreferences(AppUtils.SHARED_PREFERENCES(), Context.MODE_PRIVATE)
        token = sharedPreferences.getString("token", "")

        recyclerView.layoutManager = llm
        ambienteListAdapter = AmbienteListAdapter(ambienteList, context)
        recyclerView.adapter = ambienteListAdapter

        carregarAmbientes()

        return rootView
    }

    private fun carregarAmbientes() {

        val chamadaAmbientes = RetrofitConfig(this.token).ambienteService().listarAmbientes()
        chamadaAmbientes.enqueue(object: retrofit2.Callback<List<Ambiente>> {

            override fun onResponse(call: Call<List<Ambiente>>?, response: Response<List<Ambiente>>?) {

                val ambientes = response?.body()
                if (ambientes != null) {
                    ambienteListAdapter.carregarLista(ambientes)
                }

            }

            override fun onFailure(call: Call<List<Ambiente>>?, t: Throwable?) {
                Log.e("ambientes", "Erro ao carregar ambientes")
                Toast.makeText(context, "Verifique sua conexão de interner", Toast.LENGTH_SHORT).show()
            }


        })

    }


}
