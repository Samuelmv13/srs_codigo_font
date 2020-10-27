import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ClienteModel } from '../shared/model/cliente.model';

@Injectable({
  providedIn: 'root'
})
export class SharedClientesService {

  private readonly PATH = environment.apiUrl + "/clientes";
  
    constructor(
      private http: HttpClient
    ) {}
  
    listarClientes(){
      return this.http.get<ClienteModel[]>(this.PATH)
    }
  }
  