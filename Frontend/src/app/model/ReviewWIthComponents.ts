import { ComponentRating } from './ComponentRating';
import { Review } from './Review';

export interface ReviewWithComponents {
  review: Review;
  components: ComponentRating[];
}
