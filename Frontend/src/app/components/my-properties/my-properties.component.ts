import {Component, OnInit} from '@angular/core';
import {PropertyInfo} from '../../model/PropertyInfo';
import {ActivatedRoute} from '@angular/router';
import {PropertyService} from '../../service/property.service';
import {UserAccountService} from '../../service/user-account.service';
import {filter, mergeMap, tap} from 'rxjs';
import {MatProgressSpinner} from '@angular/material/progress-spinner';
import {PropertyPreviewComponent} from '../property-preview/property-preview.component';
import {MatButtonModule} from '@angular/material/button';

@Component({
  selector: 'app-my-properties',
  standalone: true,
  imports: [MatProgressSpinner, PropertyPreviewComponent, MatButtonModule],
  templateUrl: './my-properties.component.html',
  styleUrl: './my-properties.component.css',
})
export class MyPropertiesComponent implements OnInit {
  properties: PropertyInfo[] = [];
  loading = false;
  error: any;

  constructor(
    private route: ActivatedRoute,
    private propertyService: PropertyService,
    private userService: UserAccountService
  ) {
  }

  ngOnInit(): void {
    this.userService
      .getUserInfo()
      .pipe(
        tap(() => {
          this.error = null;
          this.loading = true;
        }),
        filter((user) => user != undefined),
        mergeMap((user) => this.userService.getAllPropertiesByHost(user!.id))
      )
      .subscribe({
        next: (resp) => {
          this.error = null;
          this.loading = false;
          this.properties = resp;
        },
        error: (error) => {
          this.error = error;
          this.loading = false;
          console.log(error);
        },
      });
  }
}
