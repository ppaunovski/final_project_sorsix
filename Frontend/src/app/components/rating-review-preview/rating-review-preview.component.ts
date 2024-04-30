import { Component, Input, OnInit } from '@angular/core';
import { Property } from '../../model/property';
import { PropertyService } from '../../service/property.service';
import { ReviewService } from '../../service/review.service';
import { Review } from '../../model/Review';
import { forkJoin, map, mergeMap, switchMap } from 'rxjs';
import { ReviewWithComponents } from '../../model/ReviewWIthComponents';
import { ComponentRating } from '../../model/ComponentRating';

@Component({
  selector: 'app-rating-review-preview',
  standalone: true,
  imports: [],
  templateUrl: './rating-review-preview.component.html',
  styleUrl: './rating-review-preview.component.css',
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
  ) {}

  ngOnInit(): void {}
}
