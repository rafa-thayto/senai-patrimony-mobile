package informatica.sp.senai.br.ianesapp.model

class Patrimonio {

    var id: Long = 0
    var nome: String = ""
    var categoria: Categoria? = null

    constructor()

    constructor(nome: String) {
        this.nome = nome
    }

    constructor(id: Long, nome: String) {
        this.id = id
        this.nome = nome
    }

}
