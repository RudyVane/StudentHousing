import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FindAdvertisementComponent } from './find-advertisement.component';

describe('FindAdvertisementComponent', () => {
  let component: FindAdvertisementComponent;
  let fixture: ComponentFixture<FindAdvertisementComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FindAdvertisementComponent]
    });
    fixture = TestBed.createComponent(FindAdvertisementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
