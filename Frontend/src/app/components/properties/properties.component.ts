import { Component, OnInit } from '@angular/core';
import { PropertyService } from '../../service/property.service';
import { Property } from '../../model/property';
import { JsonPipe } from '@angular/common';
import { PropertyPreviewComponent } from '../property-preview/property-preview.component';
import { PropertyInfo } from '../../model/PropertyInfo';
import { filter, flatMap, map, mergeMap, tap } from 'rxjs';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-properties',
  standalone: true,
  imports: [JsonPipe, PropertyPreviewComponent],
  templateUrl: './properties.component.html',
  styleUrl: './properties.component.css',
})
export class PropertiesComponent implements OnInit {
  properties: PropertyInfo[] = [];
  adults = 1;
  children = 0;
  pets = 0;
  filterString = '';
  startDate: Date | undefined | null;
  endDate: Date | undefined | null;

  constructor(
    private service: PropertyService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe((x) => console.log(x));

    this.route.queryParams
      .pipe(
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
          return this.service.getFilteredProperties(
            params['filterString'],
            params['checkIn'],
            params['checkOut'],
            params['adults'],
            params['children'],
            params['pets']
          );
        })
      )
      .subscribe({
        next: (properties) => {
          this.properties = properties;
        },
      });

    // this.service.getAllProperties().subscribe((properties) => {
    //   this.properties = properties;
    //   console.log(properties);
    // });
  }
}
