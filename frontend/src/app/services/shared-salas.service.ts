import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { SalaModel } from '../shared/model/sala.model';

@Injectable({
  providedIn: 'root'
})
export class SharedSalasService {

  private readonly PATH = environment.apiUrl + "/salas";

  constructor(
    private http: HttpClient
  ) {}

  listarSalas(){
    return this.http.get<SalaModel[]>(this.PATH)
  }
}
