import {FormControl} from "@angular/forms";
import {PropertyType} from "../PropertyType";
import {City} from "../City";

export interface PropertyRequest {
  nightlyPrice: FormControl<Number>;
  name: FormControl<string>;
  guests: FormControl<Number>;
  beds: FormControl<Number>;
  bedrooms: FormControl<Number>;
  bathrooms: FormControl<Number>;
  isGuestFavorite: FormControl<Boolean>;
  description: FormControl<string>;
  address: FormControl<string>;
  longitude: FormControl<Number>;
  latitude: FormControl<Number>;
  city: FormControl<City>;
  propertyType: FormControl<PropertyType>;
  attributes: FormControl<Number[]>;
}
