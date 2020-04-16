import {Injectable} from '@angular/core';
import {ChallengeModel} from "../models/challenge.model";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ChallengeService {
  private GET_ALL_CHALLENGES_URI = "/api/challenges";
  private GET_ALL_CHALLENGES_FOR_USER_URI = "/api/challenges/user";
  private CREATE_USER_CHALLENGE_URI = "/api/users/";

  private challenges: ChallengeModel[] = [];

  constructor(private httpClient: HttpClient) {
  }

  getAllChallenges(): Observable<ChallengeModel[]> {
    return this.httpClient.get<ChallengeModel[]>(this.GET_ALL_CHALLENGES_URI)
  }

  getAllChallengesAvailableForUser(userId: string): Observable<ChallengeModel[]> {
    let body = new HttpParams();
    body = body.set('userId', userId);
    return this.httpClient.get<ChallengeModel[]>(this.GET_ALL_CHALLENGES_FOR_USER_URI, {params: body})
  }

  addUserChallenge(userId: string, challengeId: string) {
    let body = new HttpParams();
    body = body.set('challengeId', challengeId);
    this.httpClient.post(this.CREATE_USER_CHALLENGE_URI + userId + "/challenges", body).subscribe();
  }

}
