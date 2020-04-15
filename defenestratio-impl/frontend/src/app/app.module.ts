import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {LandingComponent} from './components/landing/landing.component';
import {LeaderboardComponent} from './components/leaderboard/leaderboard.component';
import {NavbarComponent} from './components/navbar/navbar.component';
import {ChallengesComponent} from './components/challenges/challenges.component';
import {LoginComponent} from "./components/login/login.component";
import {AngularTiltModule} from "angular-tilt";
import { SignupComponent } from './components/signup/signup.component';
import { FooterComponent } from './components/footer/footer.component';
import { HttpClientModule } from '@angular/common/http';

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
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    AngularTiltModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
