import { Component, OnInit } from '@angular/core';
import { User } from "../models/user";
import {HttpClient} from "@angular/common/http";
import {AlertService} from "../services/alert.service";

@Component({
  selector: 'app-advertisements',
  templateUrl: './advertisements.component.html',
  styleUrls: ['./advertisements.component.css']
})
export class AdvertisementsComponent {
  users: User[] = [];
  isLoading: boolean = true;

  constructor(
    private http: HttpClient,
    private alertService: AlertService
  ) {
  }

  ngOnInit(): void {
    this.getAllAdvertisements();
  }

  private setAdvertisements(response: User[]): void {
    this.users = response;
    this.isLoading = false;
  }


  getAllAdvertisements(): void {
    this.http.get<User[]>('/api/advertisements').subscribe(
      (response: User[]) => {
        this.setAdvertisements(response);
      },
      (error) => {
        console.error('Error fetching users:', error);
        this.alertService.error("Error fetching users");
        this.isLoading = false;
      }
    );
  }

}
