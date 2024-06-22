import {Component, Input, OnInit} from '@angular/core';
import {PropertyImage} from '../../model/PropertyImage';
import {ImageService} from '../../service/property-image.service';
import {ImageToUrlService} from '../../service/image-to-url.service';
import {MatButtonModule} from '@angular/material/button';
import {ActivatedRoute, Router} from '@angular/router';
import {filter, map, mergeMap} from 'rxjs';

@Component({
  selector: 'app-image-gallery',
  standalone: true,
  imports: [MatButtonModule],
  templateUrl: './image-gallery.component.html',
  styleUrl: './image-gallery.component.css',
})
export class ImageGalleryComponent implements OnInit {
  @Input()
  images: PropertyImage[] = [];
  @Input()
  propertyId: number | undefined;

  pageView = false;

  imageUrls: string[] = [];

  constructor(
    private imageService: ImageToUrlService,
    private router: Router,
    private route: ActivatedRoute,
    private service: ImageService
  ) {
  }

  ngOnInit(): void {
    this.route.queryParamMap
      .pipe(
        filter((param) => param.has('page')),
        mergeMap(() => this.route.paramMap)
      )
      .pipe(
        filter((param) => param.has('id')),
        map((param) => param.get('id')!),
        mergeMap((id) => this.service.getAllImagesByPropertyId(+id))
      )
      .subscribe({
        next: (resp) => {
          this.pageView = true;
          this.images = resp;
          this.images.forEach((img) => {
            this.imageUrls.push(this.imageService.dataURItoBlob(img));
          });
          console.log(resp);
        },
      });

    this.images.forEach((img) => {
      this.imageUrls.push(this.imageService.dataURItoBlob(img));
    });

    while (this.imageUrls.length < 5) {
      this.imageUrls.push(
        this.imageService.dataURItoBlob(
          this.images[Math.floor(Math.random() * this.images.length)]
        )
      );
    }
  }

  openAllImages() {
    if (this.propertyId)
      this.router.navigate(['/properties', this.propertyId, 'images'], {
        queryParams: {
          page: true,
        },
      });
  }
}
