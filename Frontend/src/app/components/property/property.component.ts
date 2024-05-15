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
import { AverageRating } from '../../model/AverageRating';
import { Review } from '../../model/Review';
import { ImageGalleryComponent } from '../image-gallery/image-gallery.component';

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
    ImageGalleryComponent,
  ],
  templateUrl: './property.component.html',
  styleUrl: './property.component.css',
})
export class PropertyComponent implements OnInit {
  property: Property | undefined;
  imagesUrl: string[] = [];
  loading = false;
  error: any;
  propertyAttributes: PropertyAttribute[] = [];

  reviews: Review[] = [];

  averageRatings: AverageRating[] = [];
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

          this.error = null;
          if (this.property && this.property.images) {
            this.imagesUrl = this.property.images.map((image) =>
              this.dataURItoBlob(image.imageByteArray, image.type)
            );
          }
          console.log(this.imagesUrl);
          console.log(this.property);
          this.loading = false;
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
          console.log('attributes', this.propertyAttributes);
        },
        error: (error) => {
          this.errorAttribs = error;
          this.loadingAttribs = false;
        },
      });

    this.route.paramMap
      .pipe(
        filter((params) => params.has('id')),
        map((params) => params.get('id')!),
        tap(() => {
          this.loading = true;
          this.error = null;
        }),
        mergeMap((id) => {
          return this.propertyService.getAverageRatings(+id);
        })
      )
      .subscribe({
        next: (res) => {
          this.averageRatings = res;
        },
      });

    this.route.paramMap
      .pipe(
        filter((params) => params.has('id')),
        map((params) => params.get('id')!),
        tap(() => {
          this.loading = true;
          this.error = null;
        }),
        mergeMap((id) => {
          return this.propertyService.getPropertyReviewsByPropertyId(+id);
        })
      )
      .subscribe({
        next: (resp) => {
          this.reviews = resp;
          this.averageRating = (
            resp.reduce(
              (sum, review) => sum.valueOf() + review.averageRating.valueOf(),
              0
            ) / resp.length
          ).toPrecision(2);
        },
      });
  }
  dataURItoBlob(dataURI: string, type: string): string {
    const byteString = window.atob(dataURI);
    const ab = new ArrayBuffer(byteString.length);
    const ia = new Uint8Array(ab);
    for (let i = 0; i < byteString.length; i++) {
      ia[i] = byteString.charCodeAt(i);
    }
    var blob = new Blob([ab], { type: type });
    return URL.createObjectURL(blob);
  }
}
