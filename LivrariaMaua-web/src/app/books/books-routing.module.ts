import { Routes } from '@angular/router';
import { BooksComponent } from './books/books.component';
import { BooksFormComponent } from './books-form/books-form.component';

export const BOOKS_ROUTES: Routes = [
  { path: '', component: BooksComponent },
  { path: 'new', component: BooksFormComponent },
  { path: 'edit/:id', component: BooksFormComponent }
];
