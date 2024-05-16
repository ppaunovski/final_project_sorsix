import {
  AfterViewInit,
  Component,
  EventEmitter,
  Input,
  Output,
} from '@angular/core';
import * as L from 'leaflet';

@Component({
  selector: 'app-map',
  standalone: true,
  imports: [],
  templateUrl: './map.component.html',
  styleUrl: './map.component.css',
})
export class MapComponent implements AfterViewInit {
  private map: L.Map | undefined;
  private marker: L.Marker | undefined;
  private userLocation: L.LatLng | undefined;

  tiles: L.TileLayer = L.tileLayer(
    'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
    {
      maxZoom: 18,
      minZoom: 3,
      attribution:
        '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>',
    }
  );

  ngAfterViewInit(): void {
    if (this.constCoordinantes === undefined || this.constCoordinantes.lat === 0 || this.constCoordinantes.lng === 0) {
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition((position) => {
          this.userLocation = new L.LatLng(
            position.coords.latitude,
            position.coords.longitude
          );
          this.map = L.map('map', {
            center: this.userLocation,
            zoom: 10,
          });
          this.tiles.addTo(this.map as L.Map);
          this.marker = L.marker(this.userLocation, {
            title: 'Smeshtaj Location',
            draggable: true,
            icon: L.icon({
              iconUrl: 'assets/purple-icon.png',
              iconSize: [40, 40],
            }),
          }).addTo(this.map as L.Map);
          if (this.marker) 
            this.marker.on('dragend', () => this.emitEvent());
        });
      }
      else{
        this.map = L.map('map',{
          center: [41.9947,21.4299],
            zoom: 10,
        });
        this.tiles.addTo(this.map as L.Map);
        this.marker = L.marker([41.9947,21.4299], {
          title: 'Smeshtaj Location',
          draggable: true,
          icon: L.icon({
            iconUrl: 'assets/purple-icon.png',
            iconSize: [40, 40],
          }),
        }).addTo(this.map as L.Map);
        if (this.marker) 
          this.marker.on('dragend', () => this.emitEvent());
      }
    }
    else{
      this.map = L.map('map', {
        center: this.constCoordinantes,
        zoom: 13,
      });
      this.tiles.addTo(this.map as L.Map);
      this.marker = L.marker(this.constCoordinantes, {
        title: 'Smeshtaj Location',
        draggable: false,
        icon: L.icon({
          iconUrl: 'assets/purple-icon.png',
          iconSize: [40, 40],
        }),
      }).addTo(this.map as L.Map);
    }
  }
  
  emitEvent() {
    this.changeCoordinates$.emit(this.marker?.getLatLng() as L.LatLng);
  }
  @Output()
  changeCoordinates$ = new EventEmitter<L.LatLng>();
  @Input()
  constCoordinantes: L.LatLng = new L.LatLng(0, 0);
}
