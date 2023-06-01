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
  apiUrl = 'http://localhost:8080/properties';
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
  selectedCity: string = 'All Cities';
  maxRent: number = 10000;
  minRent: number = 0;
  selectedGender: string = '';
  id: number = 2;
  distinctCities: string[] = [];
  showFilterButtons: boolean = false;
  topN: number = 10;
  currentPage = 1;
  sortBy: String = "rent";
  sortDirection: String = "asc";
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

  nextPage(): void {
    if (this.properties.last) {
      // If it's the last page, do nothing
      return;
    }



    // Increment the current page number
    this.currentPage++;


    this.getProperties().subscribe(
      (data: Page<Property>) => {
        console.log(data); // Log the API response
        this.properties = data;
        this.properties.content = this.properties.content.slice(0, 1000);
      },
      (error) => {
        console.error('Error retrieving properties:', error);
      }
    );
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

    // Reset the filter values
    this.selectedCity = '';
    this.minRent = 0;
    this.maxRent = 10000;
    this.selectedGender = '';
    this.currentPage = 1;
    this.sortBy = 'rent';
    this.sortDirection = 'asc';

    // Make the API call with the updated URL and query parameters
    this.getProperties().subscribe(
      (data: Page<Property>) => {
        console.log(data); // Log the API response
        this.properties = data;
      },
      (error) => {
        console.error('Error retrieving properties:', error);
      }
    );
  }


  applyFilters(): void {
    if (this.selectedCity === 'All Cities') {
      // Retrieve all properties without filtering by city
      this.getProperties().subscribe(
        (data: Page<Property>) => {
          console.log(data); // Log the API response
          this.properties = data;
          // Set the flag to show the filter buttons only after the "Apply Filters" button is clicked
          this.showFilterButtons = true;
          // Display only the top N properties
          this.properties.content = this.properties.content.slice(0, this.topN);
        },
        (error) => {
          console.error('Error retrieving properties:', error);
        }
      );
    } else {
      // Apply the regular filtering logic
      this.currentPage = 0;

      this.getProperties().pipe(
        map((data: Page<Property>) => {
          // Filter the properties based on the selected criteria
          return data.content.filter(property => {
            let meetsCriteria = true;

            if (property.city !== this.selectedCity) {
              meetsCriteria = false;
            }
            if (this.minRent && property.rent < this.minRent) {
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
        }),
        tap((filteredProperties: Property[]) => {
          console.log(filteredProperties); // Log the filtered properties

          // Set the filtered properties to the component's properties
          this.properties.content = filteredProperties;
          // Set the flag to show the filter buttons only after the "Apply Filters" button is clicked
          this.showFilterButtons = true;
          // Display only the top N properties
          this.properties.content = this.properties.content.slice(0, this.topN);
        }),
        catchError((error) => {
          console.error('Error retrieving properties:', error);
          return [];
        })
      ).subscribe();
    }
  }


  filterRentUp(): void {
    this.sortBy = "rent";
    this.sortDirection = "asc";
    this.getProperties().pipe(
      map((data: Page<Property>) => {
        // Get all properties within the rent range
        this.properties.content = data.content;

        // Set the flag to show the filter buttons only after the "Apply Filters" button is clicked
        this.showFilterButtons = true;
      }),
      catchError((error) => {
        console.error('Error retrieving properties:', error);
        return [];
      })
    ).subscribe();
  }


  filterRentDown(): void {

  this.sortBy = "rent";
  this.sortDirection = "desc";

    this.getProperties().pipe(

      map((data: Page<Property>) => {
        // Get all properties within the rent range
        this.properties.content = data.content;

        // Set the flag to show the filter buttons only after the "Apply Filters" button is clicked
        this.showFilterButtons = true;
      }),
      catchError((error) => {
        console.error('Error retrieving properties:', error);
        return [];
      })
    ).subscribe();
  }

  filterSqmUp(): void {
    this.sortBy = "rentSqm";
    this.sortDirection = "asc";

    this.getProperties().pipe(

      map((data: Page<Property>) => {
        // Get all properties within the rent range
        this.properties.content = data.content;

        // Set the flag to show the filter buttons only after the "Apply Filters" button is clicked
        this.showFilterButtons = true;
      }),
      catchError((error) => {
        console.error('Error retrieving properties:', error);
        return [];
      })
    ).subscribe();
  }
  filterSqmDown(): void {
    this.sortBy = "rentSqm";
    this.sortDirection = "desc";


    this.getProperties().pipe(

      map((data: Page<Property>) => {
        // Get all properties within the rent range
        this.properties.content = data.content;

        // Set the flag to show the filter buttons only after the "Apply Filters" button is clicked
        this.showFilterButtons = true;
      }),
      catchError((error) => {
        console.error('Error retrieving properties:', error);
        return [];
      })
    ).subscribe();
  }
  private getProperties(): Observable<Page<Property>> {
    let params: any = {
      sortBy: this.sortBy,
      sortDirection: this.sortDirection,
      page: this.currentPage,
      gender: this.selectedGender,
      minRent: this.minRent,
      maxRent: this.maxRent
    };

    if (this.selectedCity && this.selectedCity !== 'All Cities') {
      params.city = this.selectedCity;
    }

    return this.http.get<Page<Property>>(this.apiUrl, { params }).pipe(
      tap((data: Page<Property>) => {
        console.log(data); // Log the API response
        this.properties = data;
      }),
      catchError((error) => {
        console.error('Error retrieving properties:', error);
        return of({
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
        });
      })
    );
  }
}
