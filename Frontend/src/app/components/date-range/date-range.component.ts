import { Component, EventEmitter, Input, Output } from '@angular/core';
import {
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import {
  DateFilterFn,
  MatDatepickerModule,
} from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-date-range',
  standalone: true,
  imports: [
    MatIconModule,
    MatButtonModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule,
    ReactiveFormsModule,
  ],
  templateUrl: './date-range.component.html',
  styleUrl: './date-range.component.css',
})
export class DateRangeComponent {
  @Input()
  startDate: Date | undefined | null;
  @Input()
  endDate: Date | undefined | null;
  range = new FormGroup({
    start: new FormControl<Date | null>(null),
    end: new FormControl<Date | null>(null),
  });
  @Input()
  max: Date | undefined;
  @Input()
  min: Date | undefined;
  @Input()
  filter: DateFilterFn<any> | undefined;

  @Output()
  changeEndDate$ = new EventEmitter<Date | null | undefined>();
  @Output()
  changeStartDate$ = new EventEmitter<Date | null | undefined>();

  changeEndDate(date: Date | null | undefined) {
    this.changeEndDate$.emit(date);
  }

  changeStartDate(date: Date | null | undefined) {
    this.changeStartDate$.emit(date);
  }

  closed($event: void) {
    if (this.endDate == undefined) this.changeEndDate$.next(undefined);
  }
}
