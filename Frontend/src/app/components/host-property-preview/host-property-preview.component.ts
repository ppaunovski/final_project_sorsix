import {Component, Input, OnInit} from '@angular/core';
import {UserAccount} from '../../model/UserAccount';
import {DateService} from '../../service/date.service';
import {UserImage} from '../../model/UserImage';
import {UserAccountService} from '../../service/user-account.service';
import {ImageToUrlService} from '../../service/image-to-url.service';

@Component({
  selector: 'app-host-property-preview',
  standalone: true,
  imports: [],
  templateUrl: './host-property-preview.component.html',
})
export class HostPropertyPreviewComponent implements OnInit {
  @Input()
  host: UserAccount | undefined;
  hostImage: UserImage | undefined;
  hostImageURL: string = "";

  constructor(private dateService: DateService, private userService: UserAccountService, private imageURLService: ImageToUrlService) {
  }

  ngOnInit(): void {
    if (this.host) {
      this.userService.getProfileImage(this.host?.id).subscribe((image) => {
        this.hostImage = image;
        this.hostImageURL = this.imageURLService.bytesToURL(this.hostImage?.image, this.hostImage?.type);
      });
    }

  }

  getHostDate() {
    if (this.host) {
      return this.dateService.formatDateToMonthYear(this.host.dateHostStarted);
    }
    return '';
  }
}
