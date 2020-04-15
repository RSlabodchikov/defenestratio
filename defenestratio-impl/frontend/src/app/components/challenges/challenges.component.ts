import {Component, OnInit} from '@angular/core';
import {ChallengeModel} from "../../models/challenge.model";
import {ChallengeService} from "../../services/challenge.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-challenges',
  templateUrl: './challenges.component.html',
  styleUrls: ['./challenges.component.css']
})
export class ChallengesComponent implements OnInit {

  private challenges: ChallengeModel[];
  private challengesLevel1: ChallengeModel[] = [];
  private challengesLevel2: ChallengeModel[] = [];
  private challengesLevel3: ChallengeModel[] = [];


  constructor(private challengeService: ChallengeService,
              private router: Router) {
  }

  ngOnInit() {
    this.challenges = this.challengeService.getAllChallenges();
    this.challengesLevel1 = this.getChallengesByLvl(this.challenges, "1");
    this.challengesLevel2 = this.getChallengesByLvl(this.challenges, "2");
    this.challengesLevel3 = this.getChallengesByLvl(this.challenges, "3");
  }

  getChallengesByLvl(challenges: ChallengeModel[], level: string): ChallengeModel[] {
    let challengesLvl: ChallengeModel[] = [];
    challenges.forEach(function (challenge) {
      if (challenge.level == level) {
        challengesLvl.push(challenge);
      }
    });
    return challengesLvl;
  }

  addChallenge(userId: string, challengeId: string) {
    this.challengeService.addUserChallenge(userId, challengeId);
  }

}
