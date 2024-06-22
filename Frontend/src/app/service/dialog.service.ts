import {ElementRef, Injectable} from '@angular/core';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';
import {GuestsDialogComponent} from '../components/guests-dialog/guests-dialog.component';

@Injectable({
  providedIn: 'root',
})
export class DialogService {
  constructor(public dialog: MatDialog) {
  }

  public openDialog({
                      positionRelativeToElement,
                      hasBackdrop = false,
                      height = '135px',
                      width = '290px',
                    }: {
    positionRelativeToElement: ElementRef;
    hasBackdrop?: boolean;
    height?: string;
    width?: string;
  }): MatDialogRef<GuestsDialogComponent> {
    const dialogRef: MatDialogRef<GuestsDialogComponent> = this.dialog.open(
      GuestsDialogComponent,
      {
        hasBackdrop: hasBackdrop,
        height: height,
        width: width,
        data: {positionRelativeToElement: positionRelativeToElement},
      }
    );
    return dialogRef;
  }
}
