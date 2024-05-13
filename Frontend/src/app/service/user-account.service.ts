import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserAccount } from '../model/UserAccount';
import { PropertyInfo } from '../model/PropertyInfo';

@Injectable({
  providedIn: 'root',
})
export class UserAccountService {
  private url = '/api/users';

  constructor(private http: HttpClient) {}

  getUserInfo(): Observable<UserAccount | undefined> {
    return this.http.get<UserAccount | undefined>(`${this.url}`);
  }

  getAllPropertiesByHost(id: Number): Observable<PropertyInfo[]> {
    return this.http.get<PropertyInfo[]>(`${this.url}/${id}/properties`);
  }
}
