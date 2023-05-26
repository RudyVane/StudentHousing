import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { PropertyService } from '../services/property.service';
import {Observable} from "rxjs";

interface Page<T> {
  // Define the structure of the Page interface here
  content: T[];
  totalPages: number;
  totalElements: number;
  number: number;
  size: number;
  first: boolean;
  last: boolean;
  numberOfElements: number;
  empty: boolean;
  properties?: Property[];

}

interface Property {
  // Define the structure of the Property interface here
  id: number;
  externalId: string;
  userId: number;
  areaSqm: number;
  city: string;
  coverImageUrl: string;
  furnish: string;
  latitude: string;
  longitude: string;
  postalCode: string;
  propertyType: string;
  rawAvailability: string;
  rent: number;
  rentDetail: string;
  title: string;
  additionalCosts: number;
  deposit: number;
  descriptionNonTranslated: string;
  descriptionTranslated: string;
  energyLabel: string;
  gender: string;
  internet: string;
  isRoomActive: string;
  kitchen: string;
  living: string;
  matchAge: string;
  matchCapacity: string;
  matchGender: string;
  matchLanguages: string;
  matchStatus: string;
  pageDescription: string;
  pageTitle: string;
  pets: string;
  registrationCost: number;
  roommates: string;
  shower: string;
  smokingInside: string;
  toilet: string;
}

@Component({
  selector: 'app-property-list',
  templateUrl: './property-list.component.html',
  styleUrls: ['./property-list.component.css']
})
export class PropertyListComponent implements OnInit {
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
  maxRent: number | null = null;
  selectedGender: string = '';

  distinctCities: string[] = [];
  constructor(
    private http: HttpClient,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private propertyService: PropertyService

) {
  }

  ngOnInit(): void {
    this.getProperties();
    this.getDistinctCities();
  }

  onCitySelect(event: any) {
    this.selectedCity = event.target.value;
  }
  getProperties(): void {
    this.http.get<Page<Property>>('http://localhost:8080/property')
      .subscribe(
        (data: Page<Property>) => {
          this.properties = data;
          this.getDistinctCities();

        },
        error => {
          console.error('Error retrieving properties:', error);
        }
      );
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
          this.selectedCity = this.distinctCities[0]; // Select the first city by default
        }
      },
      error => {
        console.error('Error retrieving cities:', error);
      }
    );

  }






  applyFilters(): void {
    console.log('Apply Filters clicked');
    const apiUrl = 'http://localhost:8080/property';
    const params: any = {};

    if (this.selectedCity) {
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

        // Show only the first 20 properties
        this.properties.content = this.properties.content.slice(0, 20);
      },
      (error) => {
        console.error('Error retrieving properties:', error);
      }
    );
  }



}
