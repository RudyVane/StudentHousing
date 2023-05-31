import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule} from '@angular/forms';
import { HttpClient, HttpClientModule} from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormComponent } from './form/form.component';
import { RegisterComponent } from './register/register.component';
import { PropertyListComponent } from './property-list/property-list.component';
import { UserAdvertisementComponent } from './user-advertisement/user-advertisement.component';
import { SearchComponent } from './search/search.component';
import { CommonModule } from '@angular/common';
import { ContactformComponent } from './contactform/contactform.component';
import { FormsModule } from '@angular/forms';
import { FindAdvertisementComponent } from './find-advertisement/find-advertisement.component';




@NgModule({
  declarations: [
    AppComponent,
    FormComponent,
    RegisterComponent,
    PropertyListComponent,
    UserAdvertisementComponent,
    SearchComponent,
    ContactformComponent,
    FindAdvertisementComponent
    // PropertyDetailsComponent
  ],
  imports: [
    BrowserModule,
    CommonModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [HttpClient],
  bootstrap: [AppComponent]
})
export class AppModule { }
