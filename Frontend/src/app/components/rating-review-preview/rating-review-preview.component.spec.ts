import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RatingReviewPreviewComponent } from './rating-review-preview.component';

describe('RatingReviewPreviewComponent', () => {
  let component: RatingReviewPreviewComponent;
  let fixture: ComponentFixture<RatingReviewPreviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RatingReviewPreviewComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RatingReviewPreviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
