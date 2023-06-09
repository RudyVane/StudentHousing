import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormComponent } from './form/form.component';
import { RegisterComponent } from './register/register.component';
import { PropertyListComponent } from './property-list/property-list.component';
import { UserAdvertisementComponent } from "./user-advertisement/user-advertisement.component";
import { HomeComponent } from "./home/home.component";
import { LoginComponent } from "./login/login.component";
import { AccountComponent } from "./account/account.component";
import { AlertComponent } from "./alert/alert.component";
// import { PropertyDetailsComponent } from './property-details/property-details.component';
import {AdvertisementsComponent} from "./advertisements/advertisements.component";

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'form', component: FormComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'account', component: AccountComponent },
  { path: 'properties', component: PropertyListComponent },
  { path: 'user-advertisement', component: UserAdvertisementComponent },
  // { path: 'properties/:id', component: PropertyDetailsComponent },
  { path: 'advertisements', component: AdvertisementsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
