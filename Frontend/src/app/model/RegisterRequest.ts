import {UserImage} from "./UserImage";

export interface RegisterRequest {
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  confirmPassword: string;
  image: UserImage;
}
