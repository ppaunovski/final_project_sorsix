import {
  AfterViewInit,
  Component,
  EventEmitter,
  Input,
  OnInit,
  Output,
} from '@angular/core';
import * as L from 'leaflet';
import { PropertyInfo } from '../../model/PropertyInfo';
import { PropertyPreviewComponent } from '../property-preview/property-preview.component';
import { DecimalPipe, SlicePipe } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import { RouterLink } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { ImageToUrlService } from '../../service/image-to-url.service';
import { PropertyService } from '../../service/property.service';

@Component({
  selector: 'app-map',
  standalone: true,
  imports: [
    PropertyPreviewComponent,
    MatCardModule,
    RouterLink,
    SlicePipe,
    MatIconModule,
    DecimalPipe,
  ],
  templateUrl: './map.component.html',
  styleUrl: './map.component.css',
})
export class MapComponent implements AfterViewInit, OnInit {
  private map: L.Map | undefined;
  private marker: L.Marker | undefined;
  private userLocation: L.LatLng | undefined;
  private suggestions: L.Marker[] = [];

  tiles: L.TileLayer = L.tileLayer(
    'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
    {
      maxZoom: 18,
      minZoom: 3,
      attribution:
        '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>',
    }
  );

  constructor(
    private urlService: ImageToUrlService,
    private propertyService: PropertyService
  ) {}

  ngOnInit(): void {
    this.propertyService.showMap$.subscribe((x) => {
      if (this.map)
        setTimeout(() => {
          if (this.map) this.map.invalidateSize(true);
          console.log('aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa');
        }, 0);
    });
  }

  ngAfterViewInit(): void {
    // FORMA
    if (
      (this.constCoordinantes === undefined ||
        this.constCoordinantes.lat === 0 ||
        this.constCoordinantes.lng === 0) &&
      this.properties.length == 0
    ) {
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
          if (this.marker) this.marker.on('dragend', () => this.emitEvent());
          this.map.on('click', (event: L.LeafletMouseEvent) => {
            const clickedLocation = event.latlng;
            if (this.marker) this.marker.setLatLng(clickedLocation);
            this.emitEvent();
          });
        });
      } else {
        this.map = L.map('map', {
          center: [41.9947, 21.4299],
          zoom: 10,
        });
        this.tiles.addTo(this.map as L.Map);
        this.marker = L.marker([41.9947, 21.4299], {
          title: 'Smeshtaj Location',
          draggable: true,
          icon: L.icon({
            iconUrl: 'assets/purple-icon.png',
            iconSize: [40, 40],
            iconAnchor: [45, 42],
          }),
        }).addTo(this.map as L.Map);
        if (this.marker) this.marker.on('dragend', () => this.emitEvent());
      }
    }
    // PROPERTY PAGE
    else if (
      this.constCoordinantes != undefined &&
      this.constCoordinantes.lat != 0 &&
      this.constCoordinantes.lng != 0 &&
      this.properties.length == 0
    ) {
      this.map = L.map('map', {
        center: this.constCoordinantes,
        zoom: 15,
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
    // LANDING PAGE
    else {
      this.properties.forEach((p) => {
        this.headerImages[p.id] = this.urlService.bytesToURL(
          p.image.imageByteArray,
          p.image.type
        );
      });
      this.map = L.map('map', {
        center: new L.LatLng(
          this.properties[0].latitude,
          this.properties[0].longitude
        ),
        zoom: 15,
      });
      this.suggestions = this.properties.map((property) => {
        const coord = new L.LatLng(property.latitude, property.longitude);
        const marker = L.marker(coord, {
          title: '',
          draggable: false,
          icon: L.divIcon({
            html:
              '<div class="rounded-full font-semibold" style="width: 4rem; height: 2rem; background-color: white; display: flex; justify-content: center; align-items: center;"><span style="font-size=0.5rem">' +
              property.pricePerNight +
              ' MKD' +
              '</span></div>',
          }),
        })
          .addTo(this.map as L.Map)
          .bindPopup(
            L.popup().setContent(`
        
        <a href="${'/properties/' + property.id}">
        <div class="rounded-lg flex flex-col gap-3">
          <img
            src=${this.headerImages[property.id]}
            alt="img"
            class="rounded-lg h-64 w-full object-cover"
          />
          <div class="p-2">
            <div class="flex justify-between">
              <div class="flex flex-col gap-0 text-ellipsis w-2/3">
                <span
                  class="text-lg font-bold text-ellipsis text-nowrap overflow-x-clip min-w-[150px]"
                  >${property.type}in ${property.cityName} 
                </span>
                <span class="font-light text-gray-800">
                  ${property.address}
                </span>
              </div>
              <div class="flex items-center">
                <mat-icon
                  aria-hidden="false"
                  aria-label="Example home icon"
                  fontIcon="star"
                ></mat-icon>
                <span class="p-2 font-bold text-lg">
                  ${
                    property.averageRating.toString() != 'NaN'
                      ? property.averageRating.valueOf().toPrecision(2)
                      : 'New'
                  }
                </span>
              </div>
            </div>
      
            <p class="text-nowrap overflow-clip text-ellipsis">
              ${property.description.slice(0, 150)}
            </p>
      
            <p>
              <span class="font-bold text-lg">
                <span>
                  ${property.pricePerNight.toString()}
                </span>
                MKD
              </span>
              <span> per night </span>
            </p>
          </div>
        </div>
      </a>
      
        
        `)
          );

        return marker;
      });
      this.tiles.addTo(this.map as L.Map);
    }
  }

  emitEvent() {
    this.changeCoordinates$.emit(this.marker?.getLatLng() as L.LatLng);
  }
  @Output()
  changeCoordinates$ = new EventEmitter<L.LatLng>();
  @Input()
  constCoordinantes: L.LatLng = new L.LatLng(0, 0);
  @Input()
  coordinatesList: L.LatLng[] = [];
  @Input()
  properties: PropertyInfo[] = [];
  headerImages: { [id: number]: string } = {};
}
//recenter button i onclick create marker
