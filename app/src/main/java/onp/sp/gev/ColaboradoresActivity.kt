package onp.sp.gev

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ColaboradoresActivity : AppCompatActivity() {

    private lateinit var nomeEditText: EditText
    private lateinit var dataEditText: EditText
    private lateinit var valorVendidoEditText: EditText
    private lateinit var inserirButton: Button
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_colaboradores)

        nomeEditText = findViewById(R.id.editTextNome)
        dataEditText = findViewById(R.id.editTextData)
        valorVendidoEditText = findViewById(R.id.editTextValorVendido)
        inserirButton = findViewById(R.id.buttonAdicionar)
        dbHelper = DatabaseHelper(this)

        inserirButton.setOnClickListener {
            val nome = nomeEditText.text.toString()
            val data = dataEditText.text.toString()
            val valorVendido = valorVendidoEditText.text.toString().toDoubleOrNull()

            if (nome.isNotEmpty() && data.isNotEmpty() && valorVendido != null) {
                val newRowId = dbHelper.insertColaborador(nome, data, valorVendido)
                if (newRowId != -1L) {
                    Toast.makeText(this, "Colaborador inserido com sucesso!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Erro ao inserir colaborador.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos corretamente.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
