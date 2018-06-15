package informatica.sp.senai.br.ianesapp.views

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.Toast
import informatica.sp.senai.br.ianesapp.R
import informatica.sp.senai.br.ianesapp.config.RetrofitConfig
import informatica.sp.senai.br.ianesapp.model.Usuario
import informatica.sp.senai.br.ianesapp.utils.AppUtils
import org.json.JSONObject
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    val context: Context = this
    val sharedPreferences: SharedPreferences = getSharedPreferences(AppUtils.SHARED_PREFERENCES(), 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val tilEmailLogin = findViewById<TextInputLayout>(R.id.tilEmail)
        val tilSenhaLogin = findViewById<TextInputLayout>(R.id.tilSenha)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val usuario = Usuario(tilEmailLogin.editText?.text.toString(), tilSenhaLogin.editText?.text.toString())
            val chamadaLogin = RetrofitConfig().usuarioService().authenticate(usuario)
            chamadaLogin.enqueue(object: retrofit2.Callback<Usuario> {

                override fun onResponse(call: retrofit2.Call<Usuario>?, response: Response<Usuario>?) {
                    if (response!!.isSuccessful) {
                        Log.d("token", response.body().toString())

                        val obj = JSONObject(response.body().toString())
                        val token = obj.getString("token")
                        val editor: SharedPreferences.Editor = sharedPreferences.edit()
                        editor.putString("token", "Bearer " + token)
                        editor.apply()

                        val intent = Intent(context, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }

                override fun onFailure(call: retrofit2.Call<Usuario>?, t: Throwable?) {
                    Toast.makeText(applicationContext, "Erro ao fazer login", Toast.LENGTH_SHORT).show()
                    Log.d("Login error", t?.message)
                }

            })
        }

    }
}
