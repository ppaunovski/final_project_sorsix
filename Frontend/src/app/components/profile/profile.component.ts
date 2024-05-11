import { Component, OnInit } from '@angular/core';
import { UserAccountService } from '../../service/user-account.service';
import { UserAccount } from '../../model/UserAccount';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css',
})
export class ProfileComponent implements OnInit {
  user: UserAccount | undefined;
  constructor(private userService: UserAccountService) {}
  ngOnInit(): void {
    this.userService.getUserInfo().subscribe((user) => {
      this.user = user;
    });
  }
}
