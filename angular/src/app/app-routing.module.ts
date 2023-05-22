import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormComponent } from './form/form.component';
import { RegisterComponent } from './register/register.component';
import { PropertyListComponent } from './property-list/property-list.component';
import {UserAdvertisementComponent} from "./user-advertisement/user-advertisement.component";

const routes: Routes = [
  { path: '', redirectTo: '/form', pathMatch: 'full' },
  { path: 'form', component: FormComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'property-list', component: PropertyListComponent },
  { path: 'user-advertisement', component: UserAdvertisementComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
