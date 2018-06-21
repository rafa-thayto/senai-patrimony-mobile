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
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

class LoginActivity : AppCompatActivity() {

    val context: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val sharedPreferences: SharedPreferences = context.getSharedPreferences(AppUtils.SHARED_PREFERENCES(), Context.MODE_PRIVATE)


        val tilEmailLogin = findViewById<TextInputLayout>(R.id.tilEmail)
        val tilSenhaLogin = findViewById<TextInputLayout>(R.id.tilSenha)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        // Auto completition
        tilEmailLogin.editText?.setText("admin@email.com")
        tilSenhaLogin.editText?.setText("admin@132")

        btnLogin.setOnClickListener {

            val usuario = Usuario(tilEmailLogin.editText?.text.toString(), tilSenhaLogin.editText?.text.toString())
            val chamadaLogin = RetrofitConfig().usuarioService().authenticate(usuario)

            chamadaLogin.enqueue(object: retrofit2.Callback<ResponseBody> {

                override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {

                    if (response?.isSuccessful!!) {

                        try {

                            val obj = JSONObject(response?.body()?.string())
                            Log.d("Response object: ", obj.toString())
                            val token = obj.getString("token")

                            val editor: SharedPreferences.Editor = sharedPreferences.edit()
                            editor.putString("token", "Bearer $token")
                            editor.apply()

                            val intent = Intent(context, MainActivity::class.java)
                            startActivity(intent)
                            finish()

                        } catch (e: IOException) {

                            e.printStackTrace()

                        } catch (e: JSONException) {

                            e.printStackTrace()

                        }

                    }

                }

                override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {

                    Toast.makeText(applicationContext, "Erro ao fazer login", Toast.LENGTH_SHORT).show()
                    Log.e("Login error", t?.message)

                }

            })
        }

    }
}
