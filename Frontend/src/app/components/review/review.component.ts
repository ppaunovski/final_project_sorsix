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

  constructor(public dialog: MatDialog, private reviewService: ReviewService) {}
  ngOnInit(): void {
    if (this.review)
      this.reviewService
        .getComponentRatingsForReview(this.review?.id)
        .subscribe((c) => {
          this.components = c;
        });
  }

  openDialog() {
    if (this.review) {
      const fullReview: ReviewWithComponents = {
        review: this.review,
        components: this.components,
      };
      const dialogRef = this.dialog.open(ReviewDialog, {
        data: fullReview,
      });
    }
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
  constructor(
    @Inject(MAT_DIALOG_DATA) public fullReview: ReviewWithComponents
  ) {}
}
