import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class SharedService {
  message: string;

  constructor() {}
  setMessage(data) {
    this.message = data;
  }
  getMessage() {
    console.log(this.message);
    return this.message;
  }
}
