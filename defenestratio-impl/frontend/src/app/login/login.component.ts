import {AfterViewChecked, Component, ElementRef, OnInit} from '@angular/core';
import "../../assets/scripts/js/tilt.jquery.min.js"
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, AfterViewChecked {


  constructor(private elementRef: ElementRef,
              private router: Router) { }

  ngOnInit() {
  }

  ngAfterViewChecked() {
    var etoPolnayaPizda = document.createElement("script");
    etoPolnayaPizda.type = "text/javascript";
    etoPolnayaPizda.src = "../../assets/scripts/js/tilt.jquery.min.js";
    this.elementRef.nativeElement.appendChild(etoPolnayaPizda);
  }

  navigateToUrl(url) {
    this.router.navigateByUrl(url);
  }

}
