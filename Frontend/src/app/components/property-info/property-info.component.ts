import {Component, Input} from '@angular/core';
import {Property} from '../../model/property';

@Component({
  selector: 'app-property-info',
  standalone: true,
  imports: [],
  templateUrl: './property-info.component.html',
  styleUrl: './property-info.component.css',
})
export class PropertyInfoComponent {
  @Input()
  property: Property | undefined;
}
