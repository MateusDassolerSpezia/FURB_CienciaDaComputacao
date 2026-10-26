var x: number = 1;
//x = 'Blumenau';
var vNome: string = 'Blumenau';
var vFlag: boolean;
vFlag = false;
var vQualquercoisa: any = 1;
vQualquercoisa = false;
vQualquercoisa = [1, true, {nome: "Marcos"}];
var vRestrito: string | number;
vRestrito = 'Itajaí';
vRestrito = 1;
//vRestrito = false;
type Cidade = "Blumenau" | "Gaspar" | "Timbó";
var vCidade: Cidade = "Blumenau";
//vCidade = "Indaial";
type Variado = 'Marcos'| 5 | false;
var vVariado: Variado;
//vVariado = true;

class Pessoa {
    private nome: string;
    sobrenome: string;
    cidade: string;
    constructor(pNome: string, pSobrenome: string, pCidade: string) {
        this.nome = pNome;
        this.sobrenome = pSobrenome;
        this.cidade = pCidade;
    }
    setNome(pNome: string) {
        this.nome = pNome;
    }
    getNome() {
        return this.nome;
    }
}

interface Pessoa2 {
    nome: string,
    sobrenome: string,
    cidade: string
};

var vPessoa2: Pessoa2 = {
    nome: "Marcos",
    sobrenome: "Cardoso",
    cidade: "Blumenau"
};

var vPessoa: Pessoa = new Pessoa('Marcos', 'Cardoso', 'Blumenau');
//vPessoa.nome = 'José';
vPessoa.setNome('José');
//console.log(vPessoa.nome);
console.log(vPessoa.getNome());

function mostraConsole(pMensagem: any): void {
    console.log(pMensagem);
}

function fDuplica(pNumero: number): number {
    return pNumero * 2;
}

function fFuncao(pParam: string | number): string {
    var vRetorno: string = '';

    if (typeof pParam === 'string') {
        vRetorno = pParam + " string";
    }
    if (typeof pParam === 'number') {
        vRetorno = pParam + " number";
    }

    return vRetorno;
}

console.log(fFuncao(1));
console.log(fFuncao('Blumenau'));

mostraConsole('Olá, mundo!');
mostraConsole(fDuplica(3));

var vObjeto: object;
vObjeto = new Pessoa("Dalton", 'Reis', 'Blumenau');
vObjeto = {carro: "Passat", ano: "1988"};
var vVetor: Array<number> = [1, 2, 3, 4];
//vVetor.push('Marcos');
console.log(vVetor);

var vVetor2: Array<number | string>;
var vVetor3: Array<boolean | Cidade>;
var vetPessoas: Array<Pessoa>;
vVetor2 = [1, 2, 3];
vVetor2.push("Marcos");
vVetor3 = [true, "Blumenau", false];
//vVetor3.push("Ascurra");

interface Generica<Tipo> {
    propriedade: Tipo;
}

var vGenerica: Generica<number>;
//vGenerica.propriedade = 5;
vGenerica = {propriedade: 5};
console.log(vGenerica);
var vGenerica2: Generica<number | string>;
vGenerica2 = {propriedade: 5};
vGenerica2 = {propriedade: "Blumenau"};
var vGenerica3: Generica<Array<string>>;
vGenerica3 = {propriedade: ['Marcos', 'Cardoso', 'Blumenau']};
var vGenerica4: Generica<Array<Pessoa>>;
vGenerica4 = {
    propriedade: [
        new Pessoa("Marcos", 'Cardoso', "Blumenau"),
        new Pessoa("Dalton", 'Reis', "Blumenau"),
        new Pessoa("Alexander", 'Valdameri', "Blumenau")
    ]
}
console.log(vGenerica4);