import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Booking } from '../model/Booking';
import { Observable } from 'rxjs';
import { BookingRequest } from '../model/BookingRequest';
import { BookingForReview } from '../model/BookingForReview';
import { BookingResponse } from '../model/BookingResponse';

@Injectable({
  providedIn: 'root',
})
export class BookingService {
  private url = '/api/booking';
  constructor(private http: HttpClient) {}

  getBookingsByUserId(page: number, size: number): Observable<BookingResponse> {
    return this.http.get<BookingResponse>(
      `${this.url}/user?page=${page}&size=${size}`
    );
  }

  getBookingById(id: number): Observable<Booking | undefined> {
    return this.http.get<Booking | undefined>(`${this.url}/${id}`);
  }

  confirmBooking(id: Number): Observable<Booking> {
    return this.http.post<Booking>(`${this.url}/${id}/confirm`, {});
  }

  cancelBooking(id: Number): Observable<Booking> {
    return this.http.post<Booking>(`${this.url}/${id}/cancel`, {});
  }
  getBookingForReview(id: number): Observable<BookingForReview> {
    return this.http.get<BookingForReview>(`${this.url}/${id}/for-review`);
  }
}
