import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {ComponentRating} from '../model/ComponentRating';
import {ReviewComponent} from '../model/ReviewComponent';
import {ReviewRequest} from '../model/ReviewRequest';
import {Review} from '../model/Review';

@Injectable({
  providedIn: 'root',
})
export class ReviewService {
  private url = '/api/reviews';

  constructor(private http: HttpClient) {
  }

  getComponentRatingsForReview(id: Number): Observable<ComponentRating[]> {
    return this.http.get<ComponentRating[]>(`${this.url}/${id}/components`);
  }

  getReviewComponents(): Observable<ReviewComponent[]> {
    return this.http.get<ReviewComponent[]>(`${this.url}/components`);
  }

  review(review: ReviewRequest): Observable<Review> {
    return this.http.post<Review>(`${this.url}`, JSON.stringify(review), {
      headers: {
        'Content-Type': 'application/json',
      },
    });
  }
}
