import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class BookPathServicesService {
  constructor(private httpclient: HttpClient) {}

  getBookPath() {
    return this.httpclient.get(
      'http://localhost:8080/api/v1/book/bookpath/The Sign of the Four',
      { responseType: 'text' }
    );
  }
  updateNotes(userEmail: any, previousRead) {
    console.log(previousRead);
    return this.httpclient.put(
      `http://localhost:8082/api/v1/uploadnotes/${userEmail}`,
      previousRead
    );
  }
}
