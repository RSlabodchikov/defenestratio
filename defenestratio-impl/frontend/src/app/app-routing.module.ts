import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {LeaderboardComponent} from "./leaderboard/leaderboard.component";
import {LandingComponent} from "./landing/landing.component";
import {APP_BASE_HREF} from "@angular/common";
import {ChallengesComponent} from "./challenges/challenges.component";
import {LoginComponent} from "./login/login.component";
import {SignupComponent} from "./signup/signup.component";


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
  providers: [{provide: APP_BASE_HREF, useValue: '/'}]
})
export class AppRoutingModule {
}
