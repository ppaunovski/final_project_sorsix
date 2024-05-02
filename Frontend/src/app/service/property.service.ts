import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Property } from '../model/property';
import { Review } from '../model/Review';
import { BookingRequest } from '../model/BookingRequest';
import { PropertyInfo } from '../model/PropertyInfo';

@Injectable({
  providedIn: 'root',
})
export class PropertyService {
  private url = '/api/properties';

  constructor(private http: HttpClient) {}

  getAllProperties(): Observable<PropertyInfo[]> {
    return this.http.get<PropertyInfo[]>(this.url);
  }

  getPropertyById(id: Number): Observable<Property | undefined> {
    return this.http.get<Property | undefined>(this.url + `/${id}`);
  }

  getPropertyReviewsByPropertyId(id: Number): Observable<Review[]> {
    return this.http.get<Review[]>(`${this.url}/${id}/reviews`);
  }

  getPropertyPricesForPeriod(id: Number, start: Date, end: Date) {
    this.http.get(`${this.url}/${id}/get-offer`);
  }

  bookProperty(id: Number, bookingRequest: BookingRequest) {
    return this.http.post(`${this.url}/${id}/book`, bookingRequest);
  }

  // getPropertiesByCity(city: String): Observable<Property[]>{
  //   return this.http.get<Property[]>(this.url + ``)
  // }
}
