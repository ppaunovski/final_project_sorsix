import { JsonPipe } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { Unit } from '../../model/unit';
import { UnitService } from '../../service/unit.service';

@Component({
  selector: 'app-units',
  standalone: true,
  imports: [JsonPipe],
  templateUrl: './units.component.html',
  styleUrl: './units.component.css',
})
export class UnitsComponent implements OnInit {
  @Input()
  propertyId: Number = 1;
  units: Unit[] = [];

  constructor(private service: UnitService) {}

  ngOnInit(): void {
    this.service.getAllUnitsForProperty(this.propertyId).subscribe((units) => {
      this.units = units;
    });
  }
}
