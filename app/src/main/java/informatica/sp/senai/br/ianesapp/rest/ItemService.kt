package informatica.sp.senai.br.ianesapp.rest

import informatica.sp.senai.br.ianesapp.model.ItemPatrimonio
import retrofit2.Call
import retrofit2.http.GET

interface ItemService {

    @GET("patrimonios/itens")
    fun listarItens(): Call<List<ItemPatrimonio>>

}