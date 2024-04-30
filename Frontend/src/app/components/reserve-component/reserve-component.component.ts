import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { Property } from '../../model/property';
import { GuestsDialogComponent } from '../guests-dialog/guests-dialog.component';
import {
  FormGroup,
  FormControl,
  FormsModule,
  ReactiveFormsModule,
} from '@angular/forms';
import { JsonPipe } from '@angular/common';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { provideNativeDateAdapter } from '@angular/material/core';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-reserve-component',
  standalone: true,
  imports: [
    MatIconModule,
    MatButtonModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    GuestsDialogComponent,
    MatDatepickerModule,
    ReactiveFormsModule,
    JsonPipe,
  ],
  providers: [provideNativeDateAdapter()],
  templateUrl: './reserve-component.component.html',
  styleUrl: './reserve-component.component.css',
})
export class ReserveComponentComponent implements OnInit {
  @Input()
  property: Property | undefined;
  isOpenGuestsDialog = false;
  numberOfAdults = 1;
  numberOfChildren = 0;
  numberOfPets = 0;
  changeStartDate$: Subject<Date | undefined | null> = new Subject();
  changeEndDate$: Subject<Date> = new Subject();
  startDate: Date | undefined | null;
  endDate: Date | undefined;

  range = new FormGroup({
    start: new FormControl<Date | null>(null),
    end: new FormControl<Date | null>(null),
  });

  ngOnInit(): void {
    this.changeStartDate$.subscribe((start) => {
      this.startDate = start;
    });

    this.changeEndDate$.subscribe((end) => {
      this.endDate = end;
    });
  }

  toggleGuestsDialog(state: boolean) {
    console.log(state);
    this.isOpenGuestsDialog = state;
  }

  countAdults(count: number) {
    this.numberOfAdults = count;
  }
  countChildren(count: number) {
    this.numberOfChildren = count;
  }

  countPets(count: number) {
    this.numberOfPets = count;
  }

  getGuestsText() {
    return `${this.getAdultText()}${this.getChildrenText()}${this.getPetsText()}`;
  }

  getAdultText() {
    if (this.numberOfAdults == 1) {
      return '1 Adult';
    }
    return `${this.numberOfAdults} Adults`;
  }

  getChildrenText() {
    if (this.numberOfChildren == 0) {
      return '';
    } else if (this.numberOfChildren == 1)
      return `, ${this.numberOfChildren} Child`;
    return `, ${this.numberOfChildren} Children`;
  }

  getPetsText() {
    if (this.numberOfPets == 0) {
      return '';
    } else if (this.numberOfPets == 1) return `, ${this.numberOfPets} Pet`;
    return `, ${this.numberOfPets} Pets`;
  }

  changeStartDate(date: Date | undefined | null) {
    console.log(date);
    this.changeStartDate$.next(date);
  }

  changeEndDate(date: Date) {
    this.changeEndDate$.next(date);
  }
}
