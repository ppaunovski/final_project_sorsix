import {PropertyInfo} from './PropertyInfo';
import {UserAccount} from './UserAccount';

export interface BookingForReview {
  id: number;
  guest: UserAccount;
  property: PropertyInfo;
  checkIn: Date;
  checkOut: Date;
  nightlyPrice: number;
  serviceFee: number;
  cleaningFee: number;
  status: string;
}
