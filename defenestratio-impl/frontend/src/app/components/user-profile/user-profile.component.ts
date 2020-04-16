import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ProfileService} from "../../services/profile-service/profile.service";
import {Profile} from "../../models/profile/profile";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  profile: Profile = new Profile();
  profiles: Profile[];
  form: FormGroup = new FormGroup({
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required)
  });
  //id: string = this.route.snapshot.paramMap.get('id')

  constructor(private router: Router,
              private profileService: ProfileService,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.profileService.getOneProfile('1').subscribe(data => {
      this.profile = data;
    });
  }

  submit(){
    if(this.form.valid){
      this.profile.firstName = this.firstName.value;
      this.profile.lastName = this.lastName.value;
      this.updateProfile();
    }
  }

  updateProfile() {
    this.profileService.updateProfile(this.profile).subscribe(data => {
      this.redirect('/profile');
    });
  }

  redirect(url: string) {
    this.router.navigateByUrl(url);
  }

  get firstName() { return this.form.get('firstName'); }

  get lastName() { return this.form.get('lastName'); }

}
