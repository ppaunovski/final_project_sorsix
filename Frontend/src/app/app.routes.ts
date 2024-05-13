import { Routes } from '@angular/router';
import { SignInComponent } from './components/sign-in/sign-in.component';
import { RegisterComponent } from './components/register/register.component';
import { PropertiesComponent } from './components/properties/properties.component';
import { UnitsComponent } from './components/units/units.component';
import { PropertyComponent } from './components/property/property.component';
import { CreatePropertyComponent } from './components/create-property/create-property.component';
import { ProfileComponent } from './components/profile/profile.component';
import { BookingsComponent } from './components/bookings/bookings.component';
import { ConfirmBookingComponent } from './components/confirm-booking/confirm-booking.component';

export const routes: Routes = [
  {
    path: 'login',
    component: SignInComponent,
  },
  {
    path: 'register',
    component: RegisterComponent,
  },
  {
    path: 'properties',
    component: PropertiesComponent,
  },
  {
    path: 'properties/create',
    component: CreatePropertyComponent,
  },
  {
    path: 'properties/:id',
    component: PropertyComponent,
  },

  {
    path: 'units',
    component: UnitsComponent,
  },
  {
    path: 'profiles/:id',
    component: ProfileComponent,
  },
  {
    path: 'profiles/:id/bookings',
    component: BookingsComponent,
  },
  {
    path: 'bookings/:id/confirm',
    component: ConfirmBookingComponent,
  },
  {
    path: '',
    component: PropertiesComponent,
  },
];
