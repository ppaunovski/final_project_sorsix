import { Component } from '@angular/core';
import {
  FormControl,
  FormGroupDirective,
  NgForm,
  Validators,
  FormsModule,
  ReactiveFormsModule,
} from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { RouterLink } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { AuthService } from '../../service/auth.service';
import { tap } from 'rxjs';

@Component({
  selector: 'app-sign-in',
  standalone: true,
  providers: [],
  imports: [
    MatFormFieldModule,
    MatInputModule,
    RouterLink,
    MatButtonModule,
    FormsModule,
    MatCardModule,
    ReactiveFormsModule,
  ],
  templateUrl: './sign-in.component.html',
  styleUrl: './sign-in.component.css',
})
export class SignInComponent {
  email: string | undefined;
  password: string | undefined;
  loading = false;
  error: any;

  emailFormControl = new FormControl('', [
    Validators.required,
    Validators.email,
  ]);

  matcher = new MyErrorStateMatcher();

  constructor(private authService: AuthService) {}

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

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(
    control: FormControl | null,
    form: FormGroupDirective | NgForm | null
  ): boolean {
    const isSubmitted = form && form.submitted;
    return !!(
      control &&
      control.invalid &&
      (control.dirty || control.touched || isSubmitted)
    );
  }
}
