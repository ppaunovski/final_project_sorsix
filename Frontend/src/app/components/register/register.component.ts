import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInput, MatInputModule } from '@angular/material/input';
import { RouterLink } from '@angular/router';
import { AuthService } from '../../service/auth.service';
import { tap } from 'rxjs';

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

  error: any;
  loading = false;

  constructor(private authService: AuthService) {}

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
