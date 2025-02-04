import {Component, Input} from '@angular/core';
import {AverageRating} from '../../model/AverageRating';
import {MatIconModule} from '@angular/material/icon';

@Component({
  selector: 'app-review-averages',
  standalone: true,
  imports: [MatIconModule],
  templateUrl: './review-averages.component.html',
})
export class ReviewAveragesComponent {
  @Input()
  averageRating: string | undefined;
  @Input()
  averageRatings: AverageRating[] = [];
  icons = [
    'cleaning_services',
    'verified',
    'vpn_key',
    'contact_support',
    'location_on',
    'payments',
  ];
}
