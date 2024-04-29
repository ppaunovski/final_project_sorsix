import { Component, Input, OnInit } from '@angular/core';
import { Property } from '../../model/property';
import { PropertyService } from '../../service/property.service';
import { ActivatedRoute } from '@angular/router';
import { filter, forkJoin, map, mergeMap, switchMap, tap } from 'rxjs';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { PropertyAttributeService } from '../../service/property-attribute.service';
import { PropertyAttribute } from '../../model/PropertyAttribite';
import { PropertyAttributeComponent } from '../property-attribute/property-attribute.component';
import { ReserveComponentComponent } from '../reserve-component/reserve-component.component';
import { HostPropertyPreviewComponent } from '../host-property-preview/host-property-preview.component';
import { RatingReviewPreviewComponent } from '../rating-review-preview/rating-review-preview.component';
import { PropertyInfoComponent } from '../property-info/property-info.component';
import { ReviewService } from '../../service/review.service';
import { ReviewWithComponents } from '../../model/ReviewWIthComponents';
import { ComponentRating } from '../../model/ComponentRating';
import { ReviewAveragesComponent } from '../review-averages/review-averages.component';
import { ReviewComponent } from '../review/review.component';

@Component({
  selector: 'app-property',
  standalone: true,
  imports: [
    MatProgressSpinnerModule,
    MatIconModule,
    MatButtonModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    PropertyAttributeComponent,
    ReserveComponentComponent,
    HostPropertyPreviewComponent,
    RatingReviewPreviewComponent,
    PropertyInfoComponent,
    ReviewAveragesComponent,
    ReviewComponent,
  ],
  templateUrl: './property.component.html',
  styleUrl: './property.component.css',
})
export class PropertyComponent implements OnInit {
  property: Property | undefined;
  loading = false;
  error: any;
  propertyAttributes: PropertyAttribute[] = [];

  reviewsWithComponents: ReviewWithComponents[] = [];
  componentRating: ComponentRating[][] = [];
  averageRating: string = '';

  loadingAttribs: boolean = false;
  errorAttribs: any;

  constructor(
    private propertyService: PropertyService,
    private route: ActivatedRoute,
    private propertyAttributeService: PropertyAttributeService,
    private reviewService: ReviewService
  ) {}

  ngOnInit(): void {
    this.route.paramMap
      .pipe(
        filter((params) => params.has('id')),
        map((params) => params.get('id')!),
        tap(() => {
          this.loading = true;
          this.error = null;
        }),
        mergeMap((id) => {
          return this.propertyService.getPropertyById(+id);
        })
      )
      .subscribe({
        next: (response) => {
          this.property = response;
          this.loading = false;
          this.error = null;
          console.log(this.property);
        },
        error: (error) => {
          this.error = error;
          this.loading = false;
        },
      });

    this.route.paramMap
      .pipe(
        filter((params) => params.has('id')),
        map((params) => params.get('id')!),
        tap(() => {
          this.loadingAttribs = true;
          this.errorAttribs = null;
        }),
        mergeMap((id) => {
          return this.propertyAttributeService.getAttributesForProperty(+id);
        })
      )
      .subscribe({
        next: (response) => {
          this.propertyAttributes = response;
          this.loadingAttribs = false;
          this.errorAttribs = null;
          console.log(this.propertyAttributes);
        },
        error: (error) => {
          this.errorAttribs = error;
          this.loadingAttribs = false;
        },
      });

    //TODO: na backend da se napravat funkcii za average rating i average za sekoja kategorija

    this.route.paramMap
      .pipe(
        filter((params) => params.has('id')),
        map((params) => params.get('id')!),
        tap(() => {
          this.loading = true;
          this.error = null;
        }),
        mergeMap((id) => {
          return this.propertyService.getPropertyById(+id);
        })
      )
      .pipe(
        filter((property) => property != undefined),
        mergeMap((property) =>
          this.propertyService.getPropertyReviewsByPropertyId(property?.id!)
        )
      )
      .pipe(
        switchMap((reviews) =>
          forkJoin(
            reviews.map((review) =>
              this.reviewService.getComponentRatingsForReview(review.id)
            )
          )
        )
      )
      .subscribe({
        next: (response) => {
          response.forEach((review) => {
            this.reviewsWithComponents.push({
              review: review[0].userReview,
              components: review,
            });
          });
          console.log(this.reviewsWithComponents);
          this.averageRating = this.calculateAverageRating();
        },
      });
  }

  calculateAverageRating() {
    return (
      this.reviewsWithComponents
        .flatMap((rwc) => {
          return (
            rwc.components
              .map((c) => c.rating)
              .reduce((sum, rating) => sum.valueOf() + rating.valueOf(), 0)
              .valueOf() / rwc.components.length
          );
        })
        .reduce((sum, reviewRating) => sum + reviewRating, 0) /
      this.reviewsWithComponents.length
    ).toPrecision(2);
  }
}
