package informatica.sp.senai.br.ianesapp.config

import informatica.sp.senai.br.ianesapp.rest.CategoriaService
import informatica.sp.senai.br.ianesapp.rest.UsuarioService
import informatica.sp.senai.br.ianesapp.utils.AppUtils
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {


    constructor()

    constructor(token: String) {
        this.token = token
    }

    var token: String = ""

    val okHttpClient = OkHttpClient.Builder().addInterceptor(Interceptor {
        val b = it.request().newBuilder()
        b.addHeader("Accept", "application/json")
        b.addHeader("Authorization", token)
        return@Interceptor it.proceed(b.build())
    })

    val retrofit = Retrofit.Builder()
            .baseUrl(AppUtils.BASE_URL())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun usuarioService(): UsuarioService {
        return retrofit.create(UsuarioService::class.java)
    }

    fun categoriaService(): CategoriaService {
        return retrofit.create(CategoriaService::class.java)
    }

}
