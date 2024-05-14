import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { PropertyService } from '../../service/property.service';
import { Property } from '../../model/property';
import { PropertyRequest } from '../../model/requests/PropertyRequest';
import { PropertyType } from '../../model/PropertyType';
import { PropertyTypeService } from '../../service/property-type.service';
import { Router } from '@angular/router';
import { PropertyImage } from '../../model/PropertyImage';
import { UserAccountService } from '../../service/user-account.service';
import { UserAccount } from '../../model/UserAccount';
import { City } from '../../model/City';
import { CityService } from '../../service/city.service';
import { MatButtonModule } from '@angular/material/button';
import { AttributeService } from '../../service/attribute.service';
import { Attribute } from '../../model/Attribute';
import {MatCheckboxChange, MatCheckboxModule} from '@angular/material/checkbox';



@Component({
  selector: 'app-property-form',
  standalone: true,
  imports: [ReactiveFormsModule,
    MatButtonModule,
    MatCheckboxModule,
  ],
  templateUrl: './property-form.component.html',
  styleUrl: './property-form.component.css',
})
export class PropertyFormComponent implements OnInit {

 

  constructor(private typeService: PropertyTypeService, 
    private propertyService: PropertyService, 
    private router: Router,
    private userService: UserAccountService,
    private cityService: CityService,
    private propertyTypeService: PropertyTypeService,
    private attributeService: AttributeService,
  ) { }
  form: FormGroup = new FormGroup<PropertyRequest>({
    nightlyPrice: new FormControl<Number>(0,{nonNullable: true}),
    name: new FormControl<string>('',{nonNullable: true}),
    guests: new FormControl<Number>(0,{nonNullable: true}),
    beds: new FormControl<Number>(0,{nonNullable: true}),
    bedrooms: new FormControl<Number>(0,{nonNullable: true}),
    bathrooms: new FormControl<Number>(0,{nonNullable: true}),
    isGuestFavorite: new FormControl<Boolean>(false,{nonNullable: true}),
    description: new FormControl<string>('',{nonNullable: true}),
    address: new FormControl<string>('',  {nonNullable: true}),
    longitude: new FormControl<Number>(0,{nonNullable: true}),
    latitude: new FormControl<Number>(0,{nonNullable: true}),
    city: new FormControl<City>({} as City,{nonNullable: true}),
    propertyType: new FormControl<PropertyType>({} as PropertyType,{nonNullable: true}),
  });
  propertyForm: any;
  propertyTypes: PropertyType[] = [];
  images: File[] = [];
  propertyImages: PropertyImage[] = [];
  user: UserAccount | undefined;
  cities: City[] = [];
  attributes: Attribute[] = [];
  

  ngOnInit(): void {
    this.typeService.getAllPropertyTypes().subscribe((types) => {
      this.propertyTypes = types;
    });
    this.userService.getUserInfo().subscribe((u) => {
      this.user = u;
      console.log(u);
    });
    this.cityService.getAllCities().subscribe((cities) => {
      this.cities = cities;
    });
    this.propertyTypeService.getAllPropertyTypes().subscribe((types) => {
      this.propertyTypes = types;
    });
    this.attributeService.getAllAttributes().subscribe((att) => {
      this.attributes = att;
      console.log(this.attributes);
    });

  }
  onImagePicked(event: Event) {
    const target = event.target as HTMLInputElement;
    const files = target.files as FileList;
    for (let i = 0; i < files.length; i++) {
      this.images.push(files[i]);
    }
    for (let i = 0; i < this.images.length; i++) {
      const reader = new FileReader();
      reader.onload = (e) => {
        const base64 = e.target?.result;
        const bytes = base64?.toString().split(',')[1];
        const image: PropertyImage = {
          id: 0,
          imageByteArray: bytes??'',
          type: this.images[i].type,
          order: i,
          propertyId: 0,
        };
        this.propertyImages.push(image);
      };
      reader.readAsDataURL(this.images[i]);
    }

  }
  onCheckboxChange($event: MatCheckboxChange) {
    
    }
  
private OnResposne ={
  next : (result: Property | undefined) => {
    this.router.navigate(['/properties', result?.id]);
 },
  error : (error: any) => {
    console.log(error);
  }
}
  handleSubmit() {
    console.log('submitting form');
    if(this.user === undefined){
      return;
    }
    this.form.value.city = this.cities.find((c) => c.id == this.form.value.city);
    this.form.value.propertyType = this.propertyTypes.find((t) => t.id == this.form.value.propertyType);
    console.log("Final Form: ",this.form.value);
   this.propertyService.createProperty(this.form.value, this.propertyImages).subscribe(this.OnResposne);
  }
}
