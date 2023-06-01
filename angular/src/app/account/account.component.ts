import {Component, OnInit} from '@angular/core';
import { HttpClient} from "@angular/common/http";
import { User } from "../models/user";
import { AlertService } from "../services/alert.service";
import { Router } from '@angular/router';
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
  id: number = 79;
  hasAd: boolean = false;

  constructor(
    private http: HttpClient,
    private router: Router,
    private alertService: AlertService
  ) { }

  ngOnInit(): void {
    this.getUser();
  }

  private setUser(response: User): void {
    this.user = response;
    this.id = this.user.id;
    this.isLoading = false;
  }

  getUser(): void {
    this.alertService.clear();
    if (this.id === null) {
      this.hasAd = false;
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
      const url = `/api/account?id=${this.id}`;
      this.http.get<User>(url).subscribe(
        (response) => {
          // Read response: error when null response, else update our user with the response
          if (response == null) {
            this.alertService.error("This user does not exist")
          } else {
            this.setUser(response);

            // Check if this user has a advertisement, set flag accordingly (it changes the visible buttons)
            if (this.user.adActive) {
              this.hasAd = true;
            } else {
              this.hasAd = false;
            };
          }
        },
        (error) => {
          console.error('Error fetching user:', error);
          this.alertService.error("Your user was not found");
          this.isLoading = false;
        }
      );
    }
  }

  showCreateNewAdForm(id: number) {
    this.router.navigate(['/user-advertisement'], { queryParams: { id: id } });
  }

  submitForm() {
    if (this.number) {
      this.id = this.number;
    }
    this.getUser();
  }

  showID() {
    console.log(this.id);
  }

  deleteAd(id: number) {
    this.alertService.clear();
    this.http.delete(`/api/advertisements/${id}`).subscribe(
      () => {
        this.alertService.success("Advertisement has been deleted successfully!")
        console.log('Advertisement deleted successfully');
      },
      (error) => {
        this.alertService.error("Something went wrong, please try again.")
        console.error('Error deleting advertisement:', error);
      }
    );
  }
  editAd(id: number) {
    this.alertService.clear();
    this.router.navigate(['/user-advertisement'], { queryParams: { id: id } });
  }
}
