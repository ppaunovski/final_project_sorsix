import {Component, EventEmitter, Input, Output} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {MatCardModule} from '@angular/material/card';
import {DateFilterFn, MatDatepickerInputEvent, MatDatepickerModule,} from '@angular/material/datepicker';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';

@Component({
  selector: 'app-calendar',
  standalone: true,
  imports: [
    MatFormFieldModule,
    MatDatepickerModule,
    FormsModule,
    MatInputModule,
    MatCardModule,
  ],
  templateUrl: './calendar.component.html',
  styleUrl: './calendar.component.css',
})
export class CalendarComponent {
  @Input()
  filter: DateFilterFn<any> = (d: Date | null) => {
    let date = d || new Date();

    let yesterday = new Date();
    yesterday.setDate(yesterday.getDate() - 1);

    return date >= yesterday;
  };
  @Output()
  changeDate$ = new EventEmitter<Date | undefined>();
  @Input()
  date: Date | undefined | null;
  @Input()
  isOpen = false;

  closed($event: void) {
    if (this.date == undefined) this.changeDate$.next(undefined);
  }

  handle($event: MatDatepickerInputEvent<any, any>) {
    console.log($event);

    this.changeDate($event.value);
  }

  changeDate(date: Date) {
    console.log('DATE CHANGE TEST', date);

    this.date = date;
    this.changeDate$.emit(date);
  }
}
