import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class SearchServiceService {
  constructor(private http: HttpClient) {}

  getSearchedData(data: any) {
    return this.http.get(`http://localhost:8087/books/search/${data}`);
  }
}
