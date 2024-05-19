import { Component, Input, OnInit } from '@angular/core';
import { Property } from '../../model/property';
import { PropertyService } from '../../service/property.service';

@Component({
  selector: 'app-reservation-info',
  standalone: true,
  imports: [],
  templateUrl: './reservation-info.component.html',
  styleUrl: './reservation-info.component.css',
})
export class ReservationInfoComponent implements OnInit {
  @Input()
  property: Property | undefined;
  @Input()
  startDate: Date | undefined | null;
  @Input()
  endDate: Date | undefined | null;
  total = 0;

  ngOnInit(): void {
    if (this.property && this.endDate && this.startDate) {
    }
  }
}
