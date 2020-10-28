import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { of } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ListarReservaModel } from '../modules/reserva/models/listar-reserva.model';
import { ClienteModel } from '../shared/model/cliente.model';
import { ListarEquipamentoModel } from '../shared/model/listar-equipamento.model';
import { SalaModel } from '../shared/model/sala.model';

@Injectable({
  providedIn: 'root'
})
export class SharedDashboardService {

  private readonly PATH = environment.apiUrl;


  constructor(
    private http: HttpClient
  ) { }


  listarReservas() {
    return this.http.get<ListarReservaModel[]>(this.PATH + "/reservas");
  }
  listarSalas() {
    return this.http.get<SalaModel[]>(this.PATH + "/salas");
  }
  listarEquipamentos() {
    return this.http.get<ListarEquipamentoModel[]>(this.PATH + "/equipamntos");
  }
  listarClientes() {
    return this.http.get<ClienteModel[]>(this.PATH + "/clientes");
  }
}
