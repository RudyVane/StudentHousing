import {Component, OnInit} from '@angular/core';
import { HttpClient} from "@angular/common/http";
import { User } from "../models/user";
import { AlertService } from "../services/alert.service";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {
  user: User = {} as User;
  isLoading: boolean = true;

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
  }
}
