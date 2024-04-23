import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormGroup, NonNullableFormBuilder, UntypedFormArray, Validators, ReactiveFormsModule } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';

import { Books } from '../model/books';
import { BooksService } from '../services/books.service';
import { FormUtilsService } from './../../shared/form/form-utils.service';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatOptionModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatCardModule } from '@angular/material/card';

@Component({
    selector: 'app-books-form',
    templateUrl: './books-form.component.html',
    styleUrls: ['./books-form.component.scss'],
    standalone: true,
    imports: [MatCardModule, MatToolbarModule, ReactiveFormsModule, MatFormFieldModule, MatInputModule, MatSelectModule, MatOptionModule, MatButtonModule, MatIconModule]
})

export class BooksFormComponent implements OnInit {

    form!: FormGroup;
    book: Books | undefined;

    constructor(private formBuilder: NonNullableFormBuilder,
      private service: BooksService,
      private snackBar: MatSnackBar,
      private location: Location,
      private route: ActivatedRoute,
      public formUtils: FormUtilsService) {
    }

  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    if (id) {
      this.service.loadById(id).subscribe(
        (book: Books) => {
          this.book = book;
          this.initForm(book);
        },
        error => {
          console.error('Error ao carregar informações do livro.', error);
          this.snackBar.open('Error ao carregar informações do livro.', '', { duration: 5000 });
          this.goBack();
        }
      );
    } else {
      this.initForm(null);
    }
  }

  initForm(book: Books | null): void {
    if (book) {
      this.form! = this.formBuilder.group({
        id: [book.id],
        author: [book.author, [Validators.required]],
        gender: [book.gender, [Validators.required]],
        title: [book.title, [Validators.required]],
        quantity: [book.quantity, [Validators.required, Validators.min(1)]]
      });
    } else {
      this.form! = this.formBuilder.group({
        id: [null],
        author: ['', [Validators.required]],
        gender: ['', [Validators.required]],
        title: ['', [Validators.required]],
        quantity: [null, [Validators.required, Validators.min(1)]]
      });
    }
  }

  onSubmit() {
    if (this.form!.valid) {
      this.service.save(this.form!.value)
        .subscribe(result => this.onSuccess(), error => this.onError());
    } else {
      this.formUtils.validateAllFormFields(this.form!);
    }
  }

  onEdit() {
    if (this.form!.valid) {
      this.service.update(this.form!.value)
        .subscribe(result => this.onSuccess(), error => this.onError());
    } else {
      this.formUtils.validateAllFormFields(this.form!);
    }
  }

  onCancel() {
    this.location.back();
  }

  goBack(): void {
    this.location.back();
  }

  private onSuccess() {
    this.snackBar.open('Livro salvo com sucesso!', '', { duration: 5000 });
    this.onCancel();
  }

  private onError() {
    this.snackBar.open('Erro ao salvar livro.', '', { duration: 5000 });
  }
}