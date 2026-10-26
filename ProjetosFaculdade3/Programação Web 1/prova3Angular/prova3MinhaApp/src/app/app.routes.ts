import { Routes } from '@angular/router';
import { Home } from './component/home/home';
import { Funcionarios } from './component/funcionarios/funcionarios';
import { CadPessoa } from './component/cad-pessoa/cad-pessoa';

export const routes: Routes = [
    {
        path: '',
        component: Home
    },
    {
        path: 'funcionarios',
        component: Funcionarios
    },
    {
        path: 'cadPessoa',
        component: CadPessoa
    }
];
