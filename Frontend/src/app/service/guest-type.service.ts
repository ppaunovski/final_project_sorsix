import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GuestType } from '../model/GuestType';

@Injectable({
  providedIn: 'root',
})
export class GuestTypeService {
  private url = '/api/guest-type';
  constructor(private http: HttpClient) {}

  getAllGuestTypes(): Observable<GuestType[]> {
    return this.http.get<GuestType[]>(this.url);
  }
}
