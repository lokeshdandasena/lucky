import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class DashboardService {
  constructor(private http: HttpClient) {}
   booktitle:any;
  getAllBooks() {
    return this.http.get('http://localhost:8080/api/v1/book/books');
    
  }
  
}
