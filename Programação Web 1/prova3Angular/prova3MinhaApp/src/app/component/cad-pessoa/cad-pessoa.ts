import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Pessoa } from '../../interface/pessoa';
import { Cadastro } from '../../service/cadastro';

@Component({
  selector: 'app-cad-pessoa',
  imports: [CommonModule, FormsModule],
  templateUrl: './cad-pessoa.html',
  styleUrl: './cad-pessoa.css'
})
export class CadPessoa {
  pessoa: Pessoa = {
    id: 0,
    nome: '',
    departamento: '',
    endereco: '',
    email: ''
  };

  mensagemStatus: string = '';
  mensagem: string = '';

  constructor(private cadastro: Cadastro) { }

  buscaPessoaId(): void {
    this.cadastro.findById(this.pessoa.id!).subscribe(
      {
        next: (pPessoa) => {
          if (pPessoa) {
            this.pessoa = pPessoa;
          } else {
            alert("Pessoa não encontrada!");
          }
        },
        error: (pError) => alert(pError.status + ' - ' + pError.message)
      }
    );
  }

  alteraPessoa(): void {
    this.cadastro.updatePessoa(this.pessoa.id!, this.pessoa).subscribe({
      next: (pResposta) => {
        this.mensagemStatus = pResposta.status;
        if (this.mensagem == "Alterado com sucesso!") {
          this.mensagem = '';

          setTimeout(() => {
            this.mensagem = "Alterado com sucesso!";
          }, 250);
        } else{
          this.mensagem = "Alterado com sucesso!";
        }
      },
      error: (pError) => alert(pError.status + ' - ' + pError.message)
    });
  }

  excluiPessoa(): void {
    this.cadastro.deleteById(this.pessoa.id!).subscribe({
      next: (pResposta) => {
        this.mensagemStatus = pResposta.status;
        this.mensagem = pResposta.mensagem;

        // limpa os campos se exclusão for bem-sucedida
        if (this.mensagemStatus === 'Ok') {
          this.pessoa = { id: 0, nome: '', departamento: '', endereco: '', email: '' };
        }
      },
      error: (pError) => {
        this.mensagemStatus = 'Erro';
        this.mensagem = 'Erro de conexão com a API';
      }
    });
  }
}
