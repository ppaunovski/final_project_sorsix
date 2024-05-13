import { Component, OnInit, Output, ViewChild } from '@angular/core';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { SearchBarComponent } from '../search-bar/search-bar.component';
import { AuthService } from '../../service/auth.service';
import { __importDefault } from 'tslib';
import { map, mergeMap } from 'rxjs';
import { UserAccountService } from '../../service/user-account.service';
import { UserAccount } from '../../model/UserAccount';
import { MatMenuModule, MatMenuTrigger } from '@angular/material/menu';

@Component({
  selector: 'app-navigation',
  standalone: true,
  imports: [
    MatToolbarModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    RouterLink,
    MatButtonModule,
    SearchBarComponent,
    MatInputModule,
    MatMenuModule,
  ],
  templateUrl: './navigation.component.html',
  styleUrl: './navigation.component.css',
})
export class NavigationComponent implements OnInit {
  signOut() {
    localStorage.removeItem('jwt');
    this.authService.refreshAuth$.next(true);
  }
  // @ViewChild(MatMenuTrigger) trigger: MatMenuTrigger;

  // someMethod() {
  //   this.trigger.openMenu();
  // }
  user: UserAccount | undefined;

  constructor(
    private authService: AuthService,
    private userService: UserAccountService
  ) {}

  ngOnInit(): void {
    this.authService.refreshAuth$
      .pipe(mergeMap(() => this.userService.getUserInfo()))
      .subscribe({
        next: (user) => {
          this.user = user;
          console.log('user', user);
        },
        error: (error) => {
          this.user = undefined;
        },
      });

    this.userService.getUserInfo().subscribe({
      next: (user) => {
        this.user = user;
      },
      error: (error) => {
        console.log(error);
      },
    });
  }
}
