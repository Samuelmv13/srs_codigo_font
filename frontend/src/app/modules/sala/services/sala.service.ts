import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { salaModel } from '../models/sala.model';
import { salaEditarModel } from '../models/salaEditar.model';

@Injectable({
  providedIn: 'root'
})
export class SalaService {

  constructor(
    private http: HttpClient
  ) {}

  httpOptions = {
    headers: new HttpHeaders({
      'Content-type': 'application/json'
    })
  }

  listarSalas(){
    return this.http.get<salaModel[]>(`${environment.apiUrl}/salas`)
  }
  
  salvarSala(Sala:salaModel){
    return this.http.post<salaModel>(`${environment.apiUrl}/salas`, JSON.stringify(Sala), this.httpOptions)
  }

  buscarSalas(id:number){
    return this.http.get<salaModel[]>(`${environment.apiUrl}/salas/${id}`)
  }

  editarSala(Sala: salaEditarModel){
    return this.http.put<salaEditarModel[]>(`${environment.apiUrl}/salas`, JSON.stringify(Sala), this.httpOptions)
  }

  deletarSala(id:number){
    return this.http.get(`${environment.apiUrl}/salas/${id}`)
  }
}
