import { Component } from '@angular/core';
import { Pessoa } from '../../interface/pessoa';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-cadastro',
  imports: [CommonModule],
  templateUrl: './cadastro.component.html',
  styleUrl: './cadastro.component.css'
})
export class CadastroComponent {
  pessoa: Pessoa = {
    nome: "Marcos",
    sobrenome: "Cardoso",
    cidade: "Blumenau"
  }

  vetPessoas: Pessoa[] = [
    {
      nome: "Marcos",
      sobrenome: "Cardoso",
      cidade: "Blumenau"
    },
    {
      nome: "Dalton",
      sobrenome: "Reis",
      cidade: "Blumenau"
    },
    {
      nome: "Alexander",
      sobrenome: "Valdameri",
      cidade: "Joinville"
    }
  ]

  mensagem: string = '';
  tamanhoString(pString: string): void {
    this.mensagem = pString + " possui " + pString.length + " caracteres"
  }
}
