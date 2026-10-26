class Pessoa {
    constructor(pNome, pSobrenome, pCidade) {
        this.nome = pNome;
        this.sobrenome = pSobrenome;
        this.cidade = pCidade;
    }

    nomeCompleto() {
        return this.nome + ' ' + this.sobrenome;
    }
}

class Aluno extends Pessoa {
    constructor(pNome, pSobrenome, pCidade, pCurso) {
        super(pNome, pSobrenome, pCidade);
        this.curso = pCurso;
    }

    infoCmpleta() {
        return this.nomeCompleto() + ', cursando ' + this.curso;
    }
}

class Professor extends Pessoa {
    constructor(pNome, pSobrenome, pCidade, pDepartamento) {
        super(pNome, pSobrenome, pCidade);
        this.departamento = pDepartamento;
    }

    infoCmpleta() {
        return this.nomeCompleto() + ', alocado no ' + this.curso;
    }
}