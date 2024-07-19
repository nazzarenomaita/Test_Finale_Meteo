import { TestBed } from '@angular/core/testing';

import { NominatinService } from './nominatin.service';

describe('NominatinService', () => {
  let service: NominatinService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NominatinService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
