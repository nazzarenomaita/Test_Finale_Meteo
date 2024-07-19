import { TestBed } from '@angular/core/testing';

import { MeteoserviceService } from './meteoservice.service';

describe('MeteoserviceService', () => {
  let service: MeteoserviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MeteoserviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
