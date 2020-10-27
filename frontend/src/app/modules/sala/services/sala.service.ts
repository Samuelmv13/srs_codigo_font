import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { salaModel } from '../models/sala.model';
import { salaEditarModel } from '../models/salaEditar.model';

@Injectable({
  providedIn: 'root'
})
export class SalaService {

  private readonly PATH = environment.apiUrl + "/salas/";

  constructor(
    private http: HttpClient
  ) {}

  listarSalas(){
    return this.http.get<salaModel[]>(this.PATH)
  }

  buscarSalas(id:number){
    return this.http.get<salaModel[]>(this.PATH + "{id}/")
  }

  salvarSala(Sala:salaModel){
    return this.http.post<salaModel[]>(this.PATH, Sala)
  }

  editarSala(Sala: salaEditarModel){
    return this.http.put<salaEditarModel[]>(this.PATH, Sala)
  }

  deletarSala(id:number){
    return this.http.delete(this.PATH + id)
  }
}
