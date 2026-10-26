import { Routes } from '@angular/router';
import { HomeComponent } from './component/home/home.component';
import { CadastroComponent } from './component/cadastro/cadastro.component';
import { CadAlbumComponent } from './component/cad-album/cad-album.component';

export const routes: Routes = [
    {
        path: '',
        component: HomeComponent
    },
    {
        path: 'cadastro',
        component: CadastroComponent
    },
    {
        path: 'cadAlbum',
        component: CadAlbumComponent
    }
];
