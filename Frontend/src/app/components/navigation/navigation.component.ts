import {Component, OnInit} from '@angular/core';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule} from '@angular/forms';
import {RouterLink} from '@angular/router';
import {MatButtonModule} from '@angular/material/button';
import {SearchBarComponent} from '../search-bar/search-bar.component';
import {AuthService} from '../../service/auth.service';
import {filter, map, mergeMap, tap} from 'rxjs';
import {UserAccountService} from '../../service/user-account.service';
import {UserAccount} from '../../model/UserAccount';
import {MatMenuModule} from '@angular/material/menu';
import {ImageToUrlService} from '../../service/image-to-url.service';
import {PropertyService} from '../../service/property.service';

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
  closeMap() {
    this.showMapLocal = false;
    this.propertyService.showMap$.next(false);
  }

  signOut() {
    sessionStorage.removeItem('jwt');
    this.authService.refreshAuth$.next(true);
  }

  // @ViewChild(MatMenuTrigger) trigger: MatMenuTrigger;

  // someMethod() {
  //   this.trigger.openMenu();
  // }
  user: UserAccount | undefined;
  imageURL: string | undefined;
  showMapLocal = false;

  constructor(
    private authService: AuthService,
    private userService: UserAccountService,
    private urlService: ImageToUrlService,
    private propertyService: PropertyService
  ) {
  }

  ngOnInit(): void {
    this.authService.refreshAuth$
      .pipe(mergeMap(() => this.userService.getUserInfo()))
      .pipe(
        tap((user) => {
          this.user = user;
        }),
        filter((user) => user != undefined),
        map((user) => user?.id!),
        mergeMap((id) => this.userService.getProfileImage(id))
      )
      .subscribe({
        next: (profilePicture) => {
          this.imageURL = this.urlService.bytesToURL(
            profilePicture.image,
            profilePicture.type
          );
        },

        error: (error) => {
          console.log('navigation error', error);

          this.imageURL = undefined;
        },
      });

    this.userService
      .getUserInfo()
      .pipe(
        tap((user) => {
          this.user = user;
        }),
        filter((user) => user != undefined),
        map((user) => user?.id!),
        mergeMap((id) => this.userService.getProfileImage(id))
      )
      .subscribe({
        next: (profilePicture) => {
          this.imageURL = this.urlService.bytesToURL(
            profilePicture.image,
            profilePicture.type
          );
        },

        error: (error) => {
          console.log('navigation error', error);

          this.imageURL = undefined;
        },
      });
  }

  showMap() {
    this.showMapLocal = !this.showMapLocal;
    this.propertyService.showMap$.next(this.showMapLocal);
  }
}
