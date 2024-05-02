import { Component, OnInit } from '@angular/core';
import { PropertyService } from '../../service/property.service';
import { Property } from '../../model/property';
import { JsonPipe } from '@angular/common';
import { PropertyPreviewComponent } from '../property-preview/property-preview.component';
import { PropertyInfo } from '../../model/PropertyInfo';

@Component({
  selector: 'app-properties',
  standalone: true,
  imports: [JsonPipe, PropertyPreviewComponent],
  templateUrl: './properties.component.html',
  styleUrl: './properties.component.css',
})
export class PropertiesComponent implements OnInit {
  properties: PropertyInfo[] = [];

  constructor(private service: PropertyService) {}

  ngOnInit(): void {
    this.service.getAllProperties().subscribe((properties) => {
      this.properties = properties;
    });
  }
}
