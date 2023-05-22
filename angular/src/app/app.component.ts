import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  properties: any[] = [];
  logoUrl: string = 'assets/logoSH.png';
  constructor(private router: Router, private http: HttpClient) {}

  retrieveProperties(): void {
    this.http.get<any[]>('http://localhost:8080/api/properties')
      .subscribe(data => {
        this.properties = data;
      });
  }

  showFormComponent() {
    this.router.navigate(['/form']);
  }

  showRegisterComponent() {
    this.router.navigate(['/register']);
  }
  showListComponent() {
    this.router.navigate(['/property-list']);
  }
  showUserAddComponent(){
    this.router.navigate(['/user-advertisement']);
  }
}
