import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class RegisterService {
  constructor(private http: HttpClient) {}

  signup(user: any) {
    return this.http.post('http://localhost:8082/api/v1/user', user);
  }
}
