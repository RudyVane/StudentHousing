import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-property-list',
  templateUrl: './property-list.component.html',
  styleUrls: ['./property-list.component.css']
})
export class PropertyListComponent implements OnInit {
  properties: any[] = [];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.getProperties();
  }

  getProperties(): void {
    this.http.get<any[]>('http://localhost:8080/api/properties')
      .subscribe(data => {
        this.properties = data;
      });
  }

}
