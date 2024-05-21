import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { Property } from '../../model/property';
import { RouterLink } from '@angular/router';
import { DecimalPipe, SlicePipe } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import { PropertyInfo } from '../../model/PropertyInfo';
import { DomSanitizer } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import { FavoriteService } from '../../service/favorite-service.service';
import { ImageToUrlService } from '../../service/image-to-url.service';
import { MatButtonModule } from '@angular/material/button';
import { MatTooltipModule } from '@angular/material/tooltip';
import { tap } from 'rxjs';

@Component({
  selector: 'app-property-preview',
  standalone: true,
  imports: [
    MatCardModule,
    RouterLink,
    SlicePipe,
    MatIconModule,
    DecimalPipe,
    CommonModule,
    MatButtonModule,
    MatTooltipModule,
  ],
  templateUrl: './property-preview.component.html',
  styleUrl: './property-preview.component.css',
})
export class PropertyPreviewComponent implements OnInit {
  headerImage: string = '';
  isFavorite: boolean = false;
  favoriteHoverText = '';

  constructor(
    private favoriteService: FavoriteService,
    private imageService: ImageToUrlService
  ) {}
  ngOnInit(): void {
    if (this.property && this.property.image) {
      this.headerImage = this.imageService.bytesToURL(
        this.property.image.imageByteArray,
        this.property.image.type
      );
    } else {
      this.headerImage = 'assets/placeholder.png';
    }
    if (this.property) {
      this.favoriteService.isFavorite(this.property.id).subscribe({
        next: (n) => {
          this.isFavorite = n;
          console.log('Is favorite: ' + n);
        },
        error: (err) => {
          console.log(err);
        },
      });
    }
  }
  @Input()
  property: PropertyInfo | undefined;

  @Output()
  unfavorited$ = new EventEmitter<number>();

  handleFavorite() {
    console.log('Favorite clicked');
    if (this.isFavorite) {
      if (this.property) {
        this.favoriteService.removeFavorite(this.property.id).pipe(
          tap(() => {
            if(this.property)
            this.unfavorited$.emit(this.property.id);
          })
        ).subscribe({
          next: (n) => {
            console.log(n);
            this.isFavorite = false;
            this.favoriteHoverText = 'Favorite';
          },
          error: (err) => {
            this.favoriteHoverText = 'Please sign in to add to favorites';
          },
        });
        console.log('Removed from favorites');
      }
    } else {
      if (this.property) {
        this.favoriteService.addFavorite(this.property.id).subscribe({
          next: (n) => {
            console.log(n);
            this.isFavorite = true;
            this.favoriteHoverText = 'Remove from favorites';
          },
          error: (err) => {
            this.favoriteHoverText = 'Please sign in to add to favorites';
          },
        });
        console.log('Added to favorites');
      }
    }
  }
}


