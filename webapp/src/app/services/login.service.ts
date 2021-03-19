import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  constructor(private http: HttpClient) {}
  useremail:any;
  login(user: any) {
    return this.http.post('http://localhost:8086/api/v1/login/user', user);
  }
}
