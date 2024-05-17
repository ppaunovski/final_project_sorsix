import { Routes } from '@angular/router';
import { SignInComponent } from './components/sign-in/sign-in.component';
import { RegisterComponent } from './components/register/register.component';
import { PropertiesComponent } from './components/properties/properties.component';
import { UnitsComponent } from './components/units/units.component';
import { PropertyComponent } from './components/property/property.component';
import { CreatePropertyComponent } from './components/create-property/create-property.component';
import { ProfileComponent } from './components/profile/profile.component';
import { PropertyFormComponent } from './components/property-form/property-form.component';
import { BookingsComponent } from './components/bookings/bookings.component';
import { ConfirmBookingComponent } from './components/confirm-booking/confirm-booking.component';
import { MyPropertiesComponent } from './components/my-properties/my-properties.component';
import { ReviewComponentComponent } from './components/review-component/review-component.component';
import { ImageGalleryComponent } from './components/image-gallery/image-gallery.component';

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
    component: PropertyFormComponent,
  },
  {
    path: 'properties/:id',
    component: PropertyComponent,
  },
  {
    path: 'properties/:id/review',
    component: ReviewComponentComponent,
  },
  {
    path: 'properties/:id/images',
    component: ImageGalleryComponent,
  },
  {
    path: 'units',
    component: UnitsComponent,
  },
  {
    path: 'profiles',
    component: ProfileComponent,
  },
  {
    path: 'profiles/bookings',
    component: BookingsComponent,
  },
  {
    path: 'profiles/properties',
    component: MyPropertiesComponent,
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
