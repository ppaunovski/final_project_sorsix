import { Component, Input, OnInit } from '@angular/core';
import { Booking } from '../../model/Booking';
import { ImageToUrlService } from '../../service/image-to-url.service';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { BookingService } from '../../service/booking.service';
import { getLocaleFirstDayOfWeek } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-booking-preview',
  standalone: true,
  imports: [MatButtonModule, MatIconModule],
  templateUrl: './booking-preview.component.html',
  styleUrl: './booking-preview.component.css',
})
export class BookingPreviewComponent implements OnInit {
  review() {
    this.router.navigate(['/properties', this.booking?.property.id, 'review']);
  }

  cancel() {
    if (this.booking) {
      console.log('cancel');

      this.bookingService.cancelBooking(this.booking.id).subscribe({
        next: (resp) => {
          this.booking = resp;
        },
        error: (error) => {
          console.log(error);
        },
      });
    }
  }

  showCancel() {
    if (this.booking) {
      const start = new Date(this.booking.checkIn);
      const end = new Date(this.booking.checkOut);
      const today = new Date();

      return this.booking.status != 'CANCELLED' && start > today;
    }
    return false;
  }

  @Input()
  booking: Booking | undefined;
  imageUrl: string = '';

  @Input()
  showButtons = false;

  constructor(
    private service: ImageToUrlService,
    private bookingService: BookingService,
    private router: Router
  ) {}

  ngOnInit(): void {
    if (this.booking && this.booking.property)
      this.imageUrl = this.service.dataURItoBlob(
        this.booking.property.images[0]
      );
  }
}
