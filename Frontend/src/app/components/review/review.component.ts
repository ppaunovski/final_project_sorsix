import { Component, Input } from '@angular/core';
import { Review } from '../../model/Review';
import { ReviewWithComponents } from '../../model/ReviewWIthComponents';

@Component({
  selector: 'app-review',
  standalone: true,
  imports: [],
  templateUrl: './review.component.html',
  styleUrl: './review.component.css',
})
export class ReviewComponent {
  @Input()
  review: Review | undefined;

  getAverage() {
    console.log('review', this.review);

    if (this.review) return this.review.averageRating;
    return 0;
  }
}
