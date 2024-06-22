import {Component, Input} from '@angular/core';
import {ErrorResponse} from '../../model/ErrorResponse';

@Component({
  selector: 'app-error-page',
  standalone: true,
  imports: [],
  templateUrl: './error-page.component.html',
})
export class ErrorPageComponent {
  @Input()
  error: ErrorResponse | undefined;
}
