import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable, of } from 'rxjs';

import { Books } from '../model/books';
import { BooksService } from '../services/books.service';

@Injectable({
  providedIn: 'root'
})
export class BooksResolver  {

  constructor(private service: BooksService) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Books> {
    if (route.params && route.params['id']) {
      return this.service.loadById(route.params['id']);
    }
    return of({ _id: '', author: '', title: '', gender: '', quantity: ''});
  }
}