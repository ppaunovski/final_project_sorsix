import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {PropertyType} from '../model/PropertyType';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PropertyTypeService {

  url = '/api/property-type';

  constructor(private http: HttpClient) {
  }

  getAllPropertyTypes(): Observable<PropertyType[]> {
    return this.http.get<PropertyType[]>(this.url + '/all');
  }

  getPropertyTypeById(id: Number): Observable<PropertyType> {
    return this.http.get<PropertyType>(this.url + '/' + id);
  }
}
