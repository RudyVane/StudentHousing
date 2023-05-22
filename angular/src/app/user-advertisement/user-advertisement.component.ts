import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-user-advertisement',
  templateUrl: './user-advertisement.component.html',
  styleUrls: ['./user-advertisement.component.css']
})
export class UserAdvertisementComponent {
  form!: FormGroup;
  formFields!: any[]; // Define the form fields array

  constructor(private formBuilder: FormBuilder, private http: HttpClient) {}


  ngOnInit() {
    this.formFields = [
      { label: 'Full Name:', id: 'fullName', name: 'fullName', required: true },
      { label: 'Username:', id: 'username', name: 'username', required: true },
      { label: 'Password:', id: 'password', name: 'password', required: true },
      { label: 'Registration Date:', id: 'registrationDate', name: 'registrationDate', required: true },
      { label: 'Photo URL:', id: 'photoURL', name: 'photoURL', required: false },
      { label: 'Email:', id: 'email', name: 'email', required: true },
      { label: 'Telephone:', id: 'telephone', name: 'telephone', required: true },
      { label: 'Age:', id: 'age', name: 'age', required: true },
      { label: 'Gender:', id: 'gender', name: 'gender', required: false },
      { label: 'Role:', id: 'role', name: 'role', required: false },
      { label: 'Status:', id: 'status', name: 'status', required: false },
      { label: 'Language:', id: 'language', name: 'language', required: false },
      { label: 'Max Rent:', id: 'maxRent', name: 'maxRent', required: false },
      { label: 'Preferred City:', id: 'prefCity', name: 'prefCity', required: false },
      { label: 'Preferred Gender:', id: 'prefGender', name: 'prefGender', required: false },
      { label: 'Preferred Kitchen:', id: 'prefKitchen', name: 'prefKitchen', required: false },
      { label: 'Preferred Shower:', id: 'prefShower', name: 'prefShower', required: false },
      { label: 'Preferred Toilet:', id: 'prefToilet', name: 'prefToilet', required: false },
      { label: 'Preferred Living:', id: 'prefLiving', name: 'prefLiving', required: false },
      { label: 'Preferred Internet:', id: 'prefInternet', name: 'prefInternet', required: false },
      { label: 'Preferred Energy Label:', id: 'prefEnergyLabel', name: 'prefEnergyLabel', required: false },
      { label: 'Preferred Pets:', id: 'prefPets', name: 'prefPets', required: false },
      { label: 'Preferred Smoking Inside:', id: 'prefSmokingInside', name: 'prefSmokingInside', required: false },
      { label: 'Preferred Roommates:', id: 'prefRoommates', name: 'prefRoommates', required: false },
      { label: 'Preferred Distance to Zipcode:', id: 'prefDistanceToZipcode', name: 'prefDistanceToZipcode', required: false },
      { label: 'Preferred Zipcode:', id: 'prefZipcode', name: 'prefZipcode', required: false }
    ];

    this.form = this.formBuilder.group({
      fullName: ['', Validators.required],
      username: ['', Validators.required],
      password: ['', Validators.required],
      registrationDate: ['', Validators.required],
      photoURL: [''],
      email: ['', [Validators.required, Validators.email]],
      telephone: ['', Validators.required],
      age: ['', Validators.required],
      gender: [''],
      role: [''],
      status: [''],
      language: [''],
      maxRent: [''],
      prefCity: [''],
      prefGender: [''],
      prefKitchen: [''],
      prefShower: [''],
      prefToilet: [''],
      prefLiving: [''],
      prefInternet: [''],
      prefEnergyLabel: [''],
      prefPets: [''],
      prefSmokingInside: [''],
      prefRoommates: [''],
      prefDistanceToZipcode: [''],
      prefZipcode: ['']
    });

  }

  submitForm() {
    if (this.form.valid) {
      const formData = JSON.stringify(this.form.value);
      this.http.post('/user-advertisement', formData).subscribe(
        (response) => {
          console.log('Form submission successful:', response);
          // Handle any success logic here
        },
        (error) => {
          console.error('Form submission error:', error);
          // Handle any error logic here
        }
      );
    } else {
      // Form is invalid, display error messages or take appropriate action
    }
  }
}

