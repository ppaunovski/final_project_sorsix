import { Component, Input } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { Property } from '../../model/property';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-property-preview',
  standalone: true,
  imports: [MatCardModule, RouterLink],
  templateUrl: './property-preview.component.html',
  styleUrl: './property-preview.component.css',
})
export class PropertyPreviewComponent {
  @Input()
  property: Property | undefined;
}
