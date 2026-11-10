const experiments = [...document.querySelectorAll('.experiment')];
const runButton = document.querySelector('#run-button');
const statusElement = document.querySelector('#execution-status');
const terminal = document.querySelector('#terminal code');
const summary = document.querySelector('#summary');
const description = document.querySelector('#result-description');
let selectedSimulation = 'produtor-consumidor';

experiments.forEach((experiment) => {
  experiment.addEventListener('click', () => {
    selectedSimulation = experiment.dataset.simulation;
    experiments.forEach((item) => {
      const selected = item === experiment;
      item.classList.toggle('selected', selected);
      item.setAttribute('aria-pressed', String(selected));
    });
  });
});

runButton.addEventListener('click', async () => {
  setStatus('running', 'Executando');
  runButton.disabled = true;
  terminal.innerHTML = '<span class="prompt">mini-so:~$</span> iniciando threads...';
  summary.hidden = true;

  try {
    const response = await fetch(`/api/modulo1?simulacao=${encodeURIComponent(selectedSimulation)}`, {
      method: 'POST',
      headers: { Accept: 'application/json' }
    });
    const result = await response.json();
    if (!response.ok) throw new Error(result.erro || 'Falha desconhecida.');

    description.textContent = result.descricao;
    terminal.textContent = result.eventos.join('\n');
    renderSummary(result.resumo);
    document.querySelector('#output-title').textContent = result.titulo;
    setStatus('success', 'Concluída');
  } catch (error) {
    terminal.textContent = `ERRO | ${error.message}`;
    description.textContent = 'O núcleo Java não conseguiu concluir o experimento.';
    setStatus('error', 'Falha');
  } finally {
    runButton.disabled = false;
    document.querySelector('#output').scrollIntoView({ behavior: 'smooth', block: 'start' });
  }
});

function renderSummary(items) {
  summary.replaceChildren();
  Object.entries(items).forEach(([label, value]) => {
    const container = document.createElement('div');
    const term = document.createElement('dt');
    const detail = document.createElement('dd');
    term.textContent = label;
    detail.textContent = value;
    container.append(term, detail);
    summary.append(container);
  });
  summary.hidden = false;
}

function setStatus(type, text) {
  statusElement.className = `execution-status ${type}`;
  statusElement.innerHTML = '<i aria-hidden="true"></i> ' + text;
}
