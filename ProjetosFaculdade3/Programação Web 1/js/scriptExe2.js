//---------------------------------------------------------TELA01------------------------------------------------------------//

function soma() {
    var vNum1 = document.getElementById('num1');
    var vNum2 = document.getElementById('num2');
    var vElementoResultado = document.getElementById('resultado');
    var vResultado = document.createElement('div');
    vResultado.innerHTML = parseFloat(vNum1.value) + parseFloat(vNum2.value);
    if (vElementoResultado.children[0]) {
        vElementoResultado.removeChild(vElementoResultado.children[0]);
    }
    vElementoResultado.appendChild(vResultado);
}

function comparaNum() {
    var vNum1 = document.getElementById('num1');
    var vNum2 = document.getElementById('num2');
    var vElementoComparacao = document.getElementById('comparacao');
    var vComparacao = document.createElement('div');
    if (parseFloat(vNum1.value) > parseFloat(vNum2.value)) {
        vComparacao.innerHTML = "Primeiro número é maior";
        vComparacao.classList.add('vermelho');
    } else if (parseFloat(vNum1.value) < parseFloat(vNum2.value)) {
        vComparacao.innerHTML = "Segundo número é maior"
        vComparacao.classList.add('verde');
    } else {
        vComparacao.innerHTML = "Ambos são iguais"
        vComparacao.classList.add('amarelo');
    }
    if (vElementoComparacao.children[0]) {
        vElementoComparacao.removeChild(vElementoComparacao.children[0]);
    }
    vElementoComparacao.appendChild(vComparacao);
}

//---------------------------------------------------------TELA02------------------------------------------------------------//

function conteudo() {
    var vConteudo = document.getElementById('texto');
    var vElementoCopiado = document.getElementById('textoCopiado');
    var vCopia = document.createElement('div');
    vCopia.innerHTML = vConteudo.value;
    if (vElementoCopiado.children[0]) {
        vElementoCopiado.removeChild(vElementoCopiado.children[0]);
    }
    vElementoCopiado.appendChild(vCopia);
}

/*function conteudo() {
    var vConteudo = document.getElementById('texto').value;
    var vElementoCopiado = document.getElementById('textoCopiado');
    vElementoCopiado.textContent = vConteudo;
}*/

function tamanhoCampo() {
    var vConteudo = document.getElementById('texto');
    var vElementoTamanho = document.getElementById('tamanhoCampo');
    var vTamanho = document.createElement('div');
    vTamanho.innerHTML = 'O tamanho atual do campo é de: ' + vConteudo.value.length;
    if (vElementoTamanho.children[0]) {
        vElementoTamanho.removeChild(vElementoTamanho.children[0]);
    }
    vElementoTamanho.appendChild(vTamanho);
}

//---------------------------------------------------------TELA03------------------------------------------------------------//

var contador = 0;
setInterval(() =>  {
    document.getElementById('contador').innerHTML = "Contador: " + contador;
    contador++;
}, 1000);

function mudarCor(elemento) {
    elemento.classList.toggle('blocoVermelho');
    
}