import { Injectable } from '@angular/core';
import {Profile} from "../../models/profile/profile";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  private profileUrl: string;
  private profile: Profile;

  constructor(private http: HttpClient) {
    this.profileUrl = '//localhost:8080/d181deee-e88b-48d5-a356-9c12a0824cc2';
  }

  public getOneProfile(id: string): Observable<Profile> {
    return this.http.get<Profile>(this.profileUrl + '/users/' + id + '/profile');
  }

  public getAllProfiles(): Observable<Profile[]> {
    return this.http.get<Profile[]>(this.profileUrl + '/users');
  }

  public updateProfile(profile : Profile) {
    return this.http.post(this.profileUrl + '/users/1/profile', profile);
  }

}
