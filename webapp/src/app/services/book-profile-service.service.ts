import { HttpClient } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class BookProfileServiceService {
  constructor(private httpClient: HttpClient) {}

  getbookdetails(bookTitle) {
    return this.httpClient.get(
      `http://localhost:8080/api/v1/book/bookdetails/${bookTitle}`
    );
  }

 
}
