import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ReserveComponentComponent} from './reserve-component.component';

describe('ReserveComponentComponent', () => {
  let component: ReserveComponentComponent;
  let fixture: ComponentFixture<ReserveComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReserveComponentComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(ReserveComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
