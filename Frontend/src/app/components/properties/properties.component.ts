import {Component, OnInit} from '@angular/core';
import {PropertyService} from '../../service/property.service';
import {JsonPipe} from '@angular/common';
import {PropertyPreviewComponent} from '../property-preview/property-preview.component';
import {PropertyInfo} from '../../model/PropertyInfo';
import {mergeMap, tap} from 'rxjs';
import {ActivatedRoute, Router} from '@angular/router';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatPaginatorModule, PageEvent} from '@angular/material/paginator';
import {PropertyResponse} from '../../model/PropertyResponse';
import {MatProgressSpinner} from '@angular/material/progress-spinner';
import {MatSelectModule} from '@angular/material/select';
import {MapComponent} from '../map/map.component';

@Component({
  selector: 'app-properties',
  standalone: true,
  imports: [
    JsonPipe,
    PropertyPreviewComponent,
    MatFormFieldModule,
    MatInputModule,
    MatPaginatorModule,
    MatProgressSpinner,
    MatSelectModule,
    MapComponent,
  ],
  templateUrl: './properties.component.html',
})
export class PropertiesComponent implements OnInit {
  propertyResponse: PropertyResponse | undefined;
  properties: PropertyInfo[] = [];
  adults = 1;
  children = 0;
  pets = 0;
  filterString = '';
  startDate: Date | undefined | null;
  endDate: Date | undefined | null;
  page = 0;
  size = 2;
  showMap = false;

  loading = false;
  error: any;

  constructor(
    private service: PropertyService,
    private route: ActivatedRoute,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.page = 0;
    this.size = 10;

    this.service.showMap$.subscribe((x) => {
      this.showMap = x;
    });

    this.route.queryParams
      .pipe(
        tap(() => {
          this.loading = true;
          this.error = null;
        }),
        // filter(
        //   (params) =>
        //     params['filterString'] &&
        //     params['checkIn'] &&
        //     params['checkOut'] &&
        //     params['adults'] &&
        //     params['children'] &&
        //     params['pets']
        // ),
        mergeMap((params) => {
          this.page = params['page'] ?? 0;
          this.size = params['size'] ?? 10;

          return this.service.getPaginationFilteredProperties(
            params['filterString'] ?? '',
            params['checkIn'] ?? '',
            params['checkOut'] ?? '',
            params['adults'] ?? '',
            params['children'] ?? '',
            params['pets'] ?? '',
            params['page'] ?? this.page,
            params['size'] ?? this.size
          );
        })
      )
      .subscribe({
        next: (resp) => {
          if (resp) {
            this.properties = resp?.content;
          }
          this.showMap = false;
          this.propertyResponse = resp;
          this.loading = false;
          this.error = null;
        },
        error: (error) => {
          this.showMap = false;

          this.loading = false;
          this.error = error;
        },
      });

    // this.service.getPaginationProperties(0, 2).subscribe({
    //   next: (propertyResponse) => {
    //     this.properties = propertyResponse.content;
    //     this.propertyResponse = propertyResponse;
    //     console.log(this.propertyResponse);
    //   },
    //   error: (error) => {
    //     console.log(error);
    //   },
    // });
  }

  handlePageChange(event: PageEvent) {
    const page = event.pageIndex;
    const size = event.pageSize;
    if (page != null) this.page = page;
    if (size != null) this.size = size;

    this.route.queryParams.subscribe((params) => {
      var queryParams = {
        page: this.page,
        size: this.size,
        filterString: params['filterString'] ?? '',
        checkIn: params['checkIn'] ?? '',
        checkOut: params['checkOut'] ?? '',
        adults: params['adults'] ?? '',
        children: params['children'] ?? '',
        pets: params['pets'] ?? '',
      };
      this.router.navigate(['/properties'], {
        queryParams: queryParams,
      });
    });
  }
}
