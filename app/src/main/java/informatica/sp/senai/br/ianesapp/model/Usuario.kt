package informatica.sp.senai.br.ianesapp.model

class Usuario {
    var id: Long = 0
    var email: String = ""
    var senha: String = ""
    var nome: String = ""

    constructor()

    constructor(email: String, senha: String) {
        this.email = email
        this.senha = senha
    }

    constructor(id: Long, email: String, senha: String, nome: String) {
        this.id = id
        this.email = email
        this.senha = senha
        this.nome = nome
    }
}
