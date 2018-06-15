package informatica.sp.senai.br.ianesapp.model

class Categoria {

    var id: Long = 0
    var nome: String = ""

    constructor()

    constructor(nome: String) {
        this.nome = nome
    }

    constructor(id: Long, nome: String) {
        this.id = id
        this.nome = nome
    }

    override fun toString(): String {
        return "Categoria(id=$id, nome='$nome')"
    }


}