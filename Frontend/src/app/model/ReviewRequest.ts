import { ComponentRatingRequest } from './ComponentRatingReqeust';

export interface ReviewRequest {
  propertyId: number;
  comment: string;
  componentRatings: ComponentRatingRequest[];
}
