import { Routes } from '@angular/router';
import { SignInComponent } from './components/sign-in/sign-in.component';
import { RegisterComponent } from './components/register/register.component';
import { PropertiesComponent } from './components/properties/properties.component';
import { UnitsComponent } from './components/units/units.component';
import { PropertyComponent } from './components/property/property.component';

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
    path: 'properties/:id',
    component: PropertyComponent,
  },
  {
    path: 'units',
    component: UnitsComponent,
  },
];
