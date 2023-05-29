import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { PropertyService } from '../services/property.service';
import {Observable, of, switchMap, tap} from "rxjs";
import { Property } from '../models/property'; // Update the path if necessary
import { catchError, map } from 'rxjs/operators';

export interface Page<T> {
  content: T[];
  totalPages: number;
  totalElements: number;
  number: number;
  size: number;
  first: boolean;
  last: boolean;
  numberOfElements: number;
  empty: boolean;
  properties: Property[];
}


@Component({
  selector: 'app-property-list',
  templateUrl: './property-list.component.html',
  styleUrls: ['./property-list.component.css']
})
export class PropertyListComponent implements OnInit {
  apiUrl = 'http://localhost:8080/property';
  properties: Page<Property> = {
    content: [],
    totalPages: 0,
    totalElements: 0,
    number: 0,
    size: 0,
    first: false,
    last: false,
    numberOfElements: 0,
    empty: false,
    properties: []
  };

  fallbackImageUrl = 'assets/No-image-available.jpg';
  selectedProperty: Property | null = null;
  showContactForm: boolean = false; // Define the showContactForm property
  selectedCity: string = '';
  maxRent: number = 1500;
  selectedGender: string = '';
  id: number = 2;
  distinctCities: string[] = [];
  showFilterButtons: boolean = false;

  currentPage = 1;
   constructor(
    private http: HttpClient,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private propertyService: PropertyService

) {
  }

  ngOnInit(): void {
    this.applyInit();
    this.getDistinctCities();
    this.selectedCity = this.distinctCities.length > 0 ? '' : 'All Cities';
  }

  onCitySelect(event: any) {
    this.selectedCity = event.target.value;
  }

  handleImageError(event: any): void {
    event.target.src = this.fallbackImageUrl;
  }

  showDetails(property: Property): void {
    this.selectedProperty = property;
  }

  showContact(property: Property): void {
    this.selectedProperty = property;
    this.showContactForm = true;
  }

  getDistinctCities(): void {
    this.propertyService.findAllDistinctCities().subscribe(
      (data: { cities: string[] }) => {
        this.distinctCities = data.toString().split(","); //data.split(",");
        if (this.distinctCities.length > 0) {
          this.selectedCity = "All Cities";
        }
      },
      error => {
        console.error('Error retrieving cities:', error);
      }
    );

  }
  applyInit(): void {
    console.log('Apply Filters clicked');
    const apiUrl = 'http://localhost:8080/property';
    const params: any = {};

    if (this.selectedCity && this.selectedCity !== 'All Cities') {
      params.city = this.selectedCity;
    }
    // Make the API call with the updated URL and query parameters
    this.http.get<Page<Property>>(apiUrl, { params }).subscribe(
      (data: Page<Property>) => {
        console.log(data); // Log the API response
        this.properties = data;
        this.properties.content = this.properties.content.slice(0, 100);
      },
      (error) => {
        console.error('Error retrieving properties:', error);
      }
    );
  }

  applyFilters(): void {
    console.log('Apply Filters clicked');
    const apiUrl = 'http://localhost:8080/property';
    const params: any = {};

    if (this.selectedCity && this.selectedCity !== 'All Cities') {
      params.city = this.selectedCity;
    }

    if (this.maxRent) {
      params.maxRent = this.maxRent;
    }

    if (this.selectedGender) {
      params.gender = this.selectedGender;
    }

    // Make the API call with the updated URL and query parameters
    this.http.get<Page<Property>>(apiUrl, { params }).subscribe(
      (data: Page<Property>) => {
        console.log(data); // Log the API response
        this.properties = data;

        // Filter the properties based on the selected criteria
        this.properties.content = this.properties.content.filter(property => {
          let meetsCriteria = true;

          if (this.selectedCity && this.selectedCity !== 'All Cities' && property.city !== this.selectedCity) {
            meetsCriteria = false;
          }

          if (this.maxRent && property.rent > this.maxRent) {
            meetsCriteria = false;
          }

          if (this.selectedGender && property.gender !== this.selectedGender) {
            meetsCriteria = false;
          }

          return meetsCriteria;
        });

        this.properties.content = this.properties.content.slice(0, 100);

        // Set the flag to show the filter buttons only after the "Apply Filters" button is clicked
        this.showFilterButtons = true;
      },
      (error) => {
        console.error('Error retrieving properties:', error);
      }
    );
  }


