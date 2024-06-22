import {Injectable} from '@angular/core';
import {PropertyImage} from '../model/PropertyImage';

@Injectable({
  providedIn: 'root',
})
export class ImageToUrlService {
  constructor() {
  }

  dataURItoBlob(image: PropertyImage): string {
    const dataURI = image.imageByteArray;
    const type = image.type;

    const byteString = window.atob(dataURI);
    const ab = new ArrayBuffer(byteString.length);
    const ia = new Uint8Array(ab);
    for (let i = 0; i < byteString.length; i++) {
      ia[i] = byteString.charCodeAt(i);
    }
    var blob = new Blob([ab], {type: type});
    return URL.createObjectURL(blob);
  }

  bytesToURL(dataURI: any, type: any): string {
    const byteString = window.atob(dataURI);
    const ab = new ArrayBuffer(byteString.length);
    const ia = new Uint8Array(ab);
    for (let i = 0; i < byteString.length; i++) {
      ia[i] = byteString.charCodeAt(i);
    }
    var blob = new Blob([ab], {type: type});
    return URL.createObjectURL(blob);
  }
}
