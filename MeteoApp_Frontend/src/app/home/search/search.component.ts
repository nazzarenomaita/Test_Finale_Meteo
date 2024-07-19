import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';

import { NgFor, NgIf } from '@angular/common';
import { CommonModule } from '@angular/common';
import { map } from 'rxjs';
import { NominatimService } from '../../service/nominatin.service';
import { OpenMeteoService } from '../../service/openmeteo.service';
import { AuthService } from '../../service/auth-service.service';
import { Utente } from '../../model/Utente';
import { StorageService } from '../../service/storage.service';
import { ProfileService } from '../../service/profile-service.service';
import { MeteoService } from '../../service/meteoservice.service';
import { Meteo } from '../../model/Meteo';

@Component({
  selector: 'app-search',
  standalone: true,
  imports: [
    CommonModule,
    NgIf,
    NgFor,
    FormsModule,
    MatInputModule,
    MatFormFieldModule,
    MatButtonModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatCardModule,
    MatIconModule
  ],

  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  location: string = '';
  locations: any[] = [];
  selectedLocation: any;
  selectedDate: Date = new Date();
  weather: any;
  isLogged: boolean = false;
  meteo: Meteo = new Meteo("", new Date(), 0, 0, 0);

  constructor(
    private nominatimService: NominatimService,
    private openMeteoService: OpenMeteoService,
    private authService: AuthService,
    private storageService: StorageService,
    private profiloService: ProfileService,
    private meteoService: MeteoService
  ) { }

  ngOnInit(): void {
    this.authService.isAuthenticated().subscribe(isAuth => {
      if (isAuth) {
        this.isLogged=true;
      } else {
        this.isLogged = false;
      }
    });
   }

  onLocationInput(event: any) {
    const query = event.target.value;
    if (query.length > 0) {
      this.nominatimService.search(query).subscribe(data => {
        this.locations = [];
        this.locations = data;
      });
    } else {
      this.locations = [];
    }
  }

  onLocationSelect(location: any) {
    this.selectedLocation = location;
    this.location = location.display_name;
    this.locations = [];
  }

  onSearch() {
    const date = this.selectedDate.toISOString().split('T')[0];
    this.openMeteoService.getWeather(this.selectedLocation.lat, this.selectedLocation.lon, date)
      .subscribe(data => {
        console.log(data)
        this.weather = data.daily;
      });
  }
  save() {
    this.profiloService.getProfilo(this.storageService.getProperty('user_email'))?.subscribe((res: Utente) => {
      this.meteo = new Meteo(this.selectedLocation.display_name, this.weather.time[0], this.weather.temperature_2m_max[0], this.weather.temperature_2m_min[0], res.id);
      console.log(this.meteo)
      this.meteoService.save(this.meteo)!.subscribe(res => {
        console.log(res)
      })
    })

  }
  
}