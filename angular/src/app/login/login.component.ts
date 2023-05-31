import { Component } from '@angular/core';
import { FormBuilder} from "@angular/forms";
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  userDetails = this.formBuilder.group({
    username: '',
    password: ''
  });

  constructor(
    private http: HttpClient,
    private formBuilder: FormBuilder
  ) {}

  onSubmit(): void {
    if (this.userDetails.valid) {
      console.log(this.userDetails.value);
      this.http.post<any>('/api/login', this.userDetails.value).subscribe(
        (response) => {
          console.log('Form submission successful:', response);
          // Handle any success logic here
        },
        (error) => {
          console.error('Form submission error:', error.message);
          // Handle any error logic here
        }
      );
    } else {
      // Form is invalid, display error messages or take appropriate action
      console.log('There was another error');
    }
  }
}
