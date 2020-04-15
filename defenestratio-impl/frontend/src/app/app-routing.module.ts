import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {LeaderboardComponent} from "./components/leaderboard/leaderboard.component";
import {LandingComponent} from "./components/landing/landing.component";
import {APP_BASE_HREF} from "@angular/common";
import {ChallengesComponent} from "./components/challenges/challenges.component";
import {LoginComponent} from "./components/login/login.component";
import {SignupComponent} from "./components/signup/signup.component";
import {ChallengeService} from "./services/challenge.service";


const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'home', component: LandingComponent},
  {path: 'leaderboard', component: LeaderboardComponent},
  {path: 'challenges', component: ChallengesComponent},
  {path: 'login', component: LoginComponent},
  {path: 'signup', component: SignupComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: false})],
  exports: [RouterModule],
  providers: [
    {provide: APP_BASE_HREF, useValue: '/'},
    ChallengeService
    ]
})
export class AppRoutingModule {
}
