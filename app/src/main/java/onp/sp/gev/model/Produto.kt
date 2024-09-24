package onp.sp.gev.model

data class Produto(
    val id: Int,
    val nome: String,
    val descricao: String,
    val preco: String,
    val data: String,
    val entrada: String,
    val saida: Int,
    val quantidadeEmEstoque: Int
)
