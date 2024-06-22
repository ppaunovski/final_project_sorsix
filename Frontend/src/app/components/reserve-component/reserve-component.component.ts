import {Component, Input, OnInit} from '@angular/core';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import {Property} from '../../model/property';
import {GuestsDialogComponent} from '../guests-dialog/guests-dialog.component';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule,} from '@angular/forms';
import {JsonPipe} from '@angular/common';
import {DateFilterFn, MatDatepickerModule,} from '@angular/material/datepicker';
import {MatFormFieldModule} from '@angular/material/form-field';
import {provideNativeDateAdapter} from '@angular/material/core';
import {Subject} from 'rxjs';
import {ReservationInfoComponent} from '../reservation-info/reservation-info.component';
import {PropertyService} from '../../service/property.service';
import {PropertyAvailability} from '../../model/PropertyAvailability';
import {CalendarComponent} from '../calendar/calendar.component';
import {DateRangeComponent} from '../date-range/date-range.component';
import {Router} from '@angular/router';
import {ErrorResponse} from '../../model/ErrorResponse';
import {HttpErrorResponse} from '@angular/common/http';
import {ErrorService} from '../../service/error.service';

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
    ReservationInfoComponent,
    CalendarComponent,
    DateRangeComponent,
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
  changeEndDate$: Subject<Date | null | undefined> = new Subject();
  startDate: Date | undefined | null;
  endDate: Date | null | undefined;
  maxDate: Date | undefined;
  availablePeriods: PropertyAvailability[] = [];
  filterAvailableDates: DateFilterFn<Date | null> = () => true;
  disabledDates = [
    new Date('2024-05-10'),
    new Date('2024-05-11'),
    new Date('2024-05-12'),
    new Date('2024-05-13'),
  ];

  range = new FormGroup({
    start: new FormControl<Date | null>(null),
    end: new FormControl<Date | null>(null),
  });

  openStartCalendar = false;
  openEndCalendar = false;
  isCheckinOpen = false;
  isCheckoutOpen = false;
  isGuestDialongOpen = false;

  error: ErrorResponse | null = null;
  loading = false;
  total = 0;

  constructor(
    private propertyService: PropertyService,
    private errorService: ErrorService,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.changeStartDate$.subscribe((start) => {
      this.startDate = start;
      if (start) this.maxDate = this.findClosestDisabledDate(start);
    });

    this.changeEndDate$.subscribe((end) => {
      this.endDate = end;

      if (this.startDate && this.endDate && this.property) {
        this.total =
          (this.property?.nightlyPrice.valueOf() *
            (this.endDate?.getTime() - this.startDate?.getTime())) /
          86400000;
      }
    });

    if (this.property)
      this.propertyService
        .getPropertyAvailability(this.property.id)
        .subscribe((resp) => {
          this.availablePeriods = resp;
          console.log('available periods', this.availablePeriods);

          this.filterAvailableDates = (d: Date | null) => {
            let date = d || new Date();
            const today = new Date();
            let yesterday = new Date();
            yesterday.setDate(yesterday.getDate() - 1);

            // console.log(
            //   'filter available dates: ',
            //   date,
            //   this.availablePeriods,
            //   this.availablePeriods.map((period) => {
            //     let start = new Date(period.startDate);
            //     let end = new Date(period.endDate);

            //     return start <= date && end >= date;
            //   })
            // );

            return (
              this.availablePeriods
                .map((period) => {
                  let start = new Date(period.startDate);
                  let end = new Date(period.endDate);

                  return start <= date && end >= date;
                })
                .includes(true) && date >= yesterday
            );
          };
        });
  }

  findClosestDisabledDate(date: Date): Date | undefined {
    // for (let d in this.availablePeriods) {
    //   if (this.disabledDates[d] > date) return this.disabledDates[d];
    // }

    return this.availablePeriods
      .filter((period) => {
        let start = new Date(period.startDate);
        let end = new Date(period.endDate);

        return start <= date && end >= date;
      })
      .map((period) => period.endDate)[0];
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
    if (date) {
      const offset = date?.getTimezoneOffset();

      date = new Date(date?.getTime() - offset * 60 * 1000);
    }
    this.isCheckinOpen = false;
    this.changeEndDate$.next(undefined);
    this.changeStartDate$.next(date);
  }

  changeEndDate(date: Date | null | undefined) {
    if (date) {
      const offset = date?.getTimezoneOffset();

      date = new Date(date?.getTime() - offset * 60 * 1000);
    }
    this.changeEndDate$.next(date);
  }

  reserveProperty() {
    console.log(
      this.startDate?.toISOString().split('T'),
      this.endDate?.toISOString()
    );

    if (this.property && this.startDate && this.endDate)
      this.propertyService
        .reserveProperty(this.property?.id, {
          checkInDate: this.startDate.toISOString().split('T')[0],
          checkOutDate: this.endDate.toISOString().split('T')[0],
          numberOfAdults: this.numberOfAdults,
          numberOfChildren: this.numberOfChildren,
          numberOfPets: this.numberOfPets,
        })
        .subscribe({
          next: (resp) => {
            this.router.navigate(['/bookings', resp.id, 'confirm']);
          },
          error: (error: HttpErrorResponse) => {
            this.error = error.error;
            this.loading = false;
          },
        });
  }

  minEndDate() {
    if (this.startDate) {
      let date = new Date(this.startDate);
      date.setDate(date.getDate() + 1);
      return date;
    }
    return undefined;
  }

  changeStateOfReservation(part: 'where' | 'checkin' | 'checkout' | 'who') {
    switch (part) {
      case 'where':
        // this.isCheckinOpen = false;
        this.isGuestDialongOpen = false;
        break;
      case 'checkin':
        this.isCheckinOpen = false;
        this.isCheckinOpen = true;
        this.isGuestDialongOpen = false;
        this.changeStartDate$.next(undefined);
        this.changeEndDate$.next(undefined);
        break;
      case 'checkout':
        if (this.startDate == undefined || this.startDate == null) {
          this.isCheckinOpen = true;
          this.isCheckoutOpen = false;
          return;
        }
        this.isGuestDialongOpen = false;
        this.changeEndDate$.next(undefined);
        this.changeStartDate$.next(this.startDate);
        break;
      case 'who':
        this.isCheckinOpen = false;
        this.isGuestDialongOpen = !this.isGuestDialongOpen;
        break;
    }
  }
}
