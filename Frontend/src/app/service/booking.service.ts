import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class BookingService {
  private url = '/api/bookings';
  constructor(private http: HttpClient) {}
}
