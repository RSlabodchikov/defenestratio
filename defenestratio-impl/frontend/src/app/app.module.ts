import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {LandingComponent} from './landing/landing.component';
import {LeaderboardComponent} from './leaderboard/leaderboard.component';
import {NavbarComponent} from './navbar/navbar.component';
import {ChallengesComponent} from './challenges/challenges.component';
import {LoginComponent} from "./login/login.component";
import {AngularTiltModule} from "angular-tilt";
import { SignupComponent } from './signup/signup.component';
import { FooterComponent } from './footer/footer.component';

@NgModule({
  declarations: [
    AppComponent,
    LandingComponent,
    LoginComponent,
    LeaderboardComponent,
    NavbarComponent,
    ChallengesComponent,
    SignupComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AngularTiltModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
