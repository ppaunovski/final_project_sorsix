import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {PropertyImage} from '../model/PropertyImage';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  private url = '/api/property-image';

  constructor(private http: HttpClient) {
  }

  getAllImages(): Observable<PropertyImage[]> {
    return this.http.get<PropertyImage[]>(this.url + `/all}`);
  }

  getAllImagesByPropertyId(id: Number): Observable<PropertyImage[]> {
    return this.http.get<PropertyImage[]>(this.url + `/property/${id}`);
  }

  postImage(image: PropertyImage): Observable<any> {
    return this.http.post(this.url + '/post', image);

  }

}
