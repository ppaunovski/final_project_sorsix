import { Host } from './host';

export interface Property {
  id: Number;
  name: String;
  streetName: String;
  streetNumber: Number;
  city: String;
  longitude: Number;
  latitude: Number;
  host: Host;
  swiftOfIban: String;
  transactionalNumber: String;
  allowDynamicPrice: Boolean;
  checkInFrom: String;
  checkInTo: String;
  checkOutFrom: String;
  checkOutTo: String;
  description: String;
}
