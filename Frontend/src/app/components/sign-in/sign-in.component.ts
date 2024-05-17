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
import { RouterLink, withDebugTracing } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { AuthService } from '../../service/auth.service';
import { tap } from 'rxjs';
import { Oauth2Service } from '../../service/oauth2.service';

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

  constructor(
    private authService: AuthService,
    private oauthService: Oauth2Service
  ) {}

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
            if (response) sessionStorage.setItem('jwt', response.token);
            this.authService.refreshAuth$.next(true);
          },
          error: (err) => {
            this.loading = false;
            this.error = err;
          },
        });
  }
  googleAuth() {
    window.location.href = 'http://localhost:8080/oauth2/authorization/google';
    // window.location.href =
    //   'https://accounts.google.com/o/oauth2/v2/auth/oauthchooseaccount?response_type=code&client_id=854006941325-m6rag83jr31o8h18adllhqusf7jao5ov.apps.googleusercontent.com&scope=profile%20email&state=pFWRLYuo5WAEnBOOvngIgxGp1narXK7LxRHSqFMFGPE%3D&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Flogin%2Foauth2%2Fcode%2Fgoogle&service=lso&o2v=2&ddm=0&flowName=GeneralOAuthFlow';
    // this.oauthService.googleSignIn().subscribe({
    //   next: (resp) => {
    //     console.log('google resp', resp);
    //   },
    //   error: (err) => {
    //     console.log('google error', err);
    //   },
    // });
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
