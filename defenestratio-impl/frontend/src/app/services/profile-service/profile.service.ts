import {Injectable} from '@angular/core';
import {Profile} from "../../models/profile";
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../../models/user";

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

  public updateProfile(profile: Profile, id: string): Observable<HttpResponse<any>>{
    return this.http.post<HttpResponse<any>>(this.profileUrl + id + '/profile', profile);
  }

}
