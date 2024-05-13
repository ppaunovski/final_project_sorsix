import { City } from './City';
import { PropertyType } from './PropertyType';
import { UserAccount } from './UserAccount';
import { PropertyImage } from './PropertyImage';

export interface Property {
  id: Number;
  nightlyPrice: Number;
  name: string;
  guests: Number;
  beds: Number;
  bedrooms: Number;
  bathrooms: Number;
  isGuestFavorite: Boolean;
  description: string;
  address: string;
  longitude: Number;
  latitude: Number;
  host: UserAccount;
  city: City;
  propertyType: PropertyType;
  images: PropertyImage[];
}
