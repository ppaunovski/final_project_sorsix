import { Component, OnInit } from '@angular/core';
import { PropertyService } from '../../service/property.service';
import { ActivatedRoute, Router } from '@angular/router';
import { filter, map, mergeMap, tap } from 'rxjs';
import { PropertyInfo } from '../../model/PropertyInfo';
import { Property } from '../../model/property';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import { ReviewComponent } from '../../model/ReviewComponent';
import { ReviewService } from '../../service/review.service';
import { MatIconModule } from '@angular/material/icon';
import { MatSliderModule } from '@angular/material/slider';
import { ComponentRating } from '../../model/ComponentRating';
import { ComponentRatingRequest } from '../../model/ComponentRatingReqeust';
import { MatButtonModule } from '@angular/material/button';
import { ErrorPageComponent } from '../error-page/error-page.component';
import { ErrorResponse } from '../../model/ErrorResponse';
import {
  MatProgressSpinner,
  MatProgressSpinnerModule,
} from '@angular/material/progress-spinner';
import { HttpErrorResponse } from '@angular/common/http';
import { BookingService } from '../../service/booking.service';
import { BookingForReview } from '../../model/BookingForReview';

@Component({
  selector: 'app-review-component',
  standalone: true,
  imports: [
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatSliderModule,
    MatButtonModule,
    ErrorPageComponent,
    MatProgressSpinnerModule,
  ],
  templateUrl: './review-component.component.html',
  styleUrl: './review-component.component.css',
})
export class ReviewComponentComponent implements OnInit {
  property: PropertyInfo | undefined;
  booking: BookingForReview | undefined;
  reviewComponents: ReviewComponent[] = [];
  componentRatings: ComponentRatingRequest[] = [];
  icons = [
    'cleaning_services',
    'verified',
    'vpn_key',
    'contact_support',
    'location_on',
    'payments',
  ];
  comment: string | undefined;
  error: ErrorResponse | null = null;
  loading = false;

  constructor(
    private propertyService: PropertyService,
    private route: ActivatedRoute,
    private reviewService: ReviewService,
    private bookingService: BookingService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.paramMap
      .pipe(
        tap(() => {
          this.loading = true;
          this.error = null;
        }),
        filter((params) => params.has('id')),
        map((params) => params.get('id')!),
        mergeMap((id) => this.bookingService.getBookingForReview(+id))
      )
      .subscribe({
        next: (resp) => {
          this.loading = false;
          this.error = null;
          this.booking = resp;
          this.property = resp.property;
        },
        error: (error: HttpErrorResponse) => {
          this.error = error.error;
          this.loading = false;
        },
      });

    this.reviewService.getReviewComponents().subscribe({
      next: (resp) => {
        this.reviewComponents = resp;
        this.reviewComponents.forEach((comp) => {
          this.componentRatings.push({
            rating: 1,
            reviewComponentId: comp.id,
            reviewComponentName: comp.rcComponentName,
          });
        });
      },
    });
  }

  review() {
    if (this.property && this.comment && this.booking)
      this.reviewService
        .review({
          propertyId: this.property?.id.valueOf(),
          bookingId: this.booking?.id,
          comment: this.comment,
          componentRatings: this.componentRatings,
        })
        .subscribe({
          next: (resp) => {
            console.log(resp);
            this.router.navigate(['/properties', this.property?.id]);
          },
        });
  }

  changeComment(value: string) {
    this.comment = value;
  }

  changeRating(component: number, value: string) {
    const old = this.componentRatings.find(
      (val) => val.reviewComponentId == component
    );
    if (old) old.rating = +value;
    else
      this.componentRatings.push({
        rating: +value,
        reviewComponentId: component,
        reviewComponentName: this.reviewComponents.find(
          (val) => val.id.valueOf() == component
        )?.rcComponentName!,
      });
    console.log(this.componentRatings);
  }
}
