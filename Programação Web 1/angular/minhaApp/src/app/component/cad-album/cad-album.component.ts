import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Album } from '../../interface/album';
import { AlbumService } from '../../service/album.service';
import { FormsModule } from '@angular/forms';
import { Photo } from '../../interface/photo';
import { PhotoService } from '../../service/photo.service';

@Component({
  selector: 'app-cad-album',
  imports: [CommonModule, FormsModule],
  templateUrl: './cad-album.component.html',
  styleUrl: './cad-album.component.css'
})
export class CadAlbumComponent {
  vetAlbum: Album[] = [];
  vetPhotos: Photo[] = [];

  album: Album = {
    id: 0,
    userId: 0,
    title: ''
  };

  album2: Album = {
    id: 0,
    userId: 0,
    title: ''
  };

  photo: Photo = {
    id: 0,
    albumId: 0,
    title: '',
    url: '',
    thumbnailUrl: ''
  }

  constructor(private albumService: AlbumService, private photoService: PhotoService) {}

  buscaAlbuns(): void {
    /*this.vetAlbum = */this.albumService.findAll().subscribe(
      (pVetAlbum) => this.vetAlbum = pVetAlbum
    );
  }

  editaAlbum(pAlbum: Album):void {
    this.album = pAlbum;
  }

  buscaAlbumId(): void {
    this.albumService.findById(this.album.id!).subscribe(
      //(pAlbum) => this.album = pAlbum
      {
        next: (pAlbum) => this.album = pAlbum,
        error: (pError) => alert(pError.status + ' - ' + pError.message) //console.log(pError)
      }
    );
  }

  alteraAlbum(): void {
    this.albumService.updAlbum(this.album).subscribe(
      //(pAlbum) => this.album2 = pAlbum,
      {
        next: (pAlbum) => this.album2 = pAlbum,
        error: (pError) => alert(pError.status + ' - ' + pError.message) //console.log(pError)
      }
    );
  }

  insereAlbum(): void {
    this.albumService.insAlbum(this.album).subscribe ({
      next: (pAlbum) => this.album2 = pAlbum,
      error: (pError) => alert(pError.status + ' - ' + pError.message) //console.log(pError) 
    });
  }

  excluiAlbum(): void {
    this.albumService.delAlbum(this.album.id!).subscribe({
      next: () => alert('Excluido com sucesso'),
      error: (pError) => alert(pError.status + ' - ' + pError.message) //console.log(pError)
    });
  }

  carregaFotos(): void {
    this.albumService.getPhotos(this.album.id!).subscribe({
      next: (pVetFotos) => this.vetPhotos = pVetFotos,
      error: (pError) => alert(pError.status + ' - ' + pError.message) //console.log(pError)
    });
  }

  editarFoto(pFoto: Photo): void {
    this.photo = pFoto;
  }

  updPhoto(): void {
    this.photoService.updPhoto(this.photo).subscribe({
      next: (pPhoto) => {
        this.photo = pPhoto
        alert('Foto alterada com sucesso')
      }
    });
  }
}
