import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ProfileService} from "../../services/profile-service/profile.service";
import {Profile} from "../../models/profile";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {StorageService} from "../../services/storage.service";
import {User} from "../../models/user";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  user: User;
  profile: Profile = new Profile();

  form: FormGroup = new FormGroup({
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required)
  });

  constructor(private router: Router,
              private profileService: ProfileService,
              private route: ActivatedRoute,
              private storageService: StorageService) {
  }

  ngOnInit() {
    this.user = this.storageService.currentUser;
    this.profile = this.user.profile;
  }

  submit() {
    if (this.form.valid) {
      this.profile.firstName = this.firstName.value;
      this.profile.lastName = this.lastName.value;
      this.updateProfile();
    }
  }

  updateProfile() {
    this.profileService.updateProfile(this.profile, this.user.id).subscribe(data => {
      this.redirect('/profile');
    });
  }

  redirect(url: string) {
    this.router.navigateByUrl(url);
  }

  get firstName() {
    return this.form.get('firstName');
  }

  get lastName() {
    return this.form.get('lastName');
  }

}
