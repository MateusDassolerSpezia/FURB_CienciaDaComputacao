const experimentButtons = [...document.querySelectorAll('.experiment')];
const scenarioSelect = document.querySelector('#scenario');
const scenarioHelp = document.querySelector('#scenario-help');
const runButton = document.querySelector('#run-button');
const statusElement = document.querySelector('#execution-status');
let selectedExperiment = 'banqueiro';

const scenarios = {
  banqueiro: [
    ['seguro', 'Solicitação segura — P1 pede [1, 0, 2]', 'A concessão permite que todos os processos terminem em uma sequência segura.'],
    ['inseguro', 'Solicitação insegura — P4 pede [3, 3, 0]', 'A concessão consumiria recursos essenciais e será revertida pelo Banqueiro.']
  ],
  grafo: [
    ['com-ciclo', 'Grafo com dependência circular', 'P1 e P2 aguardam recursos mantidos um pelo outro.'],
    ['sem-ciclo', 'Grafo sem dependência circular', 'As dependências formam uma cadeia, mas não retornam ao ponto inicial.']
  ]
};

experimentButtons.forEach((button) => {
  button.addEventListener('click', () => {
    selectedExperiment = button.dataset.experiment;
    experimentButtons.forEach((item) => {
      const selected = item === button;
      item.classList.toggle('selected', selected);
      item.setAttribute('aria-pressed', String(selected));
    });
    updateScenarios();
  });
});

scenarioSelect.addEventListener('change', updateScenarioHelp);

runButton.addEventListener('click', async () => {
  setStatus('running', 'Analisando');
  runButton.disabled = true;
  document.querySelector('#terminal').textContent = 'deadlock:~$ construindo estado e verificando dependências...';

  try {
    const query = new URLSearchParams({ experimento: selectedExperiment, cenario: scenarioSelect.value });
    const response = await fetch(`/api/modulo4?${query}`, { method: 'POST', headers: { Accept: 'application/json' } });
    const result = await response.json();
    if (!response.ok) throw new Error(result.erro || 'Falha desconhecida.');

    document.querySelector('#result-title').textContent = result.titulo;
    document.querySelector('#result-description').textContent = result.descricao;
    document.querySelector('#terminal').textContent = result.eventos.join('\n');
    renderSummary(result.resumo);
    document.querySelector('#banker-result').hidden = result.tipo !== 'banqueiro';
    document.querySelector('#graph-result').hidden = result.tipo !== 'grafo';
    if (result.tipo === 'banqueiro') renderBanker(result.banqueiro);
    else renderGraph(result.grafo);
    setStatus(result.tipo === 'banqueiro' ? (result.banqueiro.aprovada ? 'success' : 'warning')
      : (result.grafo.deadlock ? 'error' : 'success'), 'Concluída');
  } catch (error) {
    document.querySelector('#terminal').textContent = `ERRO | ${error.message}`;
    setStatus('error', 'Falha');
  } finally {
    runButton.disabled = false;
    document.querySelector('#results').scrollIntoView({ behavior: 'smooth', block: 'start' });
  }
});

function updateScenarios() {
  scenarioSelect.replaceChildren();
  scenarios[selectedExperiment].forEach(([value, label]) => {
    const option = document.createElement('option');
    option.value = value;
    option.textContent = label;
    scenarioSelect.append(option);
  });
  updateScenarioHelp();
}

