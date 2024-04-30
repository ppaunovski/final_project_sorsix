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
  review: ReviewWithComponents | undefined;

  getAverage() {
    if (this.review)
      return (
        this.review?.components
          .map((c) => c.rating)
          .reduce((sum, rating) => sum.valueOf() + rating.valueOf(), 0)
          .valueOf() / this.review?.components.length
      );
    return 0;
  }
}
