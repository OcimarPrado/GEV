package onp.sp.gev

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class LoginActivity: AppCompatActivity () {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        // Referências aos campos de entrada
        val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        val editTextPassword = findViewById<EditText>(R.id.editTextSenha)
        val buttonRegister = findViewById<Button>(R.id.butLogin)

        // Configuração do listener para o botão de registro
        buttonRegister.setOnClickListener {
            val email = editTextEmail.text.toString()
            val Senha = editTextPassword.text.toString()
        }
    }
}

