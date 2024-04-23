import { ConfirmationDialogComponent } from './../../shared/components/confirmation-dialog/confirmation-dialog.component';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, of, tap } from 'rxjs';

import { ErrorDialogComponent } from '../../shared/components/error-dialog/error-dialog.component';
import { Books } from '../model/books';
import { BooksService } from '../services/books.service';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { BooksListComponent } from '../components/books-list/books-list.component';
import { AsyncPipe } from '@angular/common';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.scss'],
  standalone: true,
  imports: [MatCardModule, MatToolbarModule, BooksListComponent, MatProgressSpinnerModule, AsyncPipe]
})

export class BooksComponent implements OnInit {
  books$: Observable<Books[]> | null = null;

  pageIndex = 0;
  pageSize = 10;
  totalElements = 0;
  bookList: Books[] = [];

  constructor(
    private booksService: BooksService,
    public dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar
  ) {
    this.refresh();
  }

  refresh() {
    this.booksService.list().pipe(
      tap((response: any) => {
        const booksArray = response;
        if (Array.isArray(booksArray) && booksArray.length > 0) {
          this.books$ = of(booksArray);
          this.bookList = booksArray.filter(book => 
            book.hasOwnProperty('title') && book.hasOwnProperty('author') && 
            book.hasOwnProperty('gender') && book.hasOwnProperty('quantity')
          );
        }
      })
    ).subscribe(
      () => {},
      (error: any) => {
        console.error('Erro ao obter os livros: ', error);
      }
    );
  }

  onError(errorMsg: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: errorMsg
    });
  }

  ngOnInit(): void { }

  onAdd() {
    this.router.navigate(['new'], { relativeTo: this.route });
  }

  onEdit(books: Books) {
    this.router.navigate(['edit', books.id], { relativeTo: this.route });
  }

  onRemove(books: Books) {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: 'Tem certeza que deseja remover esse livro?',
    });

    dialogRef.afterClosed().subscribe((result: boolean) => {
      if (result) {
        this.booksService.remove(books.id).subscribe(
          () => {
            this.refresh();
            this.snackBar.open('Livro removido com sucesso!', 'X', {
              duration: 5000,
              verticalPosition: 'top',
              horizontalPosition: 'center'
            });
          },
          () => this.onError('Erro ao tentar remover livro.')
        );
      }
    });
  }
}