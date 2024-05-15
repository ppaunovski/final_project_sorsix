import { ComponentRatingRequest } from './ComponentRatingReqeust';

export interface ReviewRequest {
  propertyId: number;
  bookingId: number;
  comment: string;
  componentRatings: ComponentRatingRequest[];
}
