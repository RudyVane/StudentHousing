import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Property } from '../models/property';

@Injectable({
  providedIn: 'root'
})
export class PropertyService {
  private apiUrl = 'http://localhost:8080/property'; // Replace with your API endpoint URL

  constructor(private http: HttpClient) {}

  createProperty(property: Property): Observable<Property> {
    return this.http.post<Property>(`${this.apiUrl}/newProperty`, property);
  }

  getProperties(): Observable<Property[]> {
    return this.http.get<Property[]>(`${this.apiUrl}/getProperties`);
  }

  getPropertyById(propertyId: string | null): Observable<Property> {
    return this.http.get<Property>(`${this.apiUrl}/getProperty/${propertyId}`);
  }
}
