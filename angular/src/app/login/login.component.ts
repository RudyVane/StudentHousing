import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators} from "@angular/forms";
import { HttpClient } from '@angular/common/http';
import { first } from 'rxjs/operators';

import { AlertService } from "../services/alert.service";
import { AccountService } from "../services/account.service";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  form!: FormGroup;
  loading = false;
  submitted = false;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private accountService: AccountService,
    private alertService: AlertService
  ) { }

  ngOnInit() {
    this.form = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  // convenience getter for easy access to form fields
  get f() { return this.form.controls; }

  async getCsrfToken() {
    const response = await fetch('/api/csrf')
    const payload = await response.json()
    return payload.token
  }


  async onSubmit() {
    this.submitted = true;

    // reset alerts on submit
    this.alertService.clear();

    // stop here if form is invalid
    if (this.form.invalid) {
      return;
    }


    this.loading = true;
    let response = this.accountService.login(this.f.username.value, this.f.password.value);
    localStorage.setItem('user', JSON.stringify(await response));
  }
}

// export class LoginComponent {
//
//   userDetails = this.formBuilder.group({
//     username: '',
//     password: ''
//   });
//
//   constructor(
//     private http: HttpClient,
//     private formBuilder: FormBuilder
//   ) {}
//
//   onSubmit(): void {
//     if (this.userDetails.valid) {
//       console.log(this.userDetails.value);
//       this.http.post<any>('/api/login', this.userDetails.value).subscribe(
//         (response) => {
//           console.log('Form submission successful:', response);
//           // Handle any success logic here
//         },
//         (error) => {
//           console.error('Form submission error:', error.message);
//           // Handle any error logic here
//         }
//       );
//     } else {
//       // Form is invalid, display error messages or take appropriate action
//       console.log('There was another error');
//     }
//   }
// }
