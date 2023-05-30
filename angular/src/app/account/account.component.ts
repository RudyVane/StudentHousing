import {Component, OnInit} from '@angular/core';
import { HttpClient} from "@angular/common/http";
import { User } from "../models/user";
import { AlertService } from "../services/alert.service";
import { NgForm } from '@angular/forms';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {
  user: User = {} as User;
  isLoading: boolean = true;
  number!: number;
  id: number | null = null;

  constructor(
    private http: HttpClient,
    private alertService: AlertService
  ) { }

  ngOnInit(): void {
    this.getUser();
  }

  private setUser(response: User): void {
    this.user = response;
    this.isLoading = false;
  }

  getUser(): void {
    if (this.id === null) {
      this.http.get<User>('/api/account').subscribe(
        (response: User) => {
          this.setUser(response);
        },
        (error) => {
          console.error('Error fetching user:', error);
          this.alertService.error("Your user was not found");
          this.isLoading = false;
        }
      );
    } else {
      const url = `/api/account?id=${this.number}`;
      this.http.get<User>(url).subscribe(
        (response) => {
          this.setUser(response);
        },
        (error) => {
          console.error('Error fetching user:', error);
          this.alertService.error("Your user was not found");
          this.isLoading = false;
        }
      );
    }
  }

  submitForm() {
    if (this.number) {
      this.id = this.number;
    }
    this.getUser();
  }
}
