import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { Property } from '../../model/property';
import { GuestsDialogComponent } from '../guests-dialog/guests-dialog.component';
@Component({
  selector: 'app-reserve-component',
  standalone: true,
  imports: [
    MatIconModule,
    MatButtonModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    GuestsDialogComponent,
  ],
  templateUrl: './reserve-component.component.html',
  styleUrl: './reserve-component.component.css',
})
export class ReserveComponentComponent {
  @Input()
  property: Property | undefined;
  isOpenGuestsDialog = false;
  numberOfAdults = 1;
  numberOfChildren = 0;
  numberOfPets = 0;

  toggleGuestsDialog(state: boolean) {
    console.log(state);

    this.isOpenGuestsDialog = state;
  }

  countAdults(count: number) {
    this.numberOfAdults = count;
  }
  countChildren(count: number) {
    this.numberOfChildren = count;
  }

  countPets(count: number) {
    this.numberOfPets = count;
  }
}
