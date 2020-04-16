import {Component, OnInit} from '@angular/core';
import {ChallengeModel} from "../../models/challenge.model";
import {ChallengeService} from "../../services/challenge.service";
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";

import {sleep} from "sleep-ts";

@Component({
  selector: 'app-challenges',
  templateUrl: './challenges.component.html',
  styleUrls: ['./challenges.component.css']
})
export class ChallengesComponent implements OnInit {

  private challenge: ChallengeModel;
  private challenges: ChallengeModel[];
  private challenges: ChallengeModel[] = [];
  private userId: string = "1";
  private challengesLevel1: ChallengeModel[] = [];
  private challengesLevel2: ChallengeModel[] = [];
  private challengesLevel3: ChallengeModel[] = [];
  form: FormGroup = new FormGroup({
    name: new FormControl('', Validators.required),
    shortDescription: new FormControl('',Validators.required),
    fullDescription: new FormControl('',Validators.required),
    points: new FormControl('',Validators.required),
    level: new FormControl('',Validators.required)
  });

  constructor(private challengeService: ChallengeService,
              private router: Router) {
  }

  ngOnInit() {
    this.challengeService.getOneChallenge('1').subscribe(data => {
      this.challenge = data;
    });
    this.challenges = this.challengeService.getAllChallenges();
    this.challengesLevel1 = this.getChallengesByLvl(this.challenges, "1");
    this.challengesLevel2 = this.getChallengesByLvl(this.challenges, "2");
    this.challengesLevel3 = this.getChallengesByLvl(this.challenges, "3");
    this.fillChallenges();
  }

  fillChallenges() {
    if (this.userId != null && this.userId.length != 0) {
      this.challengeService.getAllChallengesAvailableForUser(this.userId).subscribe(response => {
        this.challenges = response;
        this.challengesLevel1 = this.getChallengesByLvl(this.challenges, "1");
        this.challengesLevel2 = this.getChallengesByLvl(this.challenges, "2");
        this.challengesLevel3 = this.getChallengesByLvl(this.challenges, "3");
      });
    } else {
      this.challengeService.getAllChallenges().subscribe(response => {
        this.challenges = response;
        this.challengesLevel1 = this.getChallengesByLvl(this.challenges, "1");
        this.challengesLevel2 = this.getChallengesByLvl(this.challenges, "2");
        this.challengesLevel3 = this.getChallengesByLvl(this.challenges, "3");
      })
    }
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

  deleteChallenge(challengeId: string): void {
    this.challengeService.deleteChallenge(challengeId).subscribe(response => {
      this.challengeService.getAllChallenges();
    });
  }

  addChallenge(challenge: ChallengeModel): void {
    this.challengeService.createChallenge(challenge);
  }

  submit() {
    if(this.form.valid) {
      this.challenge.name = this.name.value;
      this.challenge.shortDescription = this.shortDescription.value;
      this.challenge.fullDescription = this.fullDescription.value;
      this.challenge.level = this.level.value;
      this.challenge.points = this.points.value;
      this.editChallenge();
    }
  }

  editChallenge() {
    this.challengeService.updateChallenge(this.challenge).subscribe(reponse => {
      location.reload();
    });
  }

  save() {
    if(this.form.valid) {
      this.challenge.id = this.challenges[this.challenges.length-1].id + 1;
      this.challenge.name = this.name.value;
      this.challenge.shortDescription = this.shortDescription.value;
      this.challenge.fullDescription = this.fullDescription.value;
      this.challenge.level = this.level.value;
      this.challenge.points = this.points.value;
      this.saveChallenge();
    }
  }

  saveChallenge() {
    this.challengeService.createChallenge(this.challenge).subscribe(response => {
      location.reload();
    })
  }

  get name() { return this.form.get('name'); }

  get shortDescription() { return this.form.get('shortDescription'); }

  get fullDescription() { return this.form.get('fullDescription'); }

  get level() { return this.form.get('level'); }

  get points() { return this.form.get('points'); }
  addChallenge(userId: string, challengeId: string) {
    this.challengeService.addUserChallenge(userId, challengeId);
    this.fillChallenges();
    window.location.reload();
  }

}
