import {Component, OnInit} from '@angular/core';
import { HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent {
  accountDetails: any[] = [];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.getAccountDetails();
  }

  getAccountDetails(): void {
    this.http.get<any[]>('/api/account')
      .subscribe(data => {
        this.accountDetails = data;
      })
  }

}
