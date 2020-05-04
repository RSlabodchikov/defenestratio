import {Injectable} from '@angular/core';
import {User} from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class StorageService {
  private USER_KEY = 'currentUser';
  private TOKEN_KEY = 'currentToken';

  constructor() {
  }

  set currentUser(user: User) {
    localStorage.setItem(this.USER_KEY, JSON.stringify(user));
  }

  get currentUser(): User {
    if (localStorage.getItem(this.USER_KEY).match(null)) {
      return null;
    }
    return JSON.parse(localStorage.getItem(this.USER_KEY));
  }

  set currentToken(token: string) {
    localStorage.setItem(this.TOKEN_KEY, JSON.stringify(token));
  }

  get currentToken(): string {
    if (localStorage.getItem(this.TOKEN_KEY).match(null)) {
      return null;
    }
    return JSON.parse(localStorage.getItem(this.TOKEN_KEY));
  }
}
