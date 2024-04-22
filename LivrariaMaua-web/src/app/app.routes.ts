import { Routes } from '@angular/router';

export const APP_ROUTES: Routes = [
    { path: '', pathMatch: 'full', redirectTo: 'books' },
    {
        path: 'books',
        loadChildren: () => import('./books/books-routing.module').then(m => m.BOOKS_ROUTES)
    }
];
export { Routes };

