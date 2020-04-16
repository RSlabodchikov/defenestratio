import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  authenticated: boolean;

  constructor(private router: Router,
              private userService: UserService) {
  }

  ngOnInit() {
    this.authenticated = this.userService.isAuthenticated();
  }

  navigateToUrl(url) {
    this.router.navigateByUrl(url);
  }

  logout() {
    if (this.authenticated) {
     this.userService.logout();
     this.authenticated = false;
    }
  }

}
