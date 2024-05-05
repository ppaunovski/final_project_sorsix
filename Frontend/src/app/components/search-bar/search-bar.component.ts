import { Component, OnInit } from '@angular/core';
import { CalendarComponent } from '../calendar/calendar.component';
import { DateRangeComponent } from '../date-range/date-range.component';
import { Subject } from 'rxjs';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-search-bar',
  standalone: true,
  imports: [
    CalendarComponent,
    DateRangeComponent,
    MatButtonModule,
    MatIconModule,
  ],
  templateUrl: './search-bar.component.html',
  styleUrl: './search-bar.component.css',
})
export class SearchBarComponent implements OnInit {
  ngOnInit(): void {
    this.changeStartDate$.subscribe((date) => {
      this.startDate = date;
    });
    this.changeEndDate$.subscribe((date) => {
      this.endDate = date;
    });
  }
  isCheckinOpen = false;
  changeStartDate$: Subject<Date | undefined | null> = new Subject();
  changeEndDate$: Subject<Date | undefined> = new Subject();
  startDate: Date | undefined | null;
  endDate: Date | undefined;

  changeStartDate(date: Date | undefined | null) {
    if (date) {
      const offset = date?.getTimezoneOffset();

      date = new Date(date?.getTime() - offset * 60 * 1000);
    }
    this.changeEndDate$.next(undefined);
    this.changeStartDate$.next(date);
  }

  changeEndDate(date: Date) {
    if (date) {
      const offset = date?.getTimezoneOffset();

      date = new Date(date?.getTime() - offset * 60 * 1000);
    }
    this.changeEndDate$.next(date);
  }

  filterAvailableDates = (d: Date | null) => {
    let date = d || new Date();
    const today = new Date();
    let yesterday = new Date();
    yesterday.setDate(yesterday.getDate() - 1);

    return date >= yesterday;
  };
}
