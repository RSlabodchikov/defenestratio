import {HttpClient, HttpResponse} from "@angular/common/http";
import {StorageService} from "./storage.service";
import {Injectable} from "@angular/core";
import {User} from "../models/user";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  LOGIN_URL = '/api/login';
  REGISTRATION_URL = '/api/register';
  DISABLE_USER_BY_ID_URL = '/api/users/';
  UNLOCK_USER_BY_ID_URL = '/api/users?';
  GET_USER_BY_ID_URL = '/api/users/';

  constructor(private httpClient: HttpClient, private storageService: StorageService) {
  }

  logout() {
    this.storageService.currentUser = null;
    this.storageService.currentToken = null;
  }

  isAuthenticated(): boolean {
    return this.storageService.currentToken != null;
  }

  login(user: User): Observable<HttpResponse<User>> {
    return this.httpClient.post<any>(this.LOGIN_URL, user, {observe: 'response', responseType: 'json'})
  }

  register(user: User): Observable<HttpResponse<User>> {
    return this.httpClient.post<any>(this.REGISTRATION_URL, user, {observe: 'response', responseType: 'json'})
  }

  disableUser(userId: string) {
    return this.httpClient.delete(this.DISABLE_USER_BY_ID_URL + userId);
  }

  unlockUser(userId: string, user: User) {
    return this.httpClient.post(this.UNLOCK_USER_BY_ID_URL + userId, user);
  }

  getUser(userId: string): Observable<any> {
    return this.httpClient.get(this.GET_USER_BY_ID_URL + userId);
  }
}
