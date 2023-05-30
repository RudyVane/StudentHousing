import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormComponent } from './form/form.component';
import { RegisterComponent } from './register/register.component';
import { PropertyListComponent } from './property-list/property-list.component';
import { UserAdvertisementComponent } from './user-advertisement/user-advertisement.component';
import { SearchComponent } from './search/search.component';
import { TopnavComponent } from './topnav/topnav.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { AccountComponent } from './account/account.component';
import { AlertComponent } from "./alert/alert.component";
import { AdvertisementsComponent } from './advertisements/advertisements.component';
import { ContactformComponent } from './contactform/contactform.component';
import { FindAdvertisementComponent } from './find-advertisement/find-advertisement.component';




@NgModule({
  declarations: [
    AppComponent,
    AlertComponent,
    FormComponent,
    RegisterComponent,
    PropertyListComponent,
    UserAdvertisementComponent,
    SearchComponent,
    TopnavComponent,
    HomeComponent,
    LoginComponent,
    AccountComponent,
    AdvertisementsComponent
    ContactformComponent,
    FindAdvertisementComponent
    // PropertyDetailsComponent
  ],
  imports: [
    BrowserModule,
    CommonModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [HttpClient],
  bootstrap: [AppComponent]
})
export class AppModule { }
