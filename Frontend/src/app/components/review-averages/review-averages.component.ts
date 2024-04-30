import { Component, Input } from '@angular/core';
import { ReviewWithComponents } from '../../model/ReviewWIthComponents';

@Component({
  selector: 'app-review-averages',
  standalone: true,
  imports: [],
  templateUrl: './review-averages.component.html',
  styleUrl: './review-averages.component.css',
})
export class ReviewAveragesComponent {
  @Input()
  averageRating: string | undefined;
  @Input()
  reviews: ReviewWithComponents[] = [];
}
