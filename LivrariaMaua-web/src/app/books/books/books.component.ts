import { ConfirmationDialogComponent } from './../../shared/components/confirmation-dialog/confirmation-dialog.component';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

import { ErrorDialogComponent } from '../../shared/components/error-dialog/error-dialog.component';
import { Books } from '../model/books';
import { BooksService } from '../services/books.service';
import { BooksPage } from '../model/books-page';
import { MatPaginator, PageEvent, MatPaginatorModule } from '@angular/material/paginator';
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
  imports: [MatCardModule, MatToolbarModule, BooksListComponent, MatPaginatorModule, MatProgressSpinnerModule, AsyncPipe]
})

export class BooksComponent implements OnInit {
  books$: Observable<BooksPage> | null = null;

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  pageIndex = 0;
  pageSize = 10;

  constructor(
    private booksService: BooksService,
    public dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar
  ) {
    this.refresh();
  }

  refresh(pageEvent: PageEvent = { length: 0, pageIndex: 0, pageSize: 10 }) {
    this.books$ = this.booksService.list(pageEvent.pageIndex, pageEvent.pageSize)
      .pipe(
        tap(() => {
          this.pageIndex = pageEvent.pageIndex;
          this.pageSize = pageEvent.pageSize;
        }),
        catchError(error => {
          this.onError('Erro ao carregar livros.');
          return of({ books: [], totalElements: 0, totalPages: 0 })
        })
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
    this.router.navigate(['edit', books._id], { relativeTo: this.route });
  }

  onRemove(books: Books) {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: 'Tem certeza que deseja remover esse livro?',
    });

    dialogRef.afterClosed().subscribe((result: boolean) => {
      if (result) {
        this.booksService.remove(books._id).subscribe(
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