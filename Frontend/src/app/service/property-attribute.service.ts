import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {PropertyAttribute} from '../model/PropertyAttribite';

@Injectable({
  providedIn: 'root',
})
export class PropertyAttributeService {
  private url = '/api/property-attributes';

  constructor(private http: HttpClient) {
  }

  getAttributesForProperty(id: Number): Observable<PropertyAttribute[]> {
    return this.http.get<PropertyAttribute[]>(this.url + `/${id}`);
  }
}
