package informatica.sp.senai.br.ianesapp.rest

import informatica.sp.senai.br.ianesapp.model.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UsuarioService {

    @POST("/auth/jwt")
    fun authenticate(@Body usuario: Usuario): Call<Usuario>

}
