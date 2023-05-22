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
      { label: 'Username:', id: 'username', name: 'username', type: 'text', required: true },
      { label: 'Email:', id: 'email', name: 'email', type: 'text', required: true },
      { label: 'Password:', id: 'password', name: 'password', type: 'password', required: true },
      { label: 'Confirm Password:', id: 'matching_password', name: 'matching_password', type: 'password', required: true }
    ];

    this.form = this.formBuilder.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      matching_password: ['', Validators.required]
    });
  }

  submitForm() {
    if (this.form.valid) {
      this.http.post('/api/register', this.form.value).subscribe(
        (response) => {
          console.log('Form submission successful:', response);
          // Handle any success logic here
        },
        (error) => {
          console.error('Form submission error:', error.error);
          // Handle any error logic here
        }
      );
    } else {
      // Form is invalid, display error messages or take appropriate action
    }
  }
}
