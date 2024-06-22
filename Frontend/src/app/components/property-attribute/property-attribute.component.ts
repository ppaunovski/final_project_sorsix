import {Component, Input} from '@angular/core';
import {PropertyAttribute} from '../../model/PropertyAttribite';
import {MatIconModule} from '@angular/material/icon';

@Component({
  selector: 'app-property-attribute',
  standalone: true,
  imports: [MatIconModule],
  templateUrl: './property-attribute.component.html',
  styleUrl: './property-attribute.component.css',
})
export class PropertyAttributeComponent {
  @Input()
  propertyAttribute: PropertyAttribute | undefined;
}
