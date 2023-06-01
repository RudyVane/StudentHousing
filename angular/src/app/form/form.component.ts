import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {
  form!: FormGroup;
  constructor(private formBuilder: FormBuilder, private http: HttpClient) {}

  ngOnInit() {
    this.form = this.formBuilder.group({
      external_id: ['', Validators.required],
      area_sqm: ['', Validators.required],
      city: ['', Validators.required],
      rent: ['', Validators.required],
      additional_costs: ['', Validators.required],
      deposit: ['', Validators.required],
      latitude: ['', Validators.required],
      longitude: ['', Validators.required],
      description: ['', Validators.required],
      cover_image_url: ['', Validators.required],
      postalCode: [''],
      propertyType: [''],
      rawAvailability: [''],
      rentDetail: [''],
      title: [''],
      descriptionNonTranslated: [''],
      descriptionTranslated: [''],
      energyLabel: [''],
      gender: [''],
      internet: [''],
      isRoomActive: [''],
      kitchen: [''],
      living: [''],
      matchAge: [''],
      matchCapacity: [''],
      matchGender: [''],
      matchLanguages: [''],
      matchStatus: [''],
      pageDescription: [''],
      pageTitle: [''],
      pets: [''],
      registrationCost: [''],
      roommates: [''],
      shower: [''],
      smokingInside: [''],
      toilet: ['']
    });
  }
  formFields = [
    { label: 'External ID:', id: 'external_id', name: 'external_id', required: true },
    { label: 'Square meters:', id: 'area_sqm', name: 'area_sqm', required: true },
    { label: 'City:', id: 'city', name: 'city', required: true },
    { label: 'Rent:', id: 'rent', name: 'rent', required: true },
    { label: 'Additional costs:', id: 'additional_costs', name: 'additional_costs', required: true },
    { label: 'Deposit:', id: 'deposit', name: 'deposit', required: true },
    { label: 'Latitude:', id: 'latitude', name: 'latitude', required: true },
    { label: 'Longitude:', id: 'longitude', name: 'longitude', required: true },
    { label: 'Description:', id: 'description', name: 'description', required: true },
    { label: 'Cover image URL:', id: 'cover_image_url', name: 'cover_image_url', required: true },
    { label: 'Postal Code:', id: 'postalCode', name: 'postalCode' },
    { label: 'Property Type:', id: 'propertyType', name: 'propertyType' },
    { label: 'Raw Availability:', id: 'rawAvailability', name: 'rawAvailability' },
    { label: 'Rent Detail:', id: 'rentDetail', name: 'rentDetail' },
    { label: 'Title:', id: 'title', name: 'title' },
    { label: 'Description (Non-Translated):', id: 'descriptionNonTranslated', name: 'descriptionNonTranslated' },
    { label: 'Description (Translated):', id: 'descriptionTranslated', name: 'descriptionTranslated' },
    { label: 'Energy Label:', id: 'energyLabel', name: 'energyLabel' },
    { label: 'Gender:', id: 'gender', name: 'gender' },
    { label: 'Internet:', id: 'internet', name: 'internet' },
    { label: 'Is Room Active:', id: 'isRoomActive', name: 'isRoomActive' },
    { label: 'Kitchen:', id: 'kitchen', name: 'kitchen' },
    { label: 'Living:', id: 'living', name: 'living' },
    { label: 'Match Age:', id: 'matchAge', name: 'matchAge' },
    { label: 'Match Capacity:', id: 'matchCapacity', name: 'matchCapacity' },
    { label: 'Match Gender:', id: 'matchGender', name: 'matchGender' },
    { label: 'Match Languages:', id: 'matchLanguages', name: 'matchLanguages' },
    { label: 'Match Status:', id: 'matchStatus', name: 'matchStatus' },
    { label: 'Page Description:', id: 'pageDescription', name: 'pageDescription' },
    { label: 'Page Title:', id: 'pageTitle', name: 'pageTitle' },
    { label: 'Pets:', id: 'pets', name: 'pets' },
    { label: 'Registration Costs:', id: 'registrationCost', name: 'registrationCost' },
    { label: 'Roommates:', id: 'roommates', name: 'roommates' },
    { label: 'Shower:', id: 'shower', name: 'shower' },
    { label: 'Smoking Inside:', id: 'smokingInside', name: 'smokingInside'},
    { label: 'Toilet:', id: 'toilet', name: 'toilet'}];

  submitForm() {
    if (this.form.valid) {
      this.http.post('http://localhost:8080/properties', this.form.value).subscribe(
        (response) => {
          console.log('Form submission successful:', response);
          // Handle any success logic here
        },
        (error) => {
          console.error('Form submission error:', error);
          // Handle any error logic here
        }
      );
    }
    else {
      // Form is invalid, display error messages or take appropriate action
    }
  }
}