function updateScenarioHelp() {
  const selected = scenarios[selectedExperiment].find(([value]) => value === scenarioSelect.value);
  scenarioHelp.textContent = selected ? selected[2] : '';
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

function renderBanker(data) {
  document.querySelector('#request-label').textContent = `${data.solicitante} pede [${data.solicitacao.join(', ')}]`;
  document.querySelector('#available-label').textContent = `[${data.disponivel.join(', ')}]`;
  const badge = document.querySelector('#decision-badge');
  badge.textContent = data.aprovada ? 'APROVADA' : 'NEGADA';
  badge.className = `decision-badge ${data.aprovada ? 'approved' : 'denied'}`;

  const matrices = document.querySelector('#matrices');
  matrices.replaceChildren();
  [['Allocation', data.alocacao], ['Max', data.maximo], ['Need', data.necessidade]].forEach(([title, matrix]) => {
    const wrapper = document.createElement('div');
    const heading = document.createElement('h4');
    heading.textContent = title;
    wrapper.append(heading, createMatrix(data.processos, data.recursos, matrix));
    matrices.append(wrapper);
  });

  renderPath(document.querySelector('#safe-sequence'), data.sequenciaSegura,
    data.estadoSeguro ? 'Todos podem finalizar' : `Impedidos: ${data.impedidos.join(', ')}`,
    data.estadoSeguro ? 'safe' : 'danger');
}

function createMatrix(processes, resources, values) {
  const table = document.createElement('table');
  const head = document.createElement('thead');
  const headRow = document.createElement('tr');
  ['Processo', ...resources].forEach((label) => {
    const cell = document.createElement('th'); cell.textContent = label; headRow.append(cell);
  });
  head.append(headRow); table.append(head);
  const body = document.createElement('tbody');
  values.forEach((row, index) => {
    const tr = document.createElement('tr');
    [processes[index], ...row].forEach((value) => {
      const cell = document.createElement('td'); cell.textContent = value; tr.append(cell);
    });
    body.append(tr);
  });
  table.append(body);
  return table;
}

function renderGraph(data) {
  const svg = document.querySelector('#resource-graph');
  svg.replaceChildren();
  const ns = 'http://www.w3.org/2000/svg';
  const defs = document.createElementNS(ns, 'defs');
  const marker = document.createElementNS(ns, 'marker');
  marker.setAttribute('id', 'arrow'); marker.setAttribute('viewBox', '0 0 10 10');
  marker.setAttribute('refX', '9'); marker.setAttribute('refY', '5'); marker.setAttribute('markerWidth', '7');
  marker.setAttribute('markerHeight', '7'); marker.setAttribute('orient', 'auto-start-reverse');
  const arrow = document.createElementNS(ns, 'path'); arrow.setAttribute('d', 'M 0 0 L 10 5 L 0 10 z');
  marker.append(arrow); defs.append(marker); svg.append(defs);

  const positions = {};
  data.processos.forEach((id, index) => { positions[id] = { x: 105, y: 75 + index * 105, type: 'process' }; });
  data.recursos.forEach((id, index) => { positions[id] = { x: 495, y: 75 + index * 105, type: 'resource' }; });
  const cycleEdges = new Set();
  for (let index = 0; index < data.ciclo.length - 1; index++) {
    cycleEdges.add(`${data.ciclo[index]}→${data.ciclo[index + 1]}`);
  }

  data.arestas.forEach((edge) => {
    const from = positions[edge.origem]; const to = positions[edge.destino];
    const line = document.createElementNS(ns, 'line');
    const direction = to.x > from.x ? 1 : -1;
    line.setAttribute('x1', from.x + direction * 39); line.setAttribute('y1', from.y);
    line.setAttribute('x2', to.x - direction * 45); line.setAttribute('y2', to.y);
    line.setAttribute('marker-end', 'url(#arrow)');
    line.setAttribute('class', cycleEdges.has(`${edge.origem}→${edge.destino}`) ? 'cycle-edge' : 'graph-edge');
    svg.append(line);
  });

  Object.entries(positions).forEach(([id, position]) => {
    const shape = document.createElementNS(ns, position.type === 'process' ? 'circle' : 'rect');
    if (position.type === 'process') {
      shape.setAttribute('cx', position.x); shape.setAttribute('cy', position.y); shape.setAttribute('r', '36');
    } else {
      shape.setAttribute('x', position.x - 36); shape.setAttribute('y', position.y - 36);
      shape.setAttribute('width', '72'); shape.setAttribute('height', '72'); shape.setAttribute('rx', '8');
    }
    shape.setAttribute('class', `graph-node ${position.type}`); svg.append(shape);
    const text = document.createElementNS(ns, 'text');
    text.setAttribute('x', position.x); text.setAttribute('y', position.y + 5); text.setAttribute('class', 'node-label');
    text.textContent = id; svg.append(text);
  });

  renderPath(document.querySelector('#cycle-path'), data.ciclo,
    data.deadlock ? 'Dependência circular confirmada' : 'Nenhum ciclo encontrado',
    data.deadlock ? 'danger' : 'safe');
}

function renderPath(container, items, emptyText, type) {
  container.replaceChildren(); container.className = `safe-sequence ${type}`;
  if (!items.length) { const message = document.createElement('p'); message.textContent = emptyText; container.append(message); return; }
  items.forEach((item, index) => {
    const node = document.createElement('span'); node.textContent = item; container.append(node);
    if (index < items.length - 1) { const arrow = document.createElement('b'); arrow.textContent = '→'; container.append(arrow); }
  });
  const note = document.createElement('small'); note.textContent = emptyText; container.append(note);
}

function setStatus(type, text) {
  statusElement.className = `execution-status ${type}`;
  statusElement.innerHTML = '<i aria-hidden="true"></i>' + text;
}

updateScenarios();
