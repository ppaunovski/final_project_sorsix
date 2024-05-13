import { TestBed } from '@angular/core/testing';

import { ImageToUrlService } from './image-to-url.service';

describe('ImageToUrlService', () => {
  let service: ImageToUrlService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ImageToUrlService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
