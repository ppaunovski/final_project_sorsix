import { Component, OnInit } from '@angular/core';
import { BookingService } from '../../service/booking.service';
import { ActivatedRoute } from '@angular/router';
import { filter } from 'rxjs';
import { Booking } from '../../model/Booking';
import { BookingPreviewComponent } from '../booking-preview/booking-preview.component';

@Component({
  selector: 'app-bookings',
  standalone: true,
  imports: [BookingPreviewComponent],
  templateUrl: './bookings.component.html',
  styleUrl: './bookings.component.css',
})
export class BookingsComponent implements OnInit {
  bookings: Booking[] = [];
  constructor(
    private bookingService: BookingService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.bookingService.getBookingsByUserId().subscribe((bookings) => {
      this.bookings = bookings;
      console.log('bookings', bookings);
    });
  }
}
