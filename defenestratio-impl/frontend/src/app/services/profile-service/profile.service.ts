import {Injectable} from '@angular/core';
import {Profile} from "../../models/profile";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  private profileUrl: string;
  private profile: Profile;

  constructor(private http: HttpClient) {
    this.profileUrl = '/api/users/';
  }

  public getOneProfile(id: string): Observable<Profile> {
    return this.http.get<Profile>(this.profileUrl + id + '/profile');
  }

  public updateProfile(profile: Profile, id: string) {
    return this.http.post(this.profileUrl + id + '/profile', profile);
  }

}
