import {TestBed} from '@angular/core/testing';

import {PropertyAttributeService} from './property-attribute.service';

describe('PropertyAttributeService', () => {
  let service: PropertyAttributeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PropertyAttributeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
