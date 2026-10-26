class Produto {
    constructor(pNome) {
        this.nome = pNome;
    }
}

class ProdutoPreco extends Produto{
    constructor(pNome, pPreco) {
        super(pNome);
        this.preco = pPreco;
    }
}

/*export*/ class ProdutoNF extends Produto {
    constructor(pNome, pNrNF) {
        super(pNome);
        this.nrNF = pNrNF;
    }
}

export {ProdutoPreco, ProdutoNF};