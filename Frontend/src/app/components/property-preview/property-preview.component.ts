import { Component, Input, OnInit } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { Property } from '../../model/property';
import { RouterLink } from '@angular/router';
import { DecimalPipe, SlicePipe } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import { PropertyInfo } from '../../model/PropertyInfo';

@Component({
  selector: 'app-property-preview',
  standalone: true,
  imports: [MatCardModule, RouterLink, SlicePipe, MatIconModule, DecimalPipe],
  templateUrl: './property-preview.component.html',
  styleUrl: './property-preview.component.css',
})
export class PropertyPreviewComponent implements OnInit {
  ngOnInit(): void {
    console.log(this.property);
  }
  @Input()
  property: PropertyInfo | undefined;
}
