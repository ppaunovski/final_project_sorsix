import {Review} from './Review';

export interface ReviewsResponse {
  content: Review[];
  page: number;
  size: number;
  totalElements: number;
  totalPages: number;
  last: boolean;
}
