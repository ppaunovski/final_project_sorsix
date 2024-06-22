import {Component, OnInit} from '@angular/core';
import {FavoriteService} from '../../service/favorite-service.service';
import {PropertyInfo} from '../../model/PropertyInfo';
import {PropertyPreviewComponent} from '../property-preview/property-preview.component';
import {MatButtonModule} from '@angular/material/button';
import {MatProgressSpinner} from '@angular/material/progress-spinner';
import {CommonModule} from '@angular/common';
import {tap} from 'rxjs';

@Component({
  selector: 'app-favorites',
  standalone: true,
  templateUrl: './favorites.component.html',
  imports: [
    PropertyPreviewComponent,
    MatProgressSpinner,
    MatButtonModule,
    CommonModule,
  ],
})
export class FavoritesComponent implements OnInit {
  properties: PropertyInfo[] = [];
  loading: any;
  error: any;

  constructor(private favoriteService: FavoriteService) {
  }

  ngOnInit(): void {
    this.favoriteService
      .getFavorites()
      .pipe(
        tap(() => {
          this.error = null;
          this.loading = true;
        })
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

  removeFromFavorites(propertyId: number): void {
    this.properties = this.properties.filter(
      (property) => property.id !== propertyId
    );
  }
}
