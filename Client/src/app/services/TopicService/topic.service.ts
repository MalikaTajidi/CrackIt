import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TopicService {
  private apiUrl = 'http://localhost:8080/topics';

  constructor(private http: HttpClient) { }
  
  getAllTopics(): Observable<any> {
    return this.http.get<any>(this.apiUrl);
  }
}
