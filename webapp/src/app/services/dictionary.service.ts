import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DictionaryService {

  constructor(private httpclient: HttpClient) { }

  getmeaning(word:any){
    return this.httpclient.get(
      `http://localhost:9090/api/v1/dictionary/meaning/${word}`,{ responseType: 'json' }
    )
  }
}
