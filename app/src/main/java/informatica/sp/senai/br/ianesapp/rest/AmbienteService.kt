package informatica.sp.senai.br.ianesapp.rest

import informatica.sp.senai.br.ianesapp.model.Ambiente
import retrofit2.Call
import retrofit2.http.GET

interface AmbienteService {

    @GET("ambientes")
    fun listarAmbientes(): Call<List<Ambiente>>

}