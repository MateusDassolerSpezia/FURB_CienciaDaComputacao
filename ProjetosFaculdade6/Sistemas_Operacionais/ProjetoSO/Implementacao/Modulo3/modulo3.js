const algorithmButtons = [...document.querySelectorAll('.algorithm')];
const framesInput = document.querySelector('#frames');
const framesValue = document.querySelector('#frames-value');
const framePreview = document.querySelector('#frame-preview');
const runButton = document.querySelector('#run-button');
const statusElement = document.querySelector('#execution-status');
let selectedAlgorithm = 'fifo';

algorithmButtons.forEach((button) => {
  button.addEventListener('click', () => {
    selectedAlgorithm = button.dataset.algorithm;
    algorithmButtons.forEach((item) => {
      const selected = item === button;
      item.classList.toggle('selected', selected);
      item.setAttribute('aria-pressed', String(selected));
    });
  });
});

framesInput.addEventListener('input', updateFramePreview);

runButton.addEventListener('click', async () => {
  setStatus('running', 'Executando');
  runButton.disabled = true;
  document.querySelector('#terminal').textContent = 'mmu:~$ consultando tabela de páginas...';

  try {
    const query = new URLSearchParams({ algoritmo: selectedAlgorithm, quadros: framesInput.value });
    const response = await fetch(`/api/modulo3?${query}`, { method: 'POST', headers: { Accept: 'application/json' } });
    const result = await response.json();
    if (!response.ok) throw new Error(result.erro || 'Falha desconhecida.');

    document.querySelector('#result-title').textContent = result.titulo;
    document.querySelector('#result-description').textContent = result.descricao;
    document.querySelector('#terminal').textContent = result.eventos.join('\n');
    renderSummary(result.resumo);
    renderHistory(result.acessos);
    renderPageTable(result.tabelaPaginas);
    setStatus('success', 'Concluída');
  } catch (error) {
    document.querySelector('#terminal').textContent = `ERRO | ${error.message}`;
    setStatus('error', 'Falha');
  } finally {
    runButton.disabled = false;
    document.querySelector('#results').scrollIntoView({ behavior: 'smooth', block: 'start' });
  }
});

function updateFramePreview() {
  const amount = Number(framesInput.value);
  framesValue.textContent = `${amount} ${amount === 1 ? 'quadro' : 'quadros'}`;
  framePreview.replaceChildren();
  for (let index = 0; index < amount; index++) {
    const frame = document.createElement('span');
    frame.textContent = `Q${index}`;
    framePreview.append(frame);
  }
}

function renderSummary(items) {
  const summary = document.querySelector('#summary');
  summary.replaceChildren();
  Object.entries(items).forEach(([label, value]) => {
    const item = document.createElement('div');
    const term = document.createElement('dt');
    const detail = document.createElement('dd');
    term.textContent = label;
    detail.textContent = value;
    item.append(term, detail);
    summary.append(item);
  });
  summary.hidden = false;
}

function renderHistory(accesses) {
  const body = document.querySelector('#history');
  body.replaceChildren();
  accesses.forEach((access) => {
    const row = document.createElement('tr');
    row.className = access.resultado === 'PAGE FAULT' ? 'fault-row' : 'hit-row';
    addCell(row, `${access.indice}. ${access.enderecoVirtual}`);
    addCell(row, `P${access.pagina} + ${access.deslocamento}`);
    const resultCell = addCell(row, access.resultado);
    resultCell.className = access.resultado === 'PAGE FAULT' ? 'fault-label' : 'hit-label';

    const memoryCell = document.createElement('td');
    const memory = document.createElement('div');
    memory.className = 'memory-row';
    access.quadros.forEach((page, frameIndex) => {
      const slot = document.createElement('span');
      slot.innerHTML = `<small>Q${frameIndex}</small><b>${page < 0 ? '—' : `P${page}`}</b>`;
      if (frameIndex === access.quadro) slot.classList.add('accessed');
      memory.append(slot);
    });
    memoryCell.append(memory);
    row.append(memoryCell);
    addCell(row, String(access.enderecoFisico));
    body.append(row);
  });
}

function renderPageTable(pages) {
  const table = document.querySelector('#page-table');
  table.replaceChildren();
  pages.forEach((page) => {
    const item = document.createElement('article');
    item.className = page.presente ? 'resident' : 'swapped';
    const number = document.createElement('strong');
    number.textContent = `P${page.pagina}`;
    const state = document.createElement('span');
    state.textContent = page.presente ? `Quadro ${page.quadro}` : 'Swap';
    const bit = document.createElement('small');
    bit.textContent = `bit V = ${page.presente ? 1 : 0}`;
    item.append(number, state, bit);
    table.append(item);
  });
}

function addCell(row, value) {
  const cell = document.createElement('td');
  cell.textContent = value;
  row.append(cell);
  return cell;
}

function setStatus(type, text) {
  statusElement.className = `execution-status ${type}`;
  statusElement.innerHTML = '<i aria-hidden="true"></i>' + text;
}

updateFramePreview();
