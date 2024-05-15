import { Component, OnInit } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { NavigationComponent } from './components/navigation/navigation.component';
import { AuthService } from './service/auth.service';
import {
  MatProgressSpinner,
  MatProgressSpinnerModule,
} from '@angular/material/progress-spinner';
import { FooterComponent } from './components/footer/footer.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    MatSlideToggleModule,
    NavigationComponent,
    MatProgressSpinnerModule,
    FooterComponent,
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent implements OnInit {
  title = 'Frontend';

  constructor(private authService: AuthService, private router: Router) {}
  ngOnInit(): void {
    this.authService.refreshAuth$.next(true);
    this.authService.refreshAuth$.subscribe(() => {
      this.router.navigate(['/properties']);
    });
  }
}
