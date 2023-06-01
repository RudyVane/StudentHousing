import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import { AlertService } from "../services/alert.service";
import { AccountService } from "../services/account.service";
import { ActivatedRoute } from "@angular/router";
import {User} from "../models/user";

@Component({
  selector: 'app-user-advertisement',
  templateUrl: './user-advertisement.component.html',
  styleUrls: ['./user-advertisement.component.css']
})
export class UserAdvertisementComponent {
  form!: FormGroup;
  formFields!: any[]; // Define the form fields array
  id!: number;

  // user: any;

  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private accountService: AccountService,
    private route: ActivatedRoute,
    private alertService: AlertService
  ) {
  }


  ngOnInit() {
    this.formFields = [
      {label: 'ID:', id: 'id', name: 'id', required: true},
      {label: 'Full Name:*', id: 'fullName', name: 'fullName', required: true},
      {label: 'Photo URL:', id: 'photoURL', name: 'photoURL', required: false},
      {label: 'Telephone:*', id: 'telephone', name: 'telephone', required: true},
      {label: 'Age:*', id: 'age', name: 'age', required: true},
      {label: 'Gender:', id: 'gender', name: 'gender', required: false},
      {label: 'Role:', id: 'role', name: 'role', required: false},
      {label: 'Status:*', id: 'status', name: 'status', required: true},
      {label: 'Language:', id: 'language', name: 'language', required: false},
      {label: 'Max Rent:', id: 'maxRent', name: 'maxRent', required: false},
      {label: 'Message:', id: 'ad_message', name: 'adMessage', type: 'textarea', placeholder: 'Tell something about yourself', required: false},
      {label: 'Preferred City:', id: 'prefCity', name: 'prefCity', required: false},
      {label: 'Preferred Gender:', id: 'prefGender', name: 'prefGender', required: false},
      {label: 'Preferred Kitchen:', id: 'prefKitchen', name: 'prefKitchen', required: false},
      {label: 'Preferred Shower:', id: 'prefShower', name: 'prefShower', required: false},
      {label: 'Preferred Toilet:', id: 'prefToilet', name: 'prefToilet', required: false},
      {label: 'Preferred Living:', id: 'prefLiving', name: 'prefLiving', required: false},
      {label: 'Preferred Internet:', id: 'prefInternet', name: 'prefInternet', required: false},
      {label: 'Preferred Energy Label:', id: 'prefEnergyLabel', name: 'prefEnergyLabel', required: false},
      {label: 'Preferred Pets:', id: 'prefPets', name: 'prefPets', required: false},
      {label: 'Preferred Smoking Inside:', id: 'prefSmokingInside', name: 'prefSmokingInside', required: false},
      {label: 'Preferred Roommates:', id: 'prefRoommates', name: 'prefRoommates', required: false},
      {label: 'Preferred Distance to Zipcode:', id: 'prefDistanceToZipcode', name: 'prefDistanceToZipcode', required: false},
      {label: 'Preferred Zipcode:', id: 'prefZipcode', name: 'prefZipcode', required: false}
    ];

    this.form = this.formBuilder.group({
      id: [{value: ''}],
      fullName: ['', Validators.required],
      photoURL: [''],
      telephone: ['', Validators.required],
      age: ['', Validators.required],
      gender: [''],
      role: [''],
      status: [''],
      language: [''],
      maxRent: [''],
      adMessage: [''],
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

    // Set value of ID field to the parameter ID
    this.route.queryParams.subscribe(params => {
      const id = params['id'];
      // Assign the value to the ID field in the form
      this.form.controls['id'].setValue(id);
      this.id = id;

    });

    // Get this user from the database and try to fill the form
    this.http.get<User>(`/api/advertisements/${this.id}`).subscribe(
      (data) => {
        console.log(data);
        // Check for a fullName, since that is a required field. Else just continue
        if (data.fullName != null) {
          // this.user = data;
          this.fillForm(data);
        }
      }
    )
  }

  submitForm() {
    this.alertService.clear();
    // @ts-ignore
    if (this.id != this.form.get('id').value) {
      this.alertService.error("You have changed your user ID, that's not allowed");
    } else if (this.form.valid) {
      console.log(this.form.value);
      this.http.post('/api/advertisements', this.form.value).subscribe(
        (response) => {
          console.log('Form submission successful:', response);
          this.alertService.success("Your advertisement is now visible!")

        },
        (error) => {
          console.error('Form submission error:', error);
          this.alertService.error("There was an error")
          // Handle any error logic here
        }
      );
    } else {
      // Form is invalid, display error messages or take appropriate action
    }
  }

  private fillForm(user: User) {
    this.form.setValue({
      id: user.id,
      fullName: user.fullName || '',
      photoURL: user.photoURL || '',
      telephone: user.telephone || '',
      age: user.age || 0,
      gender: user.gender || '',
      role: user.role || '',
      status: user.status || '',
      language: user.language || '',
      maxRent: user.maxRent || 0,
      adMessage: user.adMessage || '',
      prefCity: user.prefCity || '',
      prefGender: user.prefGender || '',
      prefKitchen: user.prefKitchen || '',
      prefShower: user.prefShower || '',
      prefToilet: user.prefToilet || '',
      prefLiving: user.prefLiving || '',
      prefInternet: user.prefInternet || '',
      prefEnergyLabel: user.prefEnergyLabel || '',
      prefPets: user.prefPets || '',
      prefSmokingInside: user.prefSmokingInside || '',
      prefRoommates: user.prefRoommates || '',
      prefDistanceToZipcode: user.prefDistanceToZipcode || '',
      prefZipcode: user.prefZipcode || ''
    });
  }
}
