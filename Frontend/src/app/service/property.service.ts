import {
  HttpClient,
  HttpErrorResponse,
  HttpParams,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject, catchError, throwError } from 'rxjs';
import { Property } from '../model/property';
import { Review } from '../model/Review';
import { BookingRequest } from '../model/BookingRequest';
import { PropertyInfo } from '../model/PropertyInfo';
import { Booking } from '../model/Booking';
import { PropertyAvailability } from '../model/PropertyAvailability';
import { ReviewAveragesComponent } from '../components/review-averages/review-averages.component';
import { AverageRating } from '../model/AverageRating';

@Injectable({
  providedIn: 'root',
})
export class PropertyService {
  search$ = new Subject<any>();

  private url = '/api/properties';

  constructor(private http: HttpClient) {}

  private formatDate(date: Date) {
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0'); // January is 0!
    const day = String(date.getDate()).padStart(2, '0');

    return `${year}-${month}-${day}`;
  }

  getAllProperties(): Observable<PropertyInfo[]> {
    return this.http.get<PropertyInfo[]>(this.url);
  }

  getFilteredProperties(
    filterString: string,
    checkIn: string,
    checkOut: string,
    adults: string,
    children: string,
    pets: string
  ): Observable<PropertyInfo[]> {
    if (filterString && checkIn && checkOut && adults && children && pets)
      return this.http
        .get<PropertyInfo[]>(
          `${this.url}/search?filterString=${filterString}&checkIn=${checkIn}&checkOut=${checkOut}&adults=${adults}&children=${children}&pets=${pets}`
        )
        .pipe(catchError(this.handleError));
    return this.getAllProperties();
  }

  private handleError(error: HttpErrorResponse) {
    // Your error handling logic goes here
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      console.error(
        `Backend returned code ${error.status}, ` + `body was: ${error.error}`
      );
    }
    // Return an observable with a user-facing error message.
    return throwError('Something bad happened; please try again later.');
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

  bookProperty(
    id: Number,
    bookingRequest: BookingRequest
  ): Observable<Booking> {
    return this.http.post<Booking>(
      `${this.url}/${id}/book`,
      JSON.stringify(bookingRequest),
      {
        headers: {
          'Content-Type': 'application/json',
        },
      }
    );
  }

  getPropertyAvailability(id: Number): Observable<PropertyAvailability[]> {
    return this.http.get<PropertyAvailability[]>(
      `${this.url}/${id}/availability`
    );
  }

  getAverageRatings(id: Number): Observable<AverageRating[]> {
    return this.http.get<AverageRating[]>(
      `${this.url}/${id}/average-component-ratings`
    );
  }

  // getPropertiesByCity(city: String): Observable<Property[]>{
  //   return this.http.get<Property[]>(this.url + ``)
  // }
}
