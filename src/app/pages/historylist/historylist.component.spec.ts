import { fakeAsync, ComponentFixture, TestBed } from '@angular/core/testing';
import { HistorylistComponent } from './historylist.component';

describe('HistorylComponent', () => {
  let component: HistorylistComponent;
  let fixture: ComponentFixture<HistorylistComponent>;

  beforeEach(fakeAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ HistorylistComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HistorylistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should compile', () => {
    expect(component).toBeTruthy();
  });
});
