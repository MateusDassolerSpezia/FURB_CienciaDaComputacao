const algorithmButtons = [...document.querySelectorAll('.algorithm')];
const quantumInput = document.querySelector('#quantum');
const quantumValue = document.querySelector('#quantum-value');
const parameterHelp = document.querySelector('#parameter-help');
const runButton = document.querySelector('#run-button');
const statusElement = document.querySelector('#execution-status');
let selectedAlgorithm = 'round-robin';

algorithmButtons.forEach((button) => {
  button.addEventListener('click', () => {
    selectedAlgorithm = button.dataset.algorithm;
    algorithmButtons.forEach((item) => {
      const selected = item === button;
      item.classList.toggle('selected', selected);
      item.setAttribute('aria-pressed', String(selected));
    });
    updateParameters();
  });
});

quantumInput.addEventListener('input', updateParameters);

runButton.addEventListener('click', async () => {
  setStatus('running', 'Executando');
  runButton.disabled = true;
  document.querySelector('#terminal').textContent = 'scheduler:~$ calculando estados e filas...';

  try {
    const query = new URLSearchParams({ algoritmo: selectedAlgorithm, quantum: quantumInput.value });
    const response = await fetch(`/api/modulo2?${query}`, { method: 'POST', headers: { Accept: 'application/json' } });
    const result = await response.json();
    if (!response.ok) throw new Error(result.erro || 'Falha desconhecida.');

    document.querySelector('#result-title').textContent = result.titulo;
    document.querySelector('#result-description').textContent = result.descricao;
    document.querySelector('#terminal').textContent = result.eventos.join('\n');
    renderSummary(result.resumo);
    renderTimeline(result.linhaTempo);
    renderMetrics(result.processos);
    setStatus('success', 'Concluída');
  } catch (error) {
    document.querySelector('#terminal').textContent = `ERRO | ${error.message}`;
    setStatus('error', 'Falha');
  } finally {
    runButton.disabled = false;
    document.querySelector('#results').scrollIntoView({ behavior: 'smooth', block: 'start' });
  }
});

function updateParameters() {
  const roundRobin = selectedAlgorithm === 'round-robin';
  quantumInput.disabled = !roundRobin;
  quantumValue.textContent = roundRobin ? `${quantumInput.value} u.t.` : 'Aging: 3 u.t.';
  parameterHelp.textContent = roundRobin
    ? `Cada processo usa até ${quantumInput.value} unidades de CPU antes de ser preemptado.`
    : 'A prioridade efetiva melhora a cada 3 unidades acumuladas na fila de prontos.';
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

function renderTimeline(ranges) {
  const timeline = document.querySelector('#timeline');
  const axis = document.querySelector('#time-axis');
  timeline.replaceChildren();
  axis.replaceChildren();

  ranges.forEach((range) => {
    const segment = document.createElement('div');
    const duration = range.fim - range.inicio;
    segment.className = `segment ${range.processo.toLowerCase()}`;
    segment.style.flexGrow = duration;
    segment.title = `${range.processo}: t=${range.inicio} até t=${range.fim}`;
    segment.innerHTML = `<strong>${range.processo}</strong><small>${range.inicio}–${range.fim}</small>`;
    timeline.append(segment);
  });

  const total = ranges.length ? ranges[ranges.length - 1].fim : 0;
  for (let time = 0; time <= total; time++) {
    const tick = document.createElement('span');
    tick.textContent = time;
    axis.append(tick);
  }
  document.querySelector('#timeline-empty').hidden = true;
  document.querySelector('#timeline-wrapper').hidden = false;
}

function renderMetrics(processes) {
  const body = document.querySelector('#metrics');
  body.replaceChildren();
  processes.forEach((process) => {
    const row = document.createElement('tr');
    [process.id, process.chegada, process.cpu, process.prioridade, process.espera,
      process.turnaround, process.resposta, process.termino].forEach((value, index) => {
      const cell = document.createElement('td');
      cell.textContent = value;
      if (index === 0) cell.className = `process-name ${String(value).toLowerCase()}`;
      row.append(cell);
    });
    body.append(row);
  });
}

function setStatus(type, text) {
  statusElement.className = `execution-status ${type}`;
  statusElement.innerHTML = '<i aria-hidden="true"></i>' + text;
}

updateParameters();