  filterRentDown(topN: number): void {
    if (this.selectedCity && this.selectedCity !== 'All Cities') {
      const apiUrl = 'http://localhost:8080/property';
      const params: any = {
        city: this.selectedCity
      };

      // Make the API call with the updated URL and query parameters
      this.http.get<Page<Property>>(apiUrl, { params }).pipe(
        map((data: Page<Property>) => {
          // Sort the properties based on rent in descending order
          data.content.sort((a, b) => b.rent - a.rent);
          return data;
        }),
        tap((data: Page<Property>) => {
          // Get the top N properties
          this.properties.content = data.content.slice(0, topN);
        }),
        catchError((error) => {
          console.error('Error retrieving properties:', error);
          return of(null);
        })
      ).subscribe();
    }
  }

  filterRentUp(topN: number): void {
    if (this.selectedCity && this.selectedCity !== 'All Cities') {
      const apiUrl = 'http://localhost:8080/property';
      const params: any = {
        city: this.selectedCity
      };

      // Make the API call with the updated URL and query parameters
      this.http.get<Page<Property>>(apiUrl, { params }).pipe(
        map((data: Page<Property>) => {
          // Sort the properties based on rent in ascending order
          data.content.sort((a, b) => a.rent - b.rent);
          return data;
        }),
        tap((data: Page<Property>) => {
          // Get the top N properties
          this.properties.content = data.content.slice(0, topN);
        }),
        catchError((error) => {
          console.error('Error retrieving properties:', error);
          return of(null);
        })
      ).subscribe();
    }
  }
  filterSqmUp(topN: number): void {
    if (this.selectedCity && this.selectedCity !== 'All Cities') {
      const apiUrl = 'http://localhost:8080/property';
      const params: any = {
        city: this.selectedCity
      };

      // Make the API call with the updated URL and query parameters
      this.http.get<Page<Property>>(apiUrl, { params }).pipe(
        map((data: Page<Property>) => {
          // Calculate rent per sqm for each property
          data.content.forEach(property => {
            property.rentPerSqm = property.rent / property.areaSqm;
          });

          // Sort the properties based on rent per sqm in ascending order
          data.content.sort((a, b) => a.rentPerSqm - b.rentPerSqm);

          return data;
        }),
        tap((data: Page<Property>) => {
          // Get the top N properties
          this.properties.content = data.content.slice(0, topN);
        }),
        catchError((error) => {
          console.error('Error retrieving properties:', error);
          return of(null);
        })
      ).subscribe();
    }
  }

  filterSqmDown(topN: number): void {
    if (this.selectedCity && this.selectedCity !== 'All Cities') {
      const apiUrl = 'http://localhost:8080/property';
      const params: any = {
        city: this.selectedCity
      };

      // Make the API call with the updated URL and query parameters
      this.http.get<Page<Property>>(apiUrl, { params }).pipe(
        map((data: Page<Property>) => {
          // Calculate rent per sqm for each property
          data.content.forEach(property => {
            property.rentPerSqm = property.rent / property.areaSqm;
          });

          // Sort the properties based on rent per sqm in descending order
          data.content.sort((a, b) => b.rentPerSqm - a.rentPerSqm);

          return data;
        }),
        tap((data: Page<Property>) => {
          // Get the top N properties
          this.properties.content = data.content.slice(0, topN);
        }),
        catchError((error) => {
          console.error('Error retrieving properties:', error);
          return of(null);
        })
      ).subscribe();
    }
  }

}
