import { Component, Input, OnInit } from '@angular/core';
import { ReviewWithComponents } from '../../model/ReviewWIthComponents';
import { PropertyService } from '../../service/property.service';
import { AverageRating } from '../../model/AverageRating';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-review-averages',
  standalone: true,
  imports: [MatIconModule],
  templateUrl: './review-averages.component.html',
  styleUrl: './review-averages.component.css',
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
