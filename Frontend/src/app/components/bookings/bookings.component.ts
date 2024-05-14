import { Component, OnInit } from '@angular/core';
import { BookingService } from '../../service/booking.service';
import { ActivatedRoute } from '@angular/router';
import { filter, tap } from 'rxjs';
import { Booking } from '../../model/Booking';
import { BookingPreviewComponent } from '../booking-preview/booking-preview.component';
import { ErrorResponse } from '../../model/ErrorResponse';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { ErrorPageComponent } from '../error-page/error-page.component';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-bookings',
  standalone: true,
  imports: [
    BookingPreviewComponent,
    MatProgressSpinnerModule,
    ErrorPageComponent,
  ],
  templateUrl: './bookings.component.html',
  styleUrl: './bookings.component.css',
})
export class BookingsComponent implements OnInit {
  bookings: Booking[] = [];
  loading = false;
  error: ErrorResponse | null = null;

  constructor(
    private bookingService: BookingService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.bookingService
      .getBookingsByUserId()
      .pipe(
        tap(() => {
          this.loading = true;
          this.error = null;
        })
      )
      .subscribe({
        next: (bookings) => {
          this.loading = false;
          this.error = null;

          this.bookings = bookings;
          console.log('bookings', bookings);
        },
        error: (error: HttpErrorResponse) => {
          this.error = error.error;
          this.loading = false;
        },
      });
  }
}
