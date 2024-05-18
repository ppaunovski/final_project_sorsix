import { Component, Input, OnInit } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { Property } from '../../model/property';
import { RouterLink } from '@angular/router';
import { DecimalPipe, SlicePipe } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import { PropertyInfo } from '../../model/PropertyInfo';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-property-preview',
  standalone: true,
  imports: [MatCardModule, RouterLink, SlicePipe, MatIconModule, DecimalPipe],
  templateUrl: './property-preview.component.html',
  styleUrl: './property-preview.component.css',
})
export class PropertyPreviewComponent implements OnInit {
  headerImage: string = '';

  ngOnInit(): void {
    if (this.property && this.property.image) {
      this.headerImage = this.dataURItoBlob(
        this.property.image.imageByteArray,
        this.property.image.type
      );
    } else {
      this.headerImage = 'assets/placeholder.png';
    }
  }
  @Input()
  property: PropertyInfo | undefined;

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
