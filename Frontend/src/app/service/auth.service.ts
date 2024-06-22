import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {AuthRequest} from '../model/AuthRequest';
import {catchError, Observable, of, Subject} from 'rxjs';
import {AuthResponse} from '../model/AuthResponse';
import {RegisterRequest} from '../model/RegisterRequest';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private url = '/api/auth';
  refreshAuth$ = new Subject<boolean>();

  constructor(private http: HttpClient) {
  }

  login(authRequest: AuthRequest): Observable<AuthResponse | undefined> {
    return this.http
      .post<AuthResponse>(`${this.url}`, JSON.stringify(authRequest), {
        headers: {
          'Content-Type': 'application/json',
        },
      })
      .pipe(catchError(this.handleError(undefined)));
  }

  handleError<T>(defValue: T): (error: any) => Observable<T> {
    return (error: any) => {
      console.log('HTTP Error', error);
      return of(defValue);
    };
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

  isAuthenticated(): Observable<Boolean> {
    return this.http.get<Boolean>(`${this.url}`);
  }
}
