import { Component } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-contactform',
  template: `
    <div class="form-container">
      <h2>Contact Form</h2>
      <form [formGroup]="contactForm" (submit)="submitForm()">
        <div>
          <label for="name">Name:</label><br>
          <input type="text" id="name" formControlName="name" required>
        </div>
        <div>
          <label for="email">Email:</label><br>
          <input type="email" id="email" formControlName="email" required>
        </div>
        <div>
          <label for="message">Message:</label><br>
          <textarea id="message" formControlName="message" required></textarea>
        </div>
        <button type="submit" [disabled]="contactForm.invalid">Submit</button>
      </form>
    </div>
  `
})
export class ContactformComponent {
  contactForm: FormGroup;


  constructor(private fb: FormBuilder, private http: HttpClient) {
    this.contactForm = new FormGroup({
      name: new FormControl('', Validators.required),
      email: new FormControl('', Validators.required),
      message: new FormControl('', Validators.required)
    });
  }




  submitForm() {
    if (this.contactForm.invalid) {
      return;
    }

    const contactForm = this.contactForm.value;

    this.http.post('/property/contact', contactForm).subscribe(
      () => {
        alert('Contact form submitted successfully!');
        this.contactForm.reset();
      },
      (error) => {
        console.error('Failed to submit contact form:', error);
        alert('Failed to submit contact form. Please try again later.');
      }
    );
  }
}
