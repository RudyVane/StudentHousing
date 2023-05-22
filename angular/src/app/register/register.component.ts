import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  form!: FormGroup;
  formFields!: any[]; // Define the form fields array

  constructor(private formBuilder: FormBuilder, private http: HttpClient) {}


  ngOnInit() {
    this.formFields = [
      { label: 'First Name:', id: 'firstName', name: 'firstName', required: true },
      { label: 'Last Name:', id: 'lastName', name: 'lastName', required: true },
      { label: 'Email:', id: 'email', name: 'email', required: true },
      { label: 'Password:', id: 'password', name: 'password', required: true },
      { label: 'Confirm Password:', id: 'confirmPassword', name: 'confirmPassword', required: true }
    ];

    this.form = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required]
    });
  }

  submitForm() {
    if (this.form.valid) {
      const formData = JSON.stringify(this.form.value);
      this.http.post('/register', formData).subscribe(
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
