import { Component, OnInit } from '@angular/core';
import { BookingService } from '../../service/booking.service';
import { ActivatedRoute, Router } from '@angular/router';
import { filter, mergeMap, tap } from 'rxjs';
import { Booking } from '../../model/Booking';
import { BookingPreviewComponent } from '../booking-preview/booking-preview.component';
import { ErrorResponse } from '../../model/ErrorResponse';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { ErrorPageComponent } from '../error-page/error-page.component';
import { HttpErrorResponse } from '@angular/common/http';
import { MatPaginatorModule, PageEvent } from '@angular/material/paginator';
import { BookingResponse } from '../../model/BookingResponse';

@Component({
  selector: 'app-bookings',
  standalone: true,
  imports: [
    BookingPreviewComponent,
    MatProgressSpinnerModule,
    ErrorPageComponent,
    MatPaginatorModule,
  ],
  templateUrl: './bookings.component.html',
  styleUrl: './bookings.component.css',
})
export class BookingsComponent implements OnInit {
  bookingResponse: BookingResponse | undefined;
  loading = false;
  error: ErrorResponse | null = null;
  page = 0;
  size = 10;

  constructor(
    private bookingService: BookingService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.queryParams
      .pipe(
        tap(() => {
          this.loading = true;
          this.error = null;
        }),
        // filter(
        //   (params) =>
        //     params['filterString'] &&
        //     params['checkIn'] &&
        //     params['checkOut'] &&
        //     params['adults'] &&
        //     params['children'] &&
        //     params['pets']
        // ),
        mergeMap((params) => {
          this.page = params['page'] ?? 0;
          this.size = params['size'] ?? 10;
          return this.bookingService.getBookingsByUserId(this.page, this.size);
        })
      )
      .subscribe({
        next: (resp) => {
          this.loading = false;
          this.error = null;

          this.bookingResponse = resp;
          console.log('bookings', resp);
        },
        error: (error: HttpErrorResponse) => {
          this.error = error.error;
          this.loading = false;
        },
      });
  }

  handlePageChange(event: PageEvent) {
    const page = event.pageIndex;
    const size = event.pageSize;
    if (page != null) this.page = page;
    if (size != null) this.size = size;

    this.route.queryParams.subscribe((params) => {
      var queryParams = {
        page: this.page,
        size: this.size,
      };
      this.router.navigate(['/profiles', 'bookings'], {
        queryParams: queryParams,
      });
    });
  }
}
