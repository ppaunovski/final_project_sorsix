import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ReviewAveragesComponent} from './review-averages.component';

describe('ReviewAveragesComponent', () => {
  let component: ReviewAveragesComponent;
  let fixture: ComponentFixture<ReviewAveragesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReviewAveragesComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(ReviewAveragesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
