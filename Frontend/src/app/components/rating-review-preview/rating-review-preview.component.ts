import {Component, Input, OnInit} from '@angular/core';
import {Property} from '../../model/property';
import {PropertyService} from '../../service/property.service';
import {ReviewService} from '../../service/review.service';
import {Review} from '../../model/Review';

@Component({
  selector: 'app-rating-review-preview',
  standalone: true,
  imports: [],
  templateUrl: './rating-review-preview.component.html',
})
export class RatingReviewPreviewComponent implements OnInit {
  @Input()
  property: Property | undefined;
  reviews: Review[] = [];

  @Input()
  averageRating: string | undefined;
  @Input()
  numberReviews: number | undefined;

  constructor(
    private propertyService: PropertyService,
    private reviewService: ReviewService
  ) {
  }

  ngOnInit(): void {
  }
}
