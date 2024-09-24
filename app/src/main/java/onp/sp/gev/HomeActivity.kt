package onp.sp.gev

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

class HomeActivity : AppCompatActivity() {

    private lateinit var barChartEstoque: BarChart
    private lateinit var pieChartVendedores: PieChart
    private lateinit var lineChartGastos: LineChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        barChartEstoque = findViewById(R.id.barChartEstoque)
        pieChartVendedores = findViewById(R.id.pieChartVendedores)
        lineChartGastos = findViewById(R.id.lineChartGastos)

        setupBarChart()
        // Configurar outros gráficos aqui
    }

    private fun setupBarChart() {
        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(0f, 100f)) // Exemplo de dados
        entries.add(BarEntry(1f, 200f))
        val dataSet = BarDataSet(entries, "Balanço de Produtos")
        val barData = BarData(dataSet)
        barChartEstoque.data = barData
        barChartEstoque.invalidate() // Atualiza o gráfico
    }

    // Adicione funções para configurar outros gráficos...
}
