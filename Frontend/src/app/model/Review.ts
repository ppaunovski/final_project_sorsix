import { UserAccount } from './UserAccount';

export interface Review {
  id: Number;
  user: UserAccount;
  comment: string;
  reviewDate: Date;
}
