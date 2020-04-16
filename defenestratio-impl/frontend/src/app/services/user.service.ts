import {HttpClient, HttpResponse} from "@angular/common/http";
import {StorageService} from "./storage.service";
import {Injectable} from "@angular/core";
import {User} from "../models/user";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  loginUrl = '/login';

  constructor(private httpClient: HttpClient, private storageService: StorageService) {
  }

  logout() {
    this.storageService.currentUser = null;
    this.storageService.currentToken = null;
  }

  isAuthenticated(): boolean {
    const token = this.storageService.currentToken;
    return token != null;
  }

  login(user: User): Observable<HttpResponse<User>> {
    return this.httpClient.post<any>(this.loginUrl, {observe: 'response', responseType: 'json'})
  }
}
