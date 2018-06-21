package informatica.sp.senai.br.ianesapp.model

class ItemPatrimonio {

    var id: Long = 0
    var patrimonio: Patrimonio? = null
    var categoria: Patrimonio? = null
    var ambienteAtual: Ambiente? = null

    constructor()

    constructor(patrimonio: Patrimonio) {
        this.patrimonio = patrimonio
    }

    constructor(patrimonio: Patrimonio, categoria: Patrimonio) {
        this.patrimonio = patrimonio
        this.categoria = categoria
    }

    constructor(patrimonio: Patrimonio, categoria: Patrimonio, ambiente: Ambiente) {
        this.patrimonio = patrimonio
        this.categoria = categoria
        this.ambienteAtual = ambiente
    }

    constructor(id: Long, patrimonio: Patrimonio, categoria: Patrimonio, ambiente: Ambiente) {
        this.id = id
        this.patrimonio = patrimonio
        this.categoria = categoria
        this.ambienteAtual = ambiente
    }

}