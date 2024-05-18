import { Component, Inject, Input, OnInit } from '@angular/core';
import { Review } from '../../model/Review';
import { ReviewWithComponents } from '../../model/ReviewWIthComponents';
import { MatIconModule } from '@angular/material/icon';
import { SlicePipe } from '@angular/common';
import {
  MatDialog,
  MAT_DIALOG_DATA,
  MatDialogRef,
  MatDialogTitle,
  MatDialogContent,
  MatDialogActions,
  MatDialogClose,
} from '@angular/material/dialog';
import { ReviewService } from '../../service/review.service';
import { ComponentRating } from '../../model/ComponentRating';
import { MatSlider } from '@angular/material/slider';
import { UserAccountService } from '../../service/user-account.service';
import { ImageToUrlService } from '../../service/image-to-url.service';
import { DialogData } from '../../model/DialogData';
import { DateService } from '../../service/date.service';

@Component({
  selector: 'app-review',
  standalone: true,
  imports: [MatIconModule, SlicePipe, MatIconModule],
  templateUrl: './review.component.html',
  styleUrl: './review.component.css',
})
export class ReviewComponent implements OnInit {
  @Input()
  review: Review | undefined;

  components: ComponentRating[] = [];

  hostImageURL: string = '';

  constructor(
    public dialog: MatDialog,
    private reviewService: ReviewService,
    private userService: UserAccountService,
    private urlService: ImageToUrlService,
    private dateService: DateService
  ) {}
  ngOnInit(): void {
    if (this.review) {
      this.reviewService
        .getComponentRatingsForReview(this.review?.id)
        .subscribe((c) => {
          this.components = c;
        });
      this.userService
        .getProfileImage(this.review?.user.id)
        .subscribe((image) => {
          this.hostImageURL = this.urlService.bytesToURL(
            image.image,
            image.type
          );
        });
    }
  }

  openDialog() {
    if (this.review) {
      const fullReview: ReviewWithComponents = {
        review: this.review,
        components: this.components,
      };
      const dialog = {
        fullReview: fullReview,
        hostImageURL: this.hostImageURL,
        formatedDate: this.dateService.formatDateToMonthYear(
          this.review.reviewDate
        ),
      };
      const dialogRef = this.dialog.open(ReviewDialog, {
        data: dialog,
      });
    }
  }
  getFormatedDate() {
    if (this.review)
      return this.dateService.formatDateToMonthYear(this.review.reviewDate);
    return '';
  }
}

@Component({
  selector: 'review-dialog',
  templateUrl: 'dialog.review.html',
  standalone: true,
  imports: [
    MatDialogTitle,
    MatDialogContent,
    MatIconModule,
    MatDialogActions,
    MatDialogClose,
    SlicePipe,
    MatSlider,
  ],
})
export class ReviewDialog {
  constructor(@Inject(MAT_DIALOG_DATA) public dialog: DialogData) {}
}
