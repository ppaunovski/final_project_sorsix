import { Component, Input } from '@angular/core';
import { ErrorResponse } from '../../model/ErrorResponse';

@Component({
  selector: 'app-error-page',
  standalone: true,
  imports: [],
  templateUrl: './error-page.component.html',
  styleUrl: './error-page.component.css',
})
export class ErrorPageComponent {
  @Input()
  error: ErrorResponse | undefined;
}
