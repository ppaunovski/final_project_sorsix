import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthRequest } from '../model/AuthRequest';
import { Observable, Subject } from 'rxjs';
import { AuthResponse } from '../model/AuthResponse';
import { RegisterRequest } from '../model/RegisterRequest';
import { UserAccount } from '../model/UserAccount';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private url = '/api/auth';
  refreshAuth$ = new Subject<boolean>();

  constructor(private http: HttpClient) {}

  login(authRequest: AuthRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(
      `${this.url}`,
      JSON.stringify(authRequest),
      {
        headers: {
          'Content-Type': 'application/json',
        },
      }
    );
  }

  register(registerRequest: RegisterRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(
      `${this.url}/register`,
      JSON.stringify(registerRequest),
      {
        headers: {
          'Content-Type': 'application/json',
        },
      }
    );
  }
}
