import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GuestTypeSelectComponent } from './guest-type-select.component';

describe('GuestTypeSelectComponent', () => {
  let component: GuestTypeSelectComponent;
  let fixture: ComponentFixture<GuestTypeSelectComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GuestTypeSelectComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GuestTypeSelectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
