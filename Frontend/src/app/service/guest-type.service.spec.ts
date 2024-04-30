import { TestBed } from '@angular/core/testing';

import { GuestTypeService } from './guest-type.service';

describe('GuestTypeService', () => {
  let service: GuestTypeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GuestTypeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
