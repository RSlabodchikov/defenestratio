import {Injectable} from '@angular/core';
import {ChallengeModel} from "../models/challenge.model";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ChallengeService {
  private CREATE_CHALLENGE_URI = "http://localhost:8080/d181deee-e88b-48d5-a356-9c12a0824cc2/challenges/";

  private DELETE_CHALLENGE_URI = "http://localhost:8080/d181deee-e88b-48d5-a356-9c12a0824cc2/challenges/";

  private GET_ALL_CHALLENGES_URI = "http://localhost:8080/2235eb08-6e04-484f-a451-cc2b10a42c9a/challenges/";

  private UPDATE_CHALLENGE_URL = "http://localhost:8080/d181deee-e88b-48d5-a356-9c12a0824cc2/challenges/update";
  private GET_ALL_CHALLENGES_URI = "http://localhost:8080/2235eb08-6e04-484f-a451-cc2b10a42c9a/challenges";
  private GET_ALL_CHALLENGES_FOR_USER_URI = "http://localhost:8080/2235eb08-6e04-484f-a451-cc2b10a42c9a/challenges/user";
  private CREATE_USER_CHALLENGE_URI = "http://localhost:8080/2235eb08-6e04-484f-a451-cc2b10a42c9a/users/";

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

  createChallenge(challenge: ChallengeModel): Observable<ChallengeModel> {
    return this.httpClient.post<ChallengeModel>(this.CREATE_CHALLENGE_URI,challenge);
  }

  deleteChallenge(challengeId: string): Observable<any> {
    return this.httpClient.delete(this.DELETE_CHALLENGE_URI + challengeId);
  }
  addUserChallenge(userId: string, challengeId: string) {
    let body = new HttpParams();
    body = body.set('challengeId', challengeId);
    this.httpClient.post(this.CREATE_USER_CHALLENGE_URI + userId + "/challenges", body).subscribe();
  }

  updateChallenge(challenge: ChallengeModel): Observable<any> {

    return this.httpClient.post(this.UPDATE_CHALLENGE_URL, challenge);
  }

  public getOneChallenge(id: string): Observable<ChallengeModel> {
    return this.httpClient.get<ChallengeModel>(this.GET_ALL_CHALLENGES_URI + id);
  }
}
