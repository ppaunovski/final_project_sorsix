import { Component, Input, OnInit } from '@angular/core';
import { Property } from '../../model/property';
import { PropertyService } from '../../service/property.service';
import { ActivatedRoute } from '@angular/router';
import { filter, map, mergeMap, tap } from 'rxjs';
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
  ],
  templateUrl: './property.component.html',
  styleUrl: './property.component.css',
})
export class PropertyComponent implements OnInit {
  property: Property | undefined;
  loading = false;
  error: any;
  propertyAttributes: PropertyAttribute[] = [];

  loadingAttribs: boolean = false;
  errorAttribs: any;

  constructor(
    private propertyService: PropertyService,
    private route: ActivatedRoute,
    private propertyAttributeService: PropertyAttributeService
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
  }
}
