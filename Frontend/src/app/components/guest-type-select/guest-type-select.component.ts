import { Component, EventEmitter, Input, Output } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { GuestType } from '../../model/GuestType';
import { CountGuests } from '../../model/CountGuests';

@Component({
  selector: 'app-guest-type-select',
  standalone: true,
  imports: [MatIconModule],
  templateUrl: './guest-type-select.component.html',
  styleUrl: './guest-type-select.component.css',
})
export class GuestTypeSelectComponent {
  @Input()
  guestType: GuestType | undefined;
  @Output()
  countEvent$ = new EventEmitter<CountGuests>();
  count = 0;
  @Input()
  maxGuests: number | undefined;

  isDisabled() {
    return false;
  }

  getCount() {
    if (this.guestType?.name == 'ADULT') return Math.max(1, this.count);
    else return this.count;
  }

  changeCount(number: number) {
    console.log(this.guestType?.name);

    if (this.guestType && this.maxGuests) {
      if (this.guestType.name == 'ADULT')
        this.count = Math.min(
          Math.max(this.getCount() + number, 1),
          this.maxGuests
        );
      else
        this.count = Math.min(
          Math.max(this.getCount() + number, 0),
          this.maxGuests
        );

      this.countEvent$.emit({
        guestType: this.guestType,
        count: this.getCount(),
      });
    }
  }
}