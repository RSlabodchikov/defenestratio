import {AfterViewChecked, Component, ElementRef, OnInit} from '@angular/core';
import "../../../assets/scripts/js/tilt.jquery.min.js"
import {Router} from "@angular/router";
import {UserService} from "../../services/user.service";
import {StorageService} from "../../services/storage.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {User} from "../../models/user";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, AfterViewChecked {
  user: User = new User();


  constructor(private elementRef: ElementRef,
              private router: Router,
              private userService: UserService,
              private storageService: StorageService) {
  }

  ngOnInit() {
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

  onLoginClick() {
    this.userService.login(this.user).subscribe(resp => {
      if (resp.status != 200) {
        console.log("Exception during authentication");
        return;
      }
      const token = resp.headers.get('X-Auth-Token');
      this.user = resp.body;
      if (token != null && this.user != null) {
        this.storageService.currentToken = token;
        this.storageService.currentUser = this.user;
      }
    })
  }

}
