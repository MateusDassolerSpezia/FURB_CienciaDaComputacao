const scenarioButtons = [...document.querySelectorAll('.scenario')];
const runButton = document.querySelector('#run-button');
const statusElement = document.querySelector('#execution-status');
let selectedScenario = 'reuso';

scenarioButtons.forEach((button) => {
  button.addEventListener('click', () => {
    selectedScenario = button.dataset.scenario;
    scenarioButtons.forEach((item) => {
      const selected = item === button;
      item.classList.toggle('selected', selected);
      item.setAttribute('aria-pressed', String(selected));
    });
  });
});

runButton.addEventListener('click', async () => {
  setStatus('running', 'Executando');
  runButton.disabled = true;
  document.querySelector('#terminal').textContent = 'fat:/$ formatando disco e aplicando operações...';
  try {
    const query = new URLSearchParams({ cenario: selectedScenario });
    const response = await fetch(`/api/modulo5?${query}`, { method: 'POST', headers: { Accept: 'application/json' } });
    const result = await response.json();
    if (!response.ok) throw new Error(result.erro || 'Falha desconhecida.');
    document.querySelector('#result-title').textContent = result.titulo;
    document.querySelector('#result-description').textContent = result.descricao;
    document.querySelector('#terminal').textContent = result.eventos.join('\n');
    renderSummary(result.resumo);
    renderDisk(result.blocos);
    renderBitmap(result.bitmap);
    renderDirectory(result.entradas);
    renderFat(result.blocos);
    setStatus('success', 'Íntegro');
  } catch (error) {
    document.querySelector('#terminal').textContent = `ERRO | ${error.message}`;
    setStatus('error', 'Falha');
  } finally {
    runButton.disabled = false;
    document.querySelector('#results').scrollIntoView({ behavior: 'smooth', block: 'start' });
  }
});

function renderSummary(items) {
  const summary = document.querySelector('#summary');
  summary.replaceChildren();
  Object.entries(items).forEach(([label, value]) => {
    const item = document.createElement('div');
    const term = document.createElement('dt'); const detail = document.createElement('dd');
    term.textContent = label; detail.textContent = value; item.append(term, detail); summary.append(item);
  });
  summary.hidden = false;
}

function renderDisk(blocks) {
  const map = document.querySelector('#disk-map');
  map.replaceChildren();
  blocks.forEach((block) => {
    const item = document.createElement('article');
    item.className = block.estado.toLowerCase();
    item.title = `Bloco ${block.numero} · ${block.estado} · ${block.proprietario}`;
    const number = document.createElement('strong'); number.textContent = block.numero;
    const next = document.createElement('small'); next.textContent = block.proximo;
    item.append(number, next); map.append(item);
  });
}

function renderBitmap(bitmap) {
  const container = document.querySelector('#bitmap');
  container.replaceChildren();
  bitmap.forEach((occupied, index) => {
    const bit = document.createElement('span'); bit.textContent = occupied ? '1' : '0';
    bit.className = occupied ? 'one' : 'zero'; bit.title = `Bloco ${index}: ${occupied ? 'ocupado' : 'livre'}`;
    container.append(bit);
  });
}

function renderDirectory(entries) {
  const body = document.querySelector('#directory'); body.replaceChildren();
  entries.forEach((entry) => {
    const row = document.createElement('tr');
    addCell(row, entry.caminho, 'path'); addCell(row, entry.tipo);
    addCell(row, entry.tipo === 'Diretório' ? '—' : `${entry.tamanho} B`);
    addCell(row, entry.primeiroBloco < 0 ? '—' : String(entry.primeiroBloco));
    addCell(row, entry.cadeia.length ? `${entry.cadeia.join(' → ')} → EOF` : '—', 'chain');
    body.append(row);
  });
}

function renderFat(blocks) {
  const body = document.querySelector('#fat'); body.replaceChildren();
  blocks.forEach((block) => {
    const row = document.createElement('tr'); row.className = block.estado.toLowerCase();
    addCell(row, String(block.numero)); addCell(row, block.estado);
    addCell(row, block.proximo, 'next'); addCell(row, block.proprietario, 'owner'); body.append(row);
  });
}

function addCell(row, value, className = '') {
  const cell = document.createElement('td'); cell.textContent = value; cell.className = className; row.append(cell);
}

function setStatus(type, text) {
  statusElement.className = `execution-status ${type}`;
  statusElement.innerHTML = '<i aria-hidden="true"></i>' + text;
}
