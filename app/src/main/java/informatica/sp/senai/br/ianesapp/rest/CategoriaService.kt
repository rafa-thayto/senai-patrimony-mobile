package informatica.sp.senai.br.ianesapp.rest

import informatica.sp.senai.br.ianesapp.model.Categoria
import retrofit2.Call
import retrofit2.http.GET

interface CategoriaService {

    @GET("patrimonios/categorias")
    fun listarCategorias(): Call<List<Categoria>>

}