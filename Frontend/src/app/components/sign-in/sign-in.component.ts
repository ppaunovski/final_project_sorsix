import { Component } from '@angular/core';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { provideNativeDateAdapter } from '@angular/material/core';
import { MatCardModule } from '@angular/material/card';
import { AuthService } from '../../service/auth.service';
import { UnaryFunction, tap } from 'rxjs';

@Component({
  selector: 'app-sign-in',
  standalone: true,
  providers: [provideNativeDateAdapter()],
  imports: [
    MatFormFieldModule,
    MatInputModule,
    RouterLink,
    MatButtonModule,
    FormsModule,
    MatCardModule,
  ],
  templateUrl: './sign-in.component.html',
  styleUrl: './sign-in.component.css',
})
export class SignInComponent {
  handleInput(type: string, value: string) {
    switch (type) {
      case 'email':
        this.email = value;
        break;
      case 'password':
        this.password = value;
        break;
    }
  }
  email: string | undefined;
  password: string | undefined;
  loading = false;
  error: any;

  constructor(private authService: AuthService) {}

  handleSubmit() {
    if (this.email && this.password)
      this.authService
        .login({
          email: this.email,
          password: this.password,
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
            if (response) localStorage.setItem('jwt', response.token);
            this.authService.refreshAuth$.next(true);
          },
          error: (err) => {
            this.loading = false;
            this.error = err;
          },
        });
  }
}
