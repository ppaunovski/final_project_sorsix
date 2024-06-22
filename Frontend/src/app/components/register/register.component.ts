import {Component} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {MatButtonModule} from '@angular/material/button';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {RouterLink} from '@angular/router';
import {AuthService} from '../../service/auth.service';
import {tap} from 'rxjs';
import {UserImage} from '../../model/UserImage';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    RouterLink,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css',
})
export class RegisterComponent {
  handleInput(type: string, value: string) {
    switch (type) {
      case 'firstName':
        this.firstName = value;
        break;
      case 'lastName':
        this.lastName = value;
        break;
      case 'email':
        this.email = value;
        break;
      case 'password':
        this.password = value;
        break;
      case 'confirmPassword':
        this.confirmPassword = value;
        break;
    }
  }

  firstName: string | undefined;
  lastName: string | undefined;
  email: string | undefined;
  password: string | undefined;
  confirmPassword: string | undefined;
  profileImage: UserImage = {
    id: 0,
    image: '',
    userAccountId: 0,
    type: '',
  };
  error: any;
  loading = false;


  constructor(private authService: AuthService) {
  }

  // onImagePicked(event: Event) {
  //   const target = event.target as HTMLInputElement;
  //   const files = target.files as FileList;
  //   for (let i = 0; i < files.length; i++) {
  //     this.images.push(files[i]);
  //   }
  //   for (let i = 0; i < this.images.length; i++) {
  //     const reader = new FileReader();
  //     reader.onload = (e) => {
  //       const base64 = e.target?.result;
  //       const bytes = base64?.toString().split(',')[1];
  //       const image: PropertyImage = {
  //         id: 0,
  //         imageByteArray: bytes??'',
  //         type: this.images[i].type,
  //         order: i,
  //         propertyId: 0,
  //       };
  //       this.propertyImages.push(image);
  //     };
  //     reader.readAsDataURL(this.images[i]);
  //   }

  // }


  onImagePicked($event: Event) {
    const fileInput = $event.target as HTMLInputElement;
    if (fileInput.files && fileInput.files.length > 0) {
      const file = fileInput.files[0];
      const reader = new FileReader();

      reader.onload = () => {
        this.profileImage.image = (reader.result as string).split(',')[1];
        console.log(this.profileImage.image);
      };
      reader.onerror = (error) => {
        console.error('Error reading file:', error);
      };

      reader.readAsDataURL(file);


    }
    if (fileInput.files && fileInput.files.length > 0) {
      this.profileImage = {
        id: 0,
        image: this.profileImage.image,
        userAccountId: 0,
        type: fileInput.files[0].type,
      };
    }
  }


  handleSubmit() {
    console.log(
      this.firstName,
      this.lastName,
      this.email,
      this.password,
      this.confirmPassword
    );

    if (
      this.firstName &&
      this.lastName &&
      this.email &&
      this.password &&
      this.confirmPassword
    )
      this.authService
        .register({
          firstName: this.firstName,
          lastName: this.lastName,
          email: this.email,
          password: this.password,
          confirmPassword: this.confirmPassword,
          image: this.profileImage,
        })
        .pipe(
          tap(() => {
            (this.loading = true), (this.error = null);
          })
        )
        .subscribe({
          next: (response) => {
            this.loading = false;
            this.error = null;
            localStorage.setItem('jwt', response.token);
            this.authService.refreshAuth$.next(true);
          },
          error: (err) => {
            this.loading = false;
            this.error = err;
          },
        });
  }
}
