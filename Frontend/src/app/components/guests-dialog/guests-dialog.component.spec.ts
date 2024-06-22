import {ComponentFixture, TestBed} from '@angular/core/testing';

import {GuestsDialogComponent} from './guests-dialog.component';

describe('GuestsDialogComponent', () => {
  let component: GuestsDialogComponent;
  let fixture: ComponentFixture<GuestsDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GuestsDialogComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(GuestsDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
