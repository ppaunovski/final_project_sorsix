import {Booking} from './Booking';

export interface BookingResponse {
  content: Booking[];
  page: number;
  size: number;
  totalElements: number;
  totalPages: number;
  last: boolean;
}
