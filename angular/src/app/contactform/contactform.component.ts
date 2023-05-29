import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-contactform',
  template: `
    <div class="form-container">

    <h2>Contact Form</h2>
    <form (submit)="submitForm()">
      <div>
        <label for="name">Name:</label><br>
        <input type="text" id="name" name="name" [(ngModel)]="name" required>
      </div>
      <div>
        <label for="email">Email:</label><br>
        <input type="email" id="email" name="email" [(ngModel)]="email" required>
      </div>
      <div>
        <label for="message">Message:</label><br>
        <textarea id="message" name="message" [(ngModel)]="message" required></textarea>
      </div>
      <button type="submit">Submit</button>
    </form>
  `
})
export class ContactformComponent {
  name: string = '';
  email: string = '';
  message: string = '';

  constructor(private http: HttpClient) {}

  submitForm() {
    const contactForm = {
      name: this.name,
      email: this.email,
      message: this.message
    };

    this.http.post('/property/contact', contactForm).subscribe(
      () => {
        alert('Contact form submitted successfully!');
        // Reset the form fields
        this.name = '';
        this.email = '';
        this.message = '';
      },

      (error) => {
        console.error('Failed to submit contact form:', error);
        alert('Failed to submit contact form. Please try again later.');
      }
    );
  }
}
