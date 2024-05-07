import { Component, Input, OnInit } from '@angular/core';
import { ReviewWithComponents } from '../../model/ReviewWIthComponents';
import { PropertyService } from '../../service/property.service';
import { AverageRating } from '../../model/AverageRating';

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
  averageRatings: AverageRating[] = [];
}
