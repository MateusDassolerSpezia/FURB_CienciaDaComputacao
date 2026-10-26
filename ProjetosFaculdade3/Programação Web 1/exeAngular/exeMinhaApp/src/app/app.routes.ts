import { Routes } from '@angular/router';
import { Home } from './component/home/home';
import { Funcionarios } from './component/funcionarios/funcionarios';

export const routes: Routes = [
    {
        path: '',
        component: Home
    },
    {
        path: 'funcionarios',
        component: Funcionarios
    }
];
