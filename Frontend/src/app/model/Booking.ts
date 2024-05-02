import { UserAccount } from './UserAccount';
import { Property } from './property';

export interface Booking {
  id: number;
  guest: UserAccount;
  property: Property;
  checkIn: Date;
  checkOut: Date;
  nightlyPrice: number;
  serviceFee: number;
  cleaningFee: number;
  status: string;
}
