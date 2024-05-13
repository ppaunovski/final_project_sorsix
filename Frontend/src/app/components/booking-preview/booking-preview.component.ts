import { Component, Input, OnInit } from '@angular/core';
import { Booking } from '../../model/Booking';
import { ImageToUrlService } from '../../service/image-to-url.service';

@Component({
  selector: 'app-booking-preview',
  standalone: true,
  imports: [],
  templateUrl: './booking-preview.component.html',
  styleUrl: './booking-preview.component.css',
})
export class BookingPreviewComponent implements OnInit {
  @Input()
  booking: Booking | undefined;
  imageUrl: string = '';

  constructor(private service: ImageToUrlService) {}

  ngOnInit(): void {
    if (this.booking && this.booking.property)
      this.imageUrl = this.service.dataURItoBlob(
        this.booking.property.images[0]
      );
  }
}
