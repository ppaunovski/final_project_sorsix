import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PropertyInfo } from '../model/PropertyInfo';

@Injectable({
  providedIn: 'root',
})
export class FavoriteService {
  url = '/api/favorite/';

  constructor(private http: HttpClient) {}

  getFavorites(): Observable<PropertyInfo[]> {
    return this.http.get<PropertyInfo[]>(`${this.url}/all`);
  }

  addFavorite(propertyId: number) {
    return this.http.post(`${this.url}save/${propertyId}`, null);
  }

  removeFavorite(propertyId: number):Observable<number> {
    return this.http.delete<number>(`${this.url}delete/${propertyId}`);
  }

  isFavorite(propertyId: number): Observable<boolean> {
    return this.http.get<boolean>(`${this.url}isFavorite/${propertyId}`);
  }
}
