import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Unit } from '../model/unit';

@Injectable({
  providedIn: 'root',
})
export class UnitService {
  private url = '/api/units';
  constructor(private http: HttpClient) {}

  getAllUnitsForProperty(propertyId: Number): Observable<Unit[]> {
    return this.http.get<Unit[]>(this.url + `/${propertyId}`);
  }
}
