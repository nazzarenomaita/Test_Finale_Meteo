import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { StorageService } from './storage.service';
import { Observable, throwError } from 'rxjs';
import { Utente } from '../model/Utente';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor(private http: HttpClient, private storageService: StorageService) { }

  url = "http://localhost:8080/api/";

  getProfilo(email: string): Observable<Utente> | null {
    try {
      const token = this.storageService.getLocalToken()
      let headers = new HttpHeaders();
      headers = headers.set('Authorization', 'Bearer ' + token)
      return this.http.get<Utente>(this.url + "users/" + email, { headers });
    } catch (error) {
      return null;
    }
  }

  private handleError(error: HttpErrorResponse) {
    return throwError(() => new Error('Something bad happened; please try again later.'));
  }
}