import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, of, throwError } from 'rxjs';
import { UserAccount } from '../model/UserAccount';
import { PropertyInfo } from '../model/PropertyInfo';
import { UserImage } from '../model/UserImage';
import { ProfitsPerProperty } from '../model/ProfitsPerProperty';

@Injectable({
  providedIn: 'root',
})
export class UserAccountService {
  private url = '/api/users';

  constructor(private http: HttpClient) {}

  getUserInfo(): Observable<UserAccount | undefined> {
    return this.http
      .get<UserAccount | undefined>(`${this.url}`)
      .pipe(catchError(this.handleError(undefined)));
  }

  handleError<T>(defValue: T): (error: any) => Observable<T> {
    return (error: any) => {
      localStorage.removeItem('jwt');
      console.log('HTTP Error', error);
      return of(defValue);
    };
  }

  getAllPropertiesByHost(id: Number): Observable<PropertyInfo[]> {
    return this.http.get<PropertyInfo[]>(`${this.url}/${id}/properties`);
  }

  getProfileImage(id: Number): Observable<UserImage> {
    return this.http.get<UserImage>(`${this.url}/${id}/images`);
  }

  getProfitsPerProperty(): Observable<ProfitsPerProperty[]> {
    return this.http.get<ProfitsPerProperty[]>(`${this.url}/profits`);
  }
}
