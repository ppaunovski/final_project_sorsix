import { PropertyImage } from './PropertyImage';

export interface PropertyInfo {
  id: number;
  cityName: string;
  name: string;
  address: string;
  averageRating: number;
  description: string;
  pricePerNight: number;
  image: PropertyImage;
  type: string;
  longitude: number;
  latitude: number;
}
