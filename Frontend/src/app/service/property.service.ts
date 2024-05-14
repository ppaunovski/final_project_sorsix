import {
  HttpClient,
  HttpErrorResponse,
  HttpParams,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject, catchError, of, throwError } from 'rxjs';
import { Property } from '../model/property';
import { Review } from '../model/Review';
import { BookingRequest } from '../model/BookingRequest';
import { PropertyInfo } from '../model/PropertyInfo';
import { Booking } from '../model/Booking';
import { PropertyAvailability } from '../model/PropertyAvailability';
import { ReviewAveragesComponent } from '../components/review-averages/review-averages.component';
import { AverageRating } from '../model/AverageRating';
import { PropertyImage } from '../model/PropertyImage';
import { UserAccount } from '../model/UserAccount';
import { CityService } from './city.service';

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
        .pipe(catchError(this.handleError([])));
    return this.getAllProperties();
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

  reserveProperty(
    id: Number,
    bookingRequest: BookingRequest
  ): Observable<Booking> {
    return this.http.post<Booking>(
      `${this.url}/${id}/reserve`,
      JSON.stringify(bookingRequest),
      {
        headers: {
          'Content-Type': 'application/json',
        },
      }
    );
  }

  handleError<T>(defValue: T): (error: any) => Observable<T> {
    return (error: any) => {
      localStorage.removeItem('jwt');
      console.log('HTTP Error', error);
      return of(defValue);
    };
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
  createProperty(
    property: Property,
    images: PropertyImage[]
  ): Observable<Property> {
    property.id = 0;
    property.images = images;
    console.log('Final Property: ', property);
    return this.http.post<Property>(this.url, property);
  }

  getPropertyForReview(id: number): Observable<PropertyInfo> {
    return this.http.get<PropertyInfo>(`${this.url}/${id}/for-review`);
  }

  // reviewProperty(id: number): Observable<PropertyInfo> {}

  // getPropertiesByCity(city: String): Observable<Property[]>{
  //   return this.http.get<Property[]>(this.url + ``)
  // }
}
