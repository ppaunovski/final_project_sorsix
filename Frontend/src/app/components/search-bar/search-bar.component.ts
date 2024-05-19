import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { CalendarComponent } from '../calendar/calendar.component';
import { DateRangeComponent } from '../date-range/date-range.component';
import { Subject } from 'rxjs';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { GuestsDialogComponent } from '../guests-dialog/guests-dialog.component';
import { PropertyService } from '../../service/property.service';
import { ActivatedRoute, Router } from '@angular/router';
import {
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
} from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';

@Component({
  selector: 'app-search-bar',
  standalone: true,
  imports: [
    CalendarComponent,
    DateRangeComponent,
    MatButtonModule,
    MatIconModule,
    GuestsDialogComponent,
    FormsModule,
    MatFormFieldModule,
    ReactiveFormsModule,
  ],
  templateUrl: './search-bar.component.html',
  styleUrl: './search-bar.component.css',
})
export class SearchBarComponent implements OnInit {
  private formatDate(date: Date | undefined | null) {
    if (date == undefined || date == null) return '';
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0'); // January is 0!
    const day = String(date.getDate()).padStart(2, '0');

    return `${year}-${month}-${day}`;
  }
  changeFilterString(value: string) {
    this.filterString = value;
  }
  search() {
    this.isCheckinOpen = false;
    this.isCheckoutOpen = false;
    this.isGuestDialongOpen = false;

    console.log('SEARCH', this.numberOfAdults);

    const queryParams = {
      filterString: encodeURI(this.filterString),
      checkIn: this.formatDate(this.startDate),
      checkOut: this.formatDate(this.endDate),
      adults: this.numberOfAdults.toString(),
      children: this.numberOfChildren.toString(),
      pets: this.numberOfPets.toString(),
      page: 0,
      size: 10,
    };

    this.router.navigate(['/properties'], { queryParams: queryParams });
  }
  constructor(private router: Router, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe({
      next: (params) => {
        this.filterString = params['filterString']
          ? decodeURI(params['filterString'])
          : '';
        this.startDate = params['checkIn']
          ? new Date(Date.parse(params['checkIn']))
          : undefined;
        this.endDate = params['checkOut']
          ? new Date(Date.parse(params['checkOut']))
          : undefined;
        this.numberOfAdults = params['adults'] ?? 1;
        this.numberOfChildren = params['children'] ?? 0;
        this.numberOfPets = params['pets'] ?? 0;

        this.isCheckinOpen = false;
        this.isCheckoutOpen = false;
        console.log('filterString on init', this.filterString);
      },
    });
    this.changeStartDate$.subscribe((date) => {
      this.startDate = date;
    });
    this.changeEndDate$.subscribe((date) => {
      this.endDate = date;
    });
  }
  isCheckinOpen = false;
  isCheckoutOpen = false;
  isGuestDialongOpen = false;
  changeStartDate$: Subject<Date | undefined | null> = new Subject();
  changeEndDate$: Subject<Date | null | undefined> = new Subject();

  startDate: Date | undefined | null;
  endDate: Date | undefined | null;
  numberOfAdults = 1;
  numberOfChildren = 0;
  numberOfPets = 0;
  filterString = '';

  form: FormGroup = new FormGroup<SearchRequest>({
    filterString: new FormControl<string>('', { nonNullable: false }),
    startDate: new FormControl<Date | undefined | null>(undefined, {
      nonNullable: false,
    }),
    endDate: new FormControl<Date | undefined | null>(undefined, {
      nonNullable: false,
    }),
    numberOfAdults: new FormControl<number>(1, { nonNullable: false }),
    numberOfChildren: new FormControl<number>(0, { nonNullable: false }),
    numberOfPets: new FormControl<number>(0, { nonNullable: false }),
  });

  changeStartDate(date: Date | undefined | null) {
    if (date) {
      const offset = date?.getTimezoneOffset();

      date = new Date(date?.getTime() - offset * 60 * 1000);
    }
    this.isCheckoutOpen = true;
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
    this.isCheckoutOpen = false;
  }

  filterAvailableDates = (d: Date | null) => {
    let date = d || new Date();
    const today = new Date();
    let yesterday = new Date();
    yesterday.setDate(yesterday.getDate() - 1);

    return date >= yesterday;
  };

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

  changeStateOfSearchBar(part: 'where' | 'checkin' | 'checkout' | 'who') {
    switch (part) {
      case 'where':
        this.isCheckinOpen = false;
        this.isGuestDialongOpen = false;
        break;
      case 'checkin':
        this.isCheckinOpen = !this.isCheckinOpen;
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

interface SearchRequest {}
