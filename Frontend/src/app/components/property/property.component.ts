import { Component, Input, OnInit } from '@angular/core';
import { Property } from '../../model/property';
import { PropertyService } from '../../service/property.service';
import { ActivatedRoute } from '@angular/router';
import { filter, map, mergeMap, tap } from 'rxjs';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

@Component({
  selector: 'app-property',
  standalone: true,
  imports: [MatProgressSpinnerModule],
  templateUrl: './property.component.html',
  styleUrl: './property.component.css',
})
export class PropertyComponent implements OnInit {
  property: Property | undefined;
  loading = false;
  error: any;

  constructor(
    private service: PropertyService,
    private route: ActivatedRoute
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
          return this.service.getPropertyById(+id);
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
  }
}
