import {Component, OnInit} from '@angular/core';
import {MapComponent} from '../map/map.component';
import {PropertyService} from '../../service/property.service';

@Component({
  selector: 'app-landing-page',
  standalone: true,
  imports: [MapComponent],
  templateUrl: './landing-page.component.html',
})
export class LandingPageComponent implements OnInit {
  constructor(private propertyService: PropertyService) {
  }

  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }
}
