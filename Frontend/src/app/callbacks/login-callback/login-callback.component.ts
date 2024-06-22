import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthService} from '../../service/auth.service';

@Component({
  selector: 'app-login-callback',
  standalone: true,
  imports: [],
  templateUrl: './login-callback.component.html',
  styleUrl: './login-callback.component.css',
})
export class LoginCallbackComponent implements OnInit {
  constructor(
    private route: ActivatedRoute,
    private authService: AuthService,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.route.queryParams
      // .pipe(
      //   filter(params => params.has('token')),
      //   map(params => params.get('token')!),
      //   mergeMap(token => this.authService.login)
      // )
      .subscribe({
        next: (params) => {
          const token = params['token'];
          sessionStorage.setItem('jwt', token);
          console.log('callback', token);

          this.authService.refreshAuth$.next(true);
          this.router.navigate(['/properties']).then(() => {
            console.log('Navigation to /properties completed.');
          });
        },
        error: (err) => {
          console.log(err);
        },
      });
  }
}
