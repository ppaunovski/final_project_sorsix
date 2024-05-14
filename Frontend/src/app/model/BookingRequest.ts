import { Property } from './property';

export interface BookingRequest {
  checkInDate: string;
  checkOutDate: string;
  numberOfAdults: number;
  numberOfChildren: number;
  numberOfPets: number;
}
