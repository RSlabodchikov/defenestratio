import {Component, OnInit} from '@angular/core';
import {ChallengeModel} from "../../models/challenge.model";
import {ChallengeService} from "../../services/challenge.service";
import {Router} from "@angular/router";
import {StorageService} from "../../services/storage.service";
import {UserService} from "../../services/user.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-challenges',
  templateUrl: './challenges.component.html',
  styleUrls: ['./challenges.component.css']
})
export class ChallengesComponent implements OnInit {

  private challenge: ChallengeModel;
  private challenges: ChallengeModel[] = [];
  private themes: string[] = [];
  private allChallenges: ChallengeModel[] = [];
  private userId: string;
  private roleId: string;
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

  themeForm: FormGroup = new FormGroup({
    theme: new FormControl('')
  });

  constructor(private challengeService: ChallengeService,
              private router: Router,
              private storageService: StorageService,
              private userService: UserService) {
  }

  ngOnInit() {
    if (this.userService.isAuthenticated()) {
      this.userId = this.storageService.currentUser.id;
      this.roleId = this.storageService.currentUser.role;
    }
    this.fillChallenges();
  }

  fillChallenges() {
    if (this.userService.isAuthenticated()) {
      this.challengeService.getAllChallengesAvailableForUser(this.userId).subscribe(response => {
        this.challenges = response;
        this.challenge = this.challenges[0];
        this.allChallenges = this.challenges;
        for (let i in this.challenges) {
          if(!this.themes.includes(this.challenges[i].theme)) {
            this.themes.push(this.challenges[i].theme);
          }
        }
        this.challengesLevel1 = this.getChallengesByLvl(this.challenges, "1");
        this.challengesLevel2 = this.getChallengesByLvl(this.challenges, "2");
        this.challengesLevel3 = this.getChallengesByLvl(this.challenges, "3");
      });
    } else {
      this.challengeService.getAllChallenges().subscribe(response => {
        this.challenges = response;
        this.challenge = this.challenges[0];
        this.allChallenges = this.challenges;
        for (let i in this.challenges) {
          if(!this.themes.includes(this.challenges[i].theme)) {
            this.themes.push(this.challenges[i].theme);
          }
        }
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

  addChallenge(userId: string, challengeId: string) {
    this.challengeService.addUserChallenge(userId, challengeId);
    this.fillChallenges();
    window.location.reload();
  }

  deleteChallenge(challengeId: string): void {
    this.challengeService.deleteChallenge(challengeId).subscribe(() => {
      location.reload();
    });
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

  saveChallenge2(challengeForSave: ChallengeModel) {
    this.challenge = challengeForSave;
    console.log(challengeForSave);
  }

  save() {
    if(this.form.valid) {
      this.challengeService.getAllChallenges().subscribe(temp => {
        this.challenge.id = temp[temp.length - 1].id + 1;
        this.challenge.name = this.name.value;
        this.challenge.shortDescription = this.shortDescription.value;
        this.challenge.fullDescription = this.fullDescription.value;
        this.challenge.level = this.level.value;
        this.challenge.points = this.points.value;
        this.saveChallenge();
      });
    }
  }

  saveChallenge() {
    this.challengeService.createChallenge(this.challenge).subscribe(response => {
      location.reload();
    })
  }

  pickTheme() {
    let theme = this.themeForm.get('theme').value;
    if (theme === 'All') {
      this.fillChallenges();
    }
    else {
      // this.challengeService.getThemeChallenges(theme).subscribe(response => {
      //   this.challenges = response;
      //   this.challenge = this.challenges[0];
      //   for (let i in this.challenges) {
      //     if(!this.themes.includes(this.challenges[i].theme)) {
      //       this.themes.push(this.challenges[i].theme);
      //     }
      //   }
      //   this.challengesLevel1 = this.getChallengesByLvl(this.challenges, "1");
      //   this.challengesLevel2 = this.getChallengesByLvl(this.challenges, "2");
      //   this.challengesLevel3 = this.getChallengesByLvl(this.challenges, "3");
      // });
      this.challenges = [];
      this.allChallenges.forEach(challenge => {
        if (challenge.theme == theme) {
          this.challenges.push(challenge);
        }
      });
      this.challengesLevel1 = this.getChallengesByLvl(this.challenges, "1");
      this.challengesLevel2 = this.getChallengesByLvl(this.challenges, "2");
      this.challengesLevel3 = this.getChallengesByLvl(this.challenges, "3");
    }
  }

  get name() { return this.form.get('name'); }

  get shortDescription() { return this.form.get('shortDescription'); }

  get fullDescription() { return this.form.get('fullDescription'); }

  get level() { return this.form.get('level'); }

  get points() { return this.form.get('points'); }
}
