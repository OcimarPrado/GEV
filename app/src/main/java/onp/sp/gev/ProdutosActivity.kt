package onp.sp.gev

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import onp.sp.gev.model.Produto

class ProdutosActivity : AppCompatActivity() {

    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produtos)

        db = DatabaseHelper(this)

        // Exemplo de inserção de produto
        insertProduto("Produto Exemplo", "Descrição do Produto Exemplo", 29.99, "2023-09-17", 20, 10, 10)

        // Recuperar todos os produtos
        val produtos = getAllProdutos()
        // Lógica para utilizar a lista de produtos, como exibir em uma lista ou RecyclerView
        // Exemplo de exibição em Toast
        produtos.forEach { produto ->
            Toast.makeText(this, "Produto: ${produto.nome}, Descrição: ${produto.descricao}, Preço: ${produto.preco}, Data: ${produto.data}" +
                    ", Quantidade em Estoque: ${produto.quantidadeEmEstoque}", Toast.LENGTH_LONG).show()
        }
    }

    // Métodos para gerenciar Produtos
    private fun insertProduto(
        nome: String,
        descricao: String,
        preco: Double,
        data: String,
        entrada: Int,
        saida: Int,
        quantidadeEmEstoque: Int
    ) {
        val id = db.insertProduto(nome, descricao, preco, data, entrada, saida, quantidadeEmEstoque)
        if (id != -1L) {
            Toast.makeText(this, "Produto inserido com sucesso!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Falha ao inserir produto.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getAllProdutos(): List<Produto> {
        return db.getAllProdutos()
    }
}
