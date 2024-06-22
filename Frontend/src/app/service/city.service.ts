import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {City} from '../model/City';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CityService {


  url = '/api/city';

  constructor(private http: HttpClient) {
  }

  getAllCities(): Observable<City[]> {
    return this.http.get<City[]>(this.url);
  }

  getCityById(id: number): Observable<City> {
    return this.http.get<City>(this.url + '/' + id);
  }
}
