
import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { Utente } from '../model/Utente';
import { StorageService } from '../service/storage.service';
import { ProfileService } from '../service/profile-service.service';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [MatButtonModule,
    MatCardModule,
    MatIconModule,
    RouterLink
  ],
  templateUrl: './profilo.component.html',
  styleUrl: './profilo.component.css'
})
export class ProfileComponent implements OnInit {

  constructor(private profiloService: ProfileService, private storageService: StorageService, private router: Router) { }

  utente: Utente = new Utente(0, "", "", "", "");

  ngOnInit(): void {
    console.log(this.storageService.getProperty('user_email'))
    this.profiloService.getProfilo(this.storageService.getProperty('user_email'))?.subscribe((res: Utente) => {
      console.log(res)
      this.utente = res;
    })
  }
  //subscribe(id: number) {
  //  let updateCorsi = this.utente.corsi;
  //  this.corsiService.getCorso(id).subscribe((res: Corso) => {
  //    updateCorsi.push(res)

  //    const utenteUpdate = new UtenteUpdate(
  //      this.utente.nome,
  //      this.utente.cognome,
  //      this.utente.email,
  //      this.utente.password,
  //      updateCorsi,
  //      this.utente.ruoli
  //    );
  //    this.profiloService.updateProfilo(utenteUpdate)?.subscribe((res) => {
  //      window.location.reload();
  //    })
  //  })
  //}

  //unsubscribe(id: number) {
  //  let updateCorsi = this.utente.corsi;
  //  updateCorsi = this.utente.corsi.filter(corso => corso.id !== id)
  //  const utenteUpdate = new UtenteUpdate(
  //    this.utente.nome,
  //    this.utente.cognome,
  //    this.utente.email,
  //    this.utente.password,
  //    updateCorsi,
  //    this.utente.ruoli
  //  );
  //  this.profiloService.updateProfilo(utenteUpdate)?.subscribe((res) => {
  //    window.location.reload();
  //  })
  //}
  logout() {
    this.storageService.deleteProperty('user_email');
    this.storageService.deleteLocalToken();
    this.router.navigate(["/"]);
  }
}
