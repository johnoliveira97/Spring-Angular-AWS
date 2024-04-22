import { Books } from "./books";

export interface BooksPage {
  books: Books[];
  totalElements: number;
  totalPages: number;
}