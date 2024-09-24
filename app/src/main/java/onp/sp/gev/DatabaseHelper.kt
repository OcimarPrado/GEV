package onp.sp.gev

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import onp.sp.gev.model.Gasto
import onp.sp.gev.model.Produto
import onp.sp.gev.model.Colaborador
import onp.sp.gev.model.Usuario

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "controle_estoque.db"
        private const val DATABASE_VERSION = 1

        // Tabela Produtos
        private const val TABLE_PRODUTOS = "produtos"
        private const val COLUMN_ID_PRODUTO = "id"
        private const val COLUMN_NOME_PRODUTO = "nome"
        private const val COLUMN_DESCRICAO_PRODUTO = "descricao"
        private const val COLUMN_PRECO_PRODUTO = "preco"
        private const val COLUMN_DATA_PRODUTO = "data"
        private const val COLUMN_ENTRADA_PRODUTO = "entrada"
        private const val COLUMN_SAIDA_PRODUTO = "saida"
        private const val COLUMN_QUANTIDADE_ESTOQUE_PRODUTO = "quantidade_em_estoque"

        // Tabela Colaboradores
        private const val TABLE_COLABORADORES = "colaboradores"
        private const val COLUMN_ID_COLABORADOR = "id"
        private const val COLUMN_NOME_COLABORADOR = "nome"
        private const val COLUMN_FUNCIONARIO_COLABORADOR = "funcao"

        // Tabela Gastos
        private const val TABLE_GASTOS = "gastos"
        private const val COLUMN_ID_GASTO = "id"
        private const val COLUMN_DESCRICAO_GASTO = "descricao"
        private const val COLUMN_VALOR_GASTO = "valor"
        private const val COLUMN_DATA_GASTO = "data"

        // Tabela Usuários
        private const val TABLE_USUARIOS = "usuarios"
        private const val COLUMN_ID_USUARIO = "id"
        private const val COLUMN_NOME_USUARIO = "nome"
        private const val COLUMN_EMAIL_USUARIO = "email"
        private const val COLUMN_SENHA_USUARIO = "senha"
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Criação da tabela Produtos
        val createProdutosTable = ("CREATE TABLE $TABLE_PRODUTOS (" +
                "$COLUMN_ID_PRODUTO INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_NOME_PRODUTO TEXT NOT NULL, " +
                "$COLUMN_DESCRICAO_PRODUTO TEXT NOT NULL, " +
                "$COLUMN_PRECO_PRODUTO REAL NOT NULL, " +
                "$COLUMN_DATA_PRODUTO TEXT NOT NULL, " +
                "$COLUMN_ENTRADA_PRODUTO INTEGER NOT NULL, " +
                "$COLUMN_SAIDA_PRODUTO INTEGER NOT NULL, " +
                "$COLUMN_QUANTIDADE_ESTOQUE_PRODUTO INTEGER NOT NULL)")
        db.execSQL(createProdutosTable)

        // Criação da tabela Colaboradores
        val createColaboradoresTable = ("CREATE TABLE $TABLE_COLABORADORES (" +
                "$COLUMN_ID_COLABORADOR INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_NOME_COLABORADOR TEXT NOT NULL, " +
                "$COLUMN_FUNCIONARIO_COLABORADOR TEXT NOT NULL)")
        db.execSQL(createColaboradoresTable)

        // Criação da tabela Gastos
        val createGastosTable = ("CREATE TABLE $TABLE_GASTOS (" +
                "$COLUMN_ID_GASTO INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_DESCRICAO_GASTO TEXT NOT NULL, " +
                "$COLUMN_VALOR_GASTO REAL NOT NULL, " +
                "$COLUMN_DATA_GASTO TEXT NOT NULL)")
        db.execSQL(createGastosTable)

        // Criação da tabela Usuários
        val createUsuariosTable = ("CREATE TABLE $TABLE_USUARIOS (" +
                "$COLUMN_ID_USUARIO INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_NOME_USUARIO TEXT NOT NULL, " +
                "$COLUMN_EMAIL_USUARIO TEXT NOT NULL, " +
                "$COLUMN_SENHA_USUARIO TEXT NOT NULL)")
        db.execSQL(createUsuariosTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Atualização do banco de dados
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PRODUTOS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_COLABORADORES")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_GASTOS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USUARIOS")
        onCreate(db)
    }

    // Métodos para Produtos
    fun insertProduto(
        nome: String,
        descricao: String,
        preco: Double,
        data: String,
        entrada: Int,
        saida: Int,
        quantidadeEmEstoque: Int
    ): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOME_PRODUTO, nome)
            put(COLUMN_DESCRICAO_PRODUTO, descricao)
            put(COLUMN_PRECO_PRODUTO, preco)
            put(COLUMN_DATA_PRODUTO, data)
            put(COLUMN_ENTRADA_PRODUTO, entrada)
            put(COLUMN_SAIDA_PRODUTO, saida)
            put(COLUMN_QUANTIDADE_ESTOQUE_PRODUTO, quantidadeEmEstoque)
        }
        return db.insert(TABLE_PRODUTOS, null, values)
    }

    fun getAllProdutos(): List<Produto> {
        val produtos = mutableListOf<Produto>()
        val db = readableDatabase
        val cursor = db.query(TABLE_PRODUTOS, null, null, null, null, null, null)
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(COLUMN_ID_PRODUTO))
                val nome = getString(getColumnIndexOrThrow(COLUMN_NOME_PRODUTO))
                val descricao = getString(getColumnIndexOrThrow(COLUMN_DESCRICAO_PRODUTO))
                val preco = getDouble(getColumnIndexOrThrow(COLUMN_PRECO_PRODUTO))
                val data = getString(getColumnIndexOrThrow(COLUMN_DATA_PRODUTO))
                val entrada = getInt(getColumnIndexOrThrow(COLUMN_ENTRADA_PRODUTO))
                val saida = getInt(getColumnIndexOrThrow(COLUMN_SAIDA_PRODUTO))
                val quantidadeEstoque = getInt(getColumnIndexOrThrow(COLUMN_QUANTIDADE_ESTOQUE_PRODUTO))
                produtos.add(Produto(id, nome, descricao, preco.toString(), data, entrada.toString(), saida, quantidadeEstoque))
            }
        }
        cursor.close()
        return produtos
    }

    // Métodos para Colaboradores
    fun insertColaborador(nome: String, funcao: String, valorVendido: Double): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOME_COLABORADOR, nome)
            put(COLUMN_FUNCIONARIO_COLABORADOR, funcao)
        }
        return db.insert(TABLE_COLABORADORES, null, values)
    }

    fun getAllColaboradores(): List<Colaborador> {
        val colaboradores = mutableListOf<Colaborador>()
        val db = readableDatabase
        val cursor = db.query(TABLE_COLABORADORES, null, null, null, null, null, null)
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(COLUMN_ID_COLABORADOR))
                val nome = getString(getColumnIndexOrThrow(COLUMN_NOME_COLABORADOR))
                val funcao = getString(getColumnIndexOrThrow(COLUMN_FUNCIONARIO_COLABORADOR))
                colaboradores.add(Colaborador(id, nome, funcao))
            }
        }
        cursor.close()
        return colaboradores
    }

    // Métodos para Gastos
    fun insertGasto(descricao: String, valor: Double, data: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_DESCRICAO_GASTO, descricao)
            put(COLUMN_VALOR_GASTO, valor)
            put(COLUMN_DATA_GASTO, data)
        }
        return db.insert(TABLE_GASTOS, null, values)
    }

    fun getAllGastos(): List<Gasto> {
        val gastos = mutableListOf<Gasto>()
        val db = readableDatabase
        val cursor = db.query(TABLE_GASTOS, null, null, null, null, null, null)
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(COLUMN_ID_GASTO))
                val descricao = getString(getColumnIndexOrThrow(COLUMN_DESCRICAO_GASTO))
                val valor = getDouble(getColumnIndexOrThrow(COLUMN_VALOR_GASTO))
                val data = getString(getColumnIndexOrThrow(COLUMN_DATA_GASTO))
                gastos.add(Gasto(id, descricao, valor.toString(), data))
            }
        }
        cursor.close()
        return gastos
    }

    // Métodos para Usuários
    fun insertUsuario(nome: String, email: String, senha: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOME_USUARIO, nome)
            put(COLUMN_EMAIL_USUARIO, email)
            put(COLUMN_SENHA_USUARIO, senha)
        }
        return db.insert(TABLE_USUARIOS, null, values)
    }

    fun getAllUsuarios(): List<Usuario> {
        val usuarios = mutableListOf<Usuario>()
        val db = readableDatabase
        val cursor = db.query(TABLE_USUARIOS, null, null, null, null, null, null)
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(COLUMN_ID_USUARIO))
                val nome = getString(getColumnIndexOrThrow(COLUMN_NOME_USUARIO))
                val email = getString(getColumnIndexOrThrow(COLUMN_EMAIL_USUARIO))
                val senha = getString(getColumnIndexOrThrow(COLUMN_SENHA_USUARIO))
                usuarios.add(Usuario(id, nome, email, senha))
            }
        }
        cursor.close() // Fechar o cursor após o uso
        return usuarios
    }

    fun checkUsuario(email: String, senha: String): Boolean {
        val db = readableDatabase
        val cursor = db.query(
            TABLE_USUARIOS,
            arrayOf(COLUMN_ID_USUARIO),
            "$COLUMN_EMAIL_USUARIO = ? AND $COLUMN_SENHA_USUARIO = ?",
            arrayOf(email, senha),
            null,
            null,
            null
        )
        val exists = cursor.count > 0
        cursor.close() // Fechar o cursor após o uso
        return exists
    }


}

