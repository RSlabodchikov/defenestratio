import {Injectable} from '@angular/core';
import {ChallengeModel} from "../models/challenge.model";
import {HttpClient, HttpParams} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ChallengeService {
  private GET_ALL_CHALLENGES_URI = "http://localhost:8080/2235eb08-6e04-484f-a451-cc2b10a42c9a/challenges";
  private GET_ALL_CHALLENGES_FOR_USER_URI = "http://localhost:8080/2235eb08-6e04-484f-a451-cc2b10a42c9a/challenges/user";
  private CREATE_USER_CHALLENGE_URI = "http://localhost:8080/2235eb08-6e04-484f-a451-cc2b10a42c9a/users/";

  private challenges: ChallengeModel[] = [];

  constructor(private httpClient: HttpClient) {
  }

  getAllChallenges(): ChallengeModel[] {
    this.httpClient.get<ChallengeModel[]>(this.GET_ALL_CHALLENGES_URI).subscribe(response => {
      this.challenges = response as ChallengeModel[];
    });
    return this.challenges;
  }

  getAllChallengesAvailableForUser(userId: string): ChallengeModel[] {
    let body = new HttpParams();
    body = body.set('userId', userId);
    this.httpClient.get<ChallengeModel[]>(this.GET_ALL_CHALLENGES_FOR_USER_URI, {params: body}).subscribe(response => {
      this.challenges = response as ChallengeModel[];
    });
    return this.challenges;
  }

  addUserChallenge(userId: string, challengeId: string) {
    let body = new HttpParams();
    body = body.set('challengeId', challengeId);
    this.httpClient.post(this.CREATE_USER_CHALLENGE_URI + userId + "/challenges", body).subscribe();
  }

}
