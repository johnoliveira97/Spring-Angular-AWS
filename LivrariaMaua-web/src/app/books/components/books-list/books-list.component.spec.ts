import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BooksListComponent } from './books-list.component';

describe('BooksListComponent', () => {
  let component: BooksListComponent;
  let fixture: ComponentFixture<BooksListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
    imports: [BooksListComponent]
})
    .compileComponents();

    fixture = TestBed.createComponent(BooksListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should emit add event', () => {
    spyOn(component.add, 'emit');
    component.onAdd();
    expect(component.add.emit).toHaveBeenCalledWith(true);
  });

  it('should emit edit event', () => {
    spyOn(component.edit, 'emit');
    const mockBook: any = {};
    component.onEdit(mockBook);
    expect(component.edit.emit).toHaveBeenCalledWith(mockBook);
  });

  it('should emit remove event', () => {
    spyOn(component.remove, 'emit');
    const mockBook: any = {};
    component.onDelete(mockBook);
    expect(component.remove.emit).toHaveBeenCalledWith(mockBook);
  });
});