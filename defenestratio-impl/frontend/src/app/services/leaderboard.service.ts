import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Profile} from "../models/profile";

@Injectable({
  providedIn: 'root'
})
export class LeaderboardService {

  private GET_USERS_LEADERBOARD_URI = "/api/users/leaders";

  constructor(private httpClient: HttpClient) {
  }

  getLeadersList(): Observable<Profile[]> {
    return this.httpClient.get<Profile[]>(this.GET_USERS_LEADERBOARD_URI);
  }
}
