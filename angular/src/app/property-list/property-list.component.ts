import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';

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
    empty: false
  };
  fallbackImageUrl = 'assets/No-image-available.jpg';
  selectedProperty: Property | null = null;
  showContactForm: boolean = false; // Define the showContactForm property

  constructor(
    private http: HttpClient,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.getProperties();
  }

  getProperties(): void {
    this.http.get<Page<Property>>('http://localhost:8080/property')
      .subscribe(
        (data: Page<Property>) => {
          this.properties = data;
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
    // Add code to show the contact form
    // For example, you can set a flag to toggle the visibility of the contact form
    this.showContactForm = true;
  }
}
