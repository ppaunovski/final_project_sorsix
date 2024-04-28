import { Component, Input } from '@angular/core';
import { UserAccount } from '../../model/UserAccount';

@Component({
  selector: 'app-host-property-preview',
  standalone: true,
  imports: [],
  templateUrl: './host-property-preview.component.html',
  styleUrl: './host-property-preview.component.css',
})
export class HostPropertyPreviewComponent {
  @Input()
  host: UserAccount | undefined;
}
