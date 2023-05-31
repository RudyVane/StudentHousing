/*
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PropertyService } from '../services/property.service';
import { Property } from '../models/property';
import { switchMap } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-property-details',
  templateUrl: './property-details.component.html',
  styleUrls: ['./property-details.component.css']
})
export class PropertyDetailsComponent implements OnInit {
  property$!: Observable<Property>;

  constructor(private route: ActivatedRoute, private propertyService: PropertyService) {}

  ngOnInit() {
    this.property$ = this.route.paramMap.pipe(
      switchMap(params => {
        const propertyId = params.get('id');
        return this.propertyService.getPropertyById(propertyId);
      })
    );
  }
}
*/
