import {Component, Input, OnInit} from '@angular/core';
import {Property} from '../../model/property';

@Component({
  selector: 'app-reservation-info',
  standalone: true,
  imports: [],
  templateUrl: './reservation-info.component.html',
})
export class ReservationInfoComponent implements OnInit {
  @Input()
  property: Property | undefined;
  @Input()
  startDate: Date | undefined | null;
  @Input()
  endDate: Date | undefined | null;

  ngOnInit(): void {
    if (this.property && this.endDate && this.startDate) {
    }
  }
}
