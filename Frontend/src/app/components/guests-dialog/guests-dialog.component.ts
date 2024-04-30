import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import {
  MatDialog,
  MatDialogRef,
  MatDialogActions,
  MatDialogClose,
  MatDialogTitle,
  MatDialogContent,
} from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { ReserveComponentComponent } from '../reserve-component/reserve-component.component';
import { MatIconModule } from '@angular/material/icon';
import { GuestType } from '../../model/GuestType';
import { GuestTypeService } from '../../service/guest-type.service';
import { GuestTypeSelectComponent } from '../guest-type-select/guest-type-select.component';
import { CountGuests } from '../../model/CountGuests';

@Component({
  selector: 'app-guests-dialog',
  standalone: true,
  imports: [
    MatButtonModule,
    MatDialogActions,
    MatDialogClose,
    MatDialogTitle,
    MatDialogContent,
    MatIconModule,
    GuestTypeSelectComponent,
  ],
  templateUrl: './guests-dialog.component.html',
  styleUrl: './guests-dialog.component.css',
})
export class GuestsDialogComponent implements OnInit {
  guestTypes: GuestType[] = [];
  numberOfAdults = 0;
  numberOfChildren = 0;
  numberOfPets = 0;
  @Output()
  adults$ = new EventEmitter<number>();
  @Output()
  children$ = new EventEmitter<number>();
  @Output()
  pets$ = new EventEmitter<number>();

  constructor(private guestTypeService: GuestTypeService) {}

  ngOnInit(): void {
    this.guestTypeService.getAllGuestTypes().subscribe({
      next: (response) => {
        this.guestTypes = response;
      },
    });
  }

  changeCount(countGuests: CountGuests) {
    switch (countGuests.guestType.name) {
      case 'ADULT':
        this.adults$.emit(countGuests.count);
        break;
      case 'CHILD':
        this.children$.emit(countGuests.count);
        break;
      case 'PETS':
        this.pets$.emit(countGuests.count);
        break;
    }
  }
}
