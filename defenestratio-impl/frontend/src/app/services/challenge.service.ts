import {Injectable} from '@angular/core';
import {ChallengeModel} from "../models/challenge.model";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserChallenge} from "../models/user.challenge";

@Injectable({
  providedIn: 'root'
})
export class ChallengeService {
  private GET_ALL_CHALLENGES_URI = "/api/challenges";
  private GET_ALL_CHALLENGES_FOR_USER_URI = "/api/challenges/user";
  private USER_CHALLENGES_URI = "/api/users/";
  private UPDATE_CHALLENGE_URL = "/api/challenges/update?";
  private CREATE_CHALLENGE_URI = "/api/challenges?";
  private DELETE_CHALLENGE_URI = "/api/challenges/";

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

  createChallenge(challenge: ChallengeModel): Observable<ChallengeModel> {
    return this.httpClient.post<ChallengeModel>(this.CREATE_CHALLENGE_URI, challenge);
  }

  deleteChallenge(challengeId: string) {
    return this.httpClient.delete(this.DELETE_CHALLENGE_URI + challengeId);
  }

  updateChallenge(challenge: ChallengeModel): Observable<any> {
    return this.httpClient.post(this.UPDATE_CHALLENGE_URL, challenge);
  }

  addUserChallenge(userId: string, challengeId: string): Observable<any> {
    let body = new HttpParams();
    body = body.set('challengeId', challengeId);
    return this.httpClient.post(this.USER_CHALLENGES_URI + userId + "/challenges", body);
  }

  getAllUserChallenges(userId: string): Observable<UserChallenge[]> {
    return this.httpClient.get<UserChallenge[]>(this.USER_CHALLENGES_URI + userId + "/challenges");
  }

  removeUserChallenge(userId: string, challengeId: string): Observable<any> {
    return this.httpClient.delete(this.USER_CHALLENGES_URI + userId + "/challenges/" + challengeId);
  }

  uploadImageToChallengeResult(file: FormData, userId: string, challengeId: string): Observable<UserChallenge> {
    return this.httpClient.put<UserChallenge>(this.USER_CHALLENGES_URI + userId + "/challenges/" + challengeId + "/image", file);
  }

}
