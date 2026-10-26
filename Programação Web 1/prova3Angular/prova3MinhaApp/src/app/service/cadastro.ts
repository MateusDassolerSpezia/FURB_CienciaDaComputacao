import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pessoa } from '../interface/pessoa';

@Injectable({
  providedIn: 'root'
})
export class Cadastro {

  constructor(private httpClient: HttpClient) { }

  findById(pId: number): Observable<Pessoa> {
    return this.httpClient.get<Pessoa>('https://bu.furb.br/mcardoso/progWeb/apiRestAval.php/cadastro/' + pId);
  }

  deleteById(pId: number): Observable<any> {
    return this.httpClient.delete<any>('https://bu.furb.br/mcardoso/progWeb/apiRestAval.php/cadastro/' + pId);
  }

  updatePessoa(pId: number, pessoa: Pessoa): Observable<any> {
    return this.httpClient.put<any>('https://bu.furb.br/mcardoso/progWeb/apiRestAval.php/cadastro/' + pId, pessoa);
  }
}
