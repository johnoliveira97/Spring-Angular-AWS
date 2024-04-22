import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Books } from '../model/books';
import { delay, first, map, tap } from 'rxjs/operators';
import { BooksPage } from '../model/books-page';

@Injectable({
  providedIn: 'root'
})
export class BooksService {

  private readonly API = 'http://localhost:5000/books';

  constructor(private httpClient: HttpClient) { }

  list(page = 0, pageSize = 10) {
    return this.httpClient.get<BooksPage>(this.API)
      .pipe(
        first(),
      );
  }

  loadById(id: string) {
    return this.httpClient.get<Books>(`${this.API}/${id}`);
  }

  save(record: Partial<Books>) {
    if (record._id) {
      return this.update(record);
    }
    return this.create(record);
  }

  private create(record: Partial<Books>) {
    return this.httpClient.post<Books>(this.API, record).pipe(first());
  }

  private update(record: Partial<Books>) {
    return this.httpClient.put<Books>(`${this.API}/${record._id}`, record).pipe(first());
  }

  remove(id: string) {
    return this.httpClient.delete(`${this.API}/${id}`).pipe(first());
  }
}