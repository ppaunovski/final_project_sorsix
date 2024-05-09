import { PropertyImage } from './PropertyImage';

export interface PropertyInfo {
  id: number;

  cityName: string;

  address: string;

  averageRating: number;

  description: string;

  pricePerNight: number;

  images: PropertyImage[];
}
