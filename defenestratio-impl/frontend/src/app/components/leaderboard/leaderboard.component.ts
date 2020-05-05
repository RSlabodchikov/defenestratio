import {Component, OnInit} from '@angular/core';
import {LeaderboardService} from "../../services/leaderboard.service";
import {Router} from "@angular/router";
import {Profile} from "../../models/profile";
import {UserService} from "../../services/user.service";
import {StorageService} from "../../services/storage.service";
import {ProfileService} from "../../services/profile-service/profile.service";

@Component({
  selector: 'leaderboard',
  templateUrl: './leaderboard.component.html',
  styleUrls: ['./leaderboard.component.css']
})
export class LeaderboardComponent implements OnInit {

  private profileList: Profile[];
  private userId: string;
  private role: string;
  private profile: Profile;

  constructor(private leaderboardService: LeaderboardService,
              private router: Router,
              private storageService: StorageService,
              private userService: UserService,
              private profileService: ProfileService,
  ) {
  }

  ngOnInit() {
    if (this.userService.isAuthenticated()) {
      this.userId = this.storageService.currentUser.id;
      this.role = this.storageService.currentUser.role;
      this.profileService.getOneProfile(this.userId).subscribe(response => {
        this.profile = response;
      });
    }
    this.leaderboardService.getLeadersList().subscribe(response => {
      this.profileList = response;
    })
  }

  disableUser(profileId: string) {
    this.userService.disableUser(profileId).subscribe();
  }
}
