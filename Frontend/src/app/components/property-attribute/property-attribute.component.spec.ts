import {ComponentFixture, TestBed} from '@angular/core/testing';

import {PropertyAttributeComponent} from './property-attribute.component';

describe('PropertyAttributeComponent', () => {
  let component: PropertyAttributeComponent;
  let fixture: ComponentFixture<PropertyAttributeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PropertyAttributeComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(PropertyAttributeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
