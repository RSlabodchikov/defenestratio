import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ProfileService} from "../../services/profile-service/profile.service";
import {Profile} from "../../models/profile";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {StorageService} from "../../services/storage.service";
import {User} from "../../models/user";
import {ChallengeService} from "../../services/challenge.service";
import {UserChallenge} from "../../models/user.challenge";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  user: User;
  profile: Profile = new Profile();
  userChallenges: UserChallenge[] = [];
  tempUserChallenge: UserChallenge;
  comment: string;
  public selectedFile;
  imgURL: any;
  receivedImageData: any;
  base64Data: any;
  convertedImage: any;

  form: FormGroup = new FormGroup({
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required)
  });

  challengeForm: FormGroup = new FormGroup({
    comment: new FormControl('')
  });

  constructor(private router: Router,
              private profileService: ProfileService,
              private route: ActivatedRoute,
              private storageService: StorageService,
              private challengeService: ChallengeService,
              private httpClient: HttpClient) {
  }

  ngOnInit() {
    this.user = this.storageService.currentUser;
    this.profile = this.user.profile;
    this.challengeService.getAllUserChallenges(this.user.id).subscribe(userChallenges => {
      this.userChallenges = userChallenges;
    })
  }

  submit() {
    if (this.form.valid) {
      this.profile.firstName = this.firstName.value;
      this.profile.lastName = this.lastName.value;
      this.updateProfile();
    }
  }

  sendResult() {

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

  setChallenge(challenge: UserChallenge) {
    this.tempUserChallenge = challenge;
  }

  public onFileChanged(event) {
    this.selectedFile = event.target.files[0];

    // Below part is used to display the selected image
    let reader = new FileReader();
    reader.readAsDataURL(event.target.files[0]);
    reader.onload = (event2) => {
      this.imgURL = reader.result;
    };

  }

  onUpload() {
    const uploadData = new FormData();
    uploadData.append('image', this.selectedFile, this.selectedFile.name);

    this.challengeService.uploadImageToChallengeResult(uploadData, this.user.id, this.tempUserChallenge.id)
      .subscribe(res => {
        console.log(res);
        this.receivedImageData = res.challengeResult.image.picture;
        this.base64Data = this.receivedImageData;
        this.convertedImage = 'data:image/jpeg;base64,' + this.base64Data;
        window.location.reload();
      });
  }

  clearBuffer() {
    this.comment = null;
    this.imgURL = null;
    this.selectedFile = null;
  }

  removeUserChallenge(challengeId: string) {
    this.challengeService.removeUserChallenge(this.user.id, challengeId).subscribe();
    window.location.reload();
  }

}
