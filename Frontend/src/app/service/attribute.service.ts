import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Attribute} from '../model/Attribute';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AttributeService {

  url: string = '/api/attributes';

  constructor(private http: HttpClient) {
  }

  getAllAttributes(): Observable<Attribute[]> {
    return this.http.get<Attribute[]>(this.url);
  }
}
