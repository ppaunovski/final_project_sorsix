import {PropertyInfo} from './PropertyInfo';

export interface PropertyResponse {
  content: PropertyInfo[];
  page: number;
  size: number;
  totalElements: number;
  totalPages: number;
  last: boolean;
}
