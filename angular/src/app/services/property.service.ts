import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable, switchMap} from 'rxjs';
import { Property } from '../models/property';
import { Page} from '../property-list/property-list.component';
import { Pageable } from '../models/pageable';

@Injectable({
  providedIn: 'root'
})
export class PropertyService {
  private apiUrl = 'http://localhost:8080/properties';
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  createProperty(property: Property): Observable<Property> {
    return this.http.post<Property>(`${this.apiUrl}/newProperty`, property);
  }
  findAllDistinctCities(): Observable<{ cities: string[] }> {
    return this.http.get<{ cities: string[] }>('http://localhost:8080/properties/distinct-cities');
  }

  getProperties(page: number, pageSize: number): Observable<Page<Property>> {
    const offset = (page - 1) * pageSize;
    return this.http.get<Page<Property>>(`${this.apiUrl}/properties?page=${page}&size=${pageSize}&offset=${offset}`);
  }
  getPropertyById(propertyId: string | null): Observable<Property> {
    return this.http.get<Property>(`${this.apiUrl}/getProperty/${propertyId}`);
  }
}
