package onp.sp.gev

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class CadastroActivity: AppCompatActivity () {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)


        // Referências aos campos de entrada
        val editTextName = findViewById<EditText>(R.id.editName)
        val editTextEmail = findViewById<EditText>(R.id.editEmail)
        val editTextPassword = findViewById<EditText>(R.id.editSenha)
        val buttonRegister = findViewById<Button>(R.id.butRegister)

        // Configuração do listener para o botão de registro
        buttonRegister.setOnClickListener {
            val nome = editTextName.text.toString()
            val email = editTextEmail.text.toString()
            val Senha = editTextPassword.text.toString()
        }
    }
}
