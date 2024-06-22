import {Review} from './Review';
import {ReviewComponent} from './ReviewComponent';

export interface ComponentRating {
  id: Number;
  rating: Number;
  reviewComponent: ReviewComponent;
  userReview: Review;
}
