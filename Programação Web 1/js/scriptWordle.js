const secretWord = "letra"; 
let gameOver = false;

function submitGuess() {
  if (gameOver) return;

  const input = document.getElementById("guessInput");
  const guess = input.value.toLowerCase();

  if (guess.length !== 5) {
    alert("Digite uma palavra de 5 letras.");
    return;
  }

  const resultDiv = document.getElementById("results");
  const attemptDiv = document.createElement("div");
  attemptDiv.className = "attempt";

  const secretArray = secretWord.split('');
  const guessArray = guess.split('');
  const status = Array(5).fill('');

  // Marcar letras corretas (verde)
  for (let i = 0; i < 5; i++) {
    if (guessArray[i] === secretArray[i]) {
      status[i] = 'correct';
      secretArray[i] = null;
    }
  }

  // Marcar letras presentes (amarelo)
  for (let i = 0; i < 5; i++) {
    if (!status[i] && secretArray.includes(guessArray[i])) {
      status[i] = 'present';
      secretArray[secretArray.indexOf(guessArray[i])] = null;
    }
  }

  // Mostrar resultado
  for (let i = 0; i < 5; i++) {
    const span = document.createElement("div");
    span.className = "letter";
    span.textContent = guess[i];
    if (status[i] === 'correct') {
      span.classList.add('correct');
    } else if (status[i] === 'present') {
      span.classList.add('present');
    }
    attemptDiv.appendChild(span);
  }

  resultDiv.appendChild(attemptDiv);
  input.value = "";

  if (guess === secretWord) {
    document.getElementById("message").textContent = "Palavra correta!";
    gameOver = true;
  }

  input.focus();

}

function resetGame() {
  document.getElementById("results").innerHTML = "";
  document.getElementById("message").textContent = "";
  document.getElementById("guessInput").value = "";
  gameOver = false;
}
