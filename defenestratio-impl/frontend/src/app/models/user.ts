import {Profile} from "./profile";

export class User {
  id: string;
  username: string;
  password: string;
  role: string;
  profile: Profile;
  disabled: boolean;
}
