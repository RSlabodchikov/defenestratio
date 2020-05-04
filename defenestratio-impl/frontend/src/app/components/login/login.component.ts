import {AfterViewChecked, Component, ElementRef, OnInit} from '@angular/core';
import "../../../assets/scripts/js/tilt.jquery.min.js"
import {Router} from "@angular/router";
import {UserService} from "../../services/user.service";
import {StorageService} from "../../services/storage.service";
import {User} from "../../models/user";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, AfterViewChecked {
  user: User = new User();
  loginFormGroup = new FormGroup({
    username: new FormControl('', [
      Validators.required
    ]),
    password: new FormControl('', [
      Validators.required
    ])
  });
  incorrectLogin: boolean;


  constructor(private elementRef: ElementRef,
              private router: Router,
              private userService: UserService,
              private storageService: StorageService) {
  }

  ngOnInit() {
    this.incorrectLogin = false;
  }

  onLoginSubmit() {
    this.login();
  }

  login(): void {
    this.user = new User();
    this.user.password = this.loginFormGroup.get('password').value;
    this.user.username = this.loginFormGroup.get('username').value;
    this.userService.login(this.user).subscribe(
      resp => {
        this.user = resp.body;
        this.storageService.currentToken = resp.headers.get('X-Auth-Token');
        this.storageService.currentUser = this.user;
        this.navigateToUrl('home');
      }, error => {
        this.incorrectLogin = true;
      }
    )
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

  closeAlert() {
    this.incorrectLogin = false;
  }

}
