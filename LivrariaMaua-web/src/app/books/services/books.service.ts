import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Books } from '../model/books';
import { first } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BooksService {

  private readonly API = 'http://livrariamaua.sa-east-1.elasticbeanstalk.com/';

  constructor(private httpClient: HttpClient) { }

  list() {
    return this.httpClient.get<Books>(`${this.API}books`);
  }

  loadById(id: string) {
    return this.httpClient.get<Books>(`${this.API}books/index/${id}`);
  }

  save(record: Partial<Books>) {
    if (record.id) {
      return this.update(record);
    }
    return this.create(record);
  }

  private create(record: Partial<Books>) {
    return this.httpClient.post<Books>(`${this.API}books`, record).pipe(first());
  }

   update(record: Partial<Books>) {
    return this.httpClient.patch<Books>(`${this.API}books/${record.id}`, record).pipe(first());
  }

  remove(id: string) {
    return this.httpClient.delete(`${this.API}books/${id}`).pipe(first());
  }
}