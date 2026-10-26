import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Album } from '../interface/album';
import { Photo } from '../interface/photo';

@Injectable({
  providedIn: 'root'
})
export class AlbumService {

  constructor(private httpClient: HttpClient) { }

  findAll(): Observable<Album[]> {
    return this.httpClient.get<Album[]>('https://jsonplaceholder.typicode.com/albums');
  }

  findById(pId: number): Observable<Album> {
    return this.httpClient.get<Album>('https://jsonplaceholder.typicode.com/albums/' + pId);
  }

  updAlbum(pAlbum: Album): Observable<Album> {
    return this.httpClient.put<Album>('https://jsonplaceholder.typicode.com/albums/' + pAlbum.id, pAlbum);
  }

  insAlbum(pAlbum: Album): Observable<Album> {
    return this.httpClient.post<Album>('https://jsonplaceholder.typicode.com/albums', pAlbum);
  }

  delAlbum(pId: number): Observable<void> {
    return this.httpClient.delete<void>('https://jsonplaceholder.typicode.com/albums/' + pId);
  }

  getPhotos(pAlbumId: number): Observable<Photo[]> {
    return this.httpClient.get<Photo[]>('https://jsonplaceholder.typicode.com/albums/' + pAlbumId + '/photos');
  }
}