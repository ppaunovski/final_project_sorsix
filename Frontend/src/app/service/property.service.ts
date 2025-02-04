import {HttpClient,} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {catchError, Observable, of, Subject} from 'rxjs';
import {Property} from '../model/property';
import {Review} from '../model/Review';
import {BookingRequest} from '../model/BookingRequest';
import {PropertyInfo} from '../model/PropertyInfo';
import {Booking} from '../model/Booking';
import {PropertyAvailability} from '../model/PropertyAvailability';
import {AverageRating} from '../model/AverageRating';
import {PropertyImage} from '../model/PropertyImage';
import {Attribute} from '../model/Attribute';
import {PropertyResponse} from '../model/PropertyResponse';

@Injectable({
  providedIn: 'root',
})
export class PropertyService {
  search$ = new Subject<any>();
  showMap$ = new Subject<boolean>();

  private url = '/api/properties';

  constructor(private http: HttpClient) {
  }

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
    if (filterString && adults && children && pets)
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
    images: PropertyImage[],
    attributes: Attribute[]
  ): Observable<Property> {
    property.id = 0;
    property.images = images;
    property.attributes = attributes;
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
  getPaginationProperties(
    page: number,
    size: number
  ): Observable<PropertyResponse> {
    return this.http.get<PropertyResponse>(
      `${this.url}/pagination?page=${page}&size=${size}`
    );
  }

  getPaginationFilteredProperties(
    filterString: string,
    checkIn: string,
    checkOut: string,
    adults: string,
    children: string,
    pets: string,
    page: number,
    size: number
  ): Observable<PropertyResponse | undefined> {
    if (filterString && adults && children && pets && page && size)
      return this.http
        .get<PropertyResponse>(
          `${this.url}/pagination?filterString=${filterString}&checkIn=${checkIn}&checkOut=${checkOut}&adults=${adults}&children=${children}&pets=${pets}&page=${page}&size=${size}`
        )
        .pipe(catchError(this.handleError(undefined)));
    return this.getPaginationProperties(page, size);
  }

  getNearestProperties(
    lat: number | undefined,
    lng: number | undefined
  ): Observable<PropertyInfo[]> {
    return this.http.get<PropertyInfo[]>(
      `${this.url}/nearest?lat=${lat}&lng=${lng}`
    );
  }
}
