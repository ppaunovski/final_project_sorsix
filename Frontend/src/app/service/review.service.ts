import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ComponentRating } from '../model/ComponentRating';

@Injectable({
  providedIn: 'root',
})
export class ReviewService {
  private url = '/api/reviews';

  constructor(private http: HttpClient) {}

  getComponentRatingsForReview(id: Number): Observable<ComponentRating[]> {
    return this.http.get<ComponentRating[]>(`${this.url}/${id}/components`);
  }
}
