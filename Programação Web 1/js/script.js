document.getElementById('elemento_h3').innerHTML = 'Conteúdo inicial do elemento';

function alteraH3() {
    var vElemento = document.getElementById('elemento_h3');
    vElemento.innerHTML = 'Conteúdo alterado';
}

function alteraEstilo() {
    var vElemento = document.getElementById('elemento_h3');
    vElemento.style.textAlign = 'right';
}

function ativaClasse1() {
    document.getElementById('elemento_h3').classList.add('classe1');
}

function ativaClasse2() {
    document.getElementById('elemento_h3').classList.add('classe2');
}

function criaParagrafo() {
    var vNovoParagrafo = document.createElement('p');
    vNovoParagrafo.innerHTML = 'Novo parágrafo';
    var vElementoPai = document.getElementById('novoP');
    vElementoPai.appendChild(vNovoParagrafo);
}

function criaTarefa() {
    var vElementoInput = document.getElementById('itemTarefa');
    var vElementoLista = document.getElementById('listaTarefas');
    var vItemLista = document.createElement('li');
    vItemLista.innerHTML = vElementoInput.value;
    vElementoLista.appendChild(vItemLista);

    vElementoInput.value = '';
    vElementoInput.focus();
}

function criaInicio() {
    var vElementoInput = document.getElementById('itemTarefa');
    var vElementoLista = document.getElementById('listaTarefas');
    var vItemLista = document.createElement('li');
    vItemLista.innerHTML = vElementoInput.value;

    vElementoLista.insertBefore(vItemLista, vElementoLista.firstChild);

    vElementoInput.value = '';
    vElementoInput.focus();
}

function removeItem() {
    var vElementoPai = document.getElementById('listaTarefas');
    vElementoPai.removeChild(vElementoPai.lastChild);
}

function removeTerceiro() {
    var vElementoPai = document.getElementById('listaTarefas');
    vElementoPai.removeChild(vElementoPai.children[2]);
}

function insereLinha() {
    var vElementoTable = document.getElementById('tabela');

    /*var vElementoLinha = document.createElement('tr');
    var vElementoTD1 = document.createElement('td');
    var vElementoTD2 = document.createElement('td');
    var vElementoTD3 = document.createElement('td');
    vElementoTD1.innerHTML = 'Cell 1';
    vElementoTD2.innerHTML = 'Cell 2';
    vElementoTD3.innerHTML = 'Cell 3';
    vElementoLinha.appendChild(vElementoTD1);
    vElementoLinha.appendChild(vElementoTD2);
    vElementoLinha.appendChild(vElementoTD3);
    vElementoTable.appendChild(vElementoLinha);*/

    var vElementoLinha = vElementoTable.insertRow();
    var vElementoTD1 = vElementoLinha.insertCell();
    var vElementoTD2 = vElementoLinha.insertCell();
    var vElementoTD3 = vElementoLinha.insertCell();
    vElementoTD1.innerHTML = 'Cell 1';
    vElementoTD2.innerHTML = 'Cell 2';
    vElementoTD3.innerHTML = 'Cell 3';
}

function removeLinha() {
    var vElementoTabela = document.getElementById('tabela');
    vElementoTabela.deleteRow(-1);
}