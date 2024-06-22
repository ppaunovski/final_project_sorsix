import {Component, OnInit} from '@angular/core';
import {Booking} from '../../model/Booking';
import {BookingService} from '../../service/booking.service';
import {ActivatedRoute, Router} from '@angular/router';
import {filter, map, mergeMap, tap} from 'rxjs';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {BookingPreviewComponent} from '../booking-preview/booking-preview.component';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';

@Component({
  selector: 'app-confirm-booking',
  standalone: true,
  imports: [
    MatProgressSpinnerModule,
    BookingPreviewComponent,
    MatButtonModule,
    MatIconModule,
  ],
  templateUrl: './confirm-booking.component.html',
  styleUrl: './confirm-booking.component.css',
})
export class ConfirmBookingComponent implements OnInit {
  cancel() {
    if (this.booking)
      this.bookingService.cancelBooking(this.booking.id).subscribe({
        next: (resp) => {
          this.router.navigate(['/profiles', 'bookings']);
        },
        error: (error) => {
          console.log(error);
          this.router.navigate(['/profiles', 'bookings']);
        },
      });
  }

  confirm() {
    if (this.booking)
      this.bookingService.confirmBooking(this.booking.id).subscribe({
        next: (resp) => {
          this.router.navigate(['/profiles', 'bookings']);
        },
        error: (error) => {
          console.log(error);
        },
      });
  }

  booking: Booking | undefined;
  loading = false;
  error: any;

  constructor(
    private bookingService: BookingService,
    private route: ActivatedRoute,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.route.paramMap
      .pipe(
        tap(() => {
          this.loading = true;
          this.error = null;
        }),
        filter((it) => it.has('id')),
        map((it) => it.get('id')!),
        mergeMap((id) => this.bookingService.getBookingById(+id))
      )
      .subscribe({
        next: (booking) => {
          this.loading = false;
          this.error = null;
          this.booking = booking;
        },
        error: (error) => {
          this.loading = false;
          this.error = error;
        },
      });
  }
}
