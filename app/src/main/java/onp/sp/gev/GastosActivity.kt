package onp.sp.gev

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class GastosActivity : AppCompatActivity() {

    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gastos)

        db = DatabaseHelper(this)
        // Implementar a lógica de gastos aqui
    }
}
