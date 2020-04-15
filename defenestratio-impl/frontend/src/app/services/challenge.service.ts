import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {ChallengeModel} from "../models/challenge.model";
import {HttpClient, HttpParams} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ChallengeService {
  private GET_ALL_CHALLENGES_URI = "http://localhost:8080/2235eb08-6e04-484f-a451-cc2b10a42c9a/challenges";
  private CREATE_USER_CHALLENGE_URI = "http://localhost:8080/2235eb08-6e04-484f-a451-cc2b10a42c9a/users/";

  private challenges: ChallengeModel[];

  constructor(private httpClient: HttpClient) {
  }

  getAllChallenges(): ChallengeModel[] {
    this.httpClient.get<ChallengeModel[]>(this.GET_ALL_CHALLENGES_URI).subscribe(response => {
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
