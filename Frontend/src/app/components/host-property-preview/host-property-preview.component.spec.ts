import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HostPropertyPreviewComponent } from './host-property-preview.component';

describe('HostPropertyPreviewComponent', () => {
  let component: HostPropertyPreviewComponent;
  let fixture: ComponentFixture<HostPropertyPreviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HostPropertyPreviewComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(HostPropertyPreviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
