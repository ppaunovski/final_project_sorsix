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

  isDisabled() {
    return false;
  }

  changeCount(number: number) {
    if (this.guestType) {
      this.count = Math.max(this.count + number, 0);

      this.countEvent$.emit({
        guestType: this.guestType,
        count: this.count,
      });
    }
  }
}
