/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { PurchasehistoryService } from './purchasehistory.service';

describe('Service: Purchasehistory', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PurchasehistoryService]
    });
  });

  it('should ...', inject([PurchasehistoryService], (service: PurchasehistoryService) => {
    expect(service).toBeTruthy();
  }));
});
