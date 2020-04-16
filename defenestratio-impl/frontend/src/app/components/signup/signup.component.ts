import {AfterViewChecked, Component, ElementRef, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {UserService} from "../../services/user.service";
import {StorageService} from "../../services/storage.service";
import {User} from "../../models/user";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit, AfterViewChecked {
  user: User = new User();
  registrationFormGroup = new FormGroup({
    username: new FormControl('', [
      Validators.required
    ]),
    password: new FormControl('', [
      Validators.required
    ])
  });
  registrationFailed: boolean;

  constructor(private elementRef: ElementRef,
              private router: Router,
              private userService: UserService,
              private storageService: StorageService) {
  }

  ngOnInit() {
    this.registrationFailed = false;
  }

  onRegistrationSubmit() {
    this.register();
  }

  ngAfterViewChecked() {
    var tiltScript = document.createElement("script");
    tiltScript.type = "text/javascript";
    tiltScript.src = "../../assets/scripts/js/tilt.jquery.min.js";
    this.elementRef.nativeElement.appendChild(tiltScript);
  }

  navigateToUrl(url) {
    this.router.navigateByUrl(url);
  }

  register(): void {
    this.user = new User();
    this.user.password = this.registrationFormGroup.get('password').value;
    this.user.username = this.registrationFormGroup.get('username').value;
    this.userService.register(this.user).subscribe(
      resp => {
        this.user = resp.body;
        this.storageService.currentToken = resp.headers.get('X-Auth-Token');
        this.storageService.currentUser = this.user;
        this.navigateToUrl('home');
      }, error => {
        this.registrationFailed = true;
      }
    )
  }

}
