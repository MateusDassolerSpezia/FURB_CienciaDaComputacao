const exitButton = document.querySelector('#exit-button');

exitButton.addEventListener('click', async () => {
  const confirmed = window.confirm('Deseja encerrar o servidor do Mini-SO?');
  if (!confirmed) return;

  exitButton.disabled = true;
  try {
    const response = await fetch('/api/encerrar', { method: 'POST' });
    if (!response.ok) throw new Error('O servidor recusou a solicitação.');
    document.body.innerHTML = `
      <main class="shutdown">
        <div>
          <span class="shutdown-mark">OS</span>
          <h1>Sessão encerrada.</h1>
          <p>O núcleo local do Mini-SO foi finalizado. Esta aba já pode ser fechada com segurança.</p>
        </div>
      </main>`;
  } catch (error) {
    exitButton.disabled = false;
    window.alert(`Não foi possível encerrar: ${error.message}`);
  }
});
