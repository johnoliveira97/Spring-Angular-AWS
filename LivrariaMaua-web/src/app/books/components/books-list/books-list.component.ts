import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

import { Books } from '../../model/books';
import { CategoryPipe } from '../../../shared/pipes/category.pipe';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';

@Component({
    selector: 'app-books-list',
    templateUrl: './books-list.component.html',
    styleUrls: ['./books-list.component.scss'],
    standalone: true,
    imports: [MatTableModule, MatIconModule, MatButtonModule, CategoryPipe]
})

export class BooksListComponent implements OnInit {

  @Input() books: Books[] = [];
  @Output() add = new EventEmitter(false);
  @Output() edit = new EventEmitter(false);
  @Output() remove = new EventEmitter(false);

  readonly displayedColumns = ['title', 'author', 'gender', 'quantity'];

  constructor() { }

  ngOnInit(): void { }

  onAdd() {
    this.add.emit(true);
  }

  onEdit(books: Books) {
    this.edit.emit(books);
  }

  onDelete(books: Books) {
    this.remove.emit(books);
  }

}