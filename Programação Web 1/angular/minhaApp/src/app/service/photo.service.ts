import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Photo } from '../interface/photo';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PhotoService {

  constructor(private httpClient: HttpClient) { }

  updPhoto(pPhoto: Photo): Observable<Photo> {
    return this.httpClient.put<Photo>('https://jsonplaceholder.typicode.com/photos/' + pPhoto.id, pPhoto);
  }
}
