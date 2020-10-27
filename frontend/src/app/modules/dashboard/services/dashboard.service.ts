import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ListarClientesModel } from '../models/listar-clientes.model';
import { ListarEquipamentoModel } from '../models/listar-equipamento.model';
import { ListarReservaModel } from '../models/listar-reserva.model';
import { ListarSalaModel } from '../models/listar-sala.model';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {  

  constructor(
    private http: HttpClient
  ) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-type': 'application/json'
    })
  }
    
  listarClientes(): Observable<ListarClientesModel[]> {
    return this.http.get<ListarClientesModel[]>(`${environment.apiUrl}/clientes`);
  }

  listarSalas(): Observable<ListarSalaModel[]> {
    return this.http.get<ListarSalaModel[]>(`${environment.apiUrl}/salas`);
  }

  listarEquipamentos(): Observable<ListarEquipamentoModel[]> {
    return of<ListarEquipamentoModel[]>([{
      id: 1,
      nome: "Geladeira",
      precoDiaria: 290.90,
      idTipoEquipamento: 2
    },
    {
      id: 2,
      nome: "NoteBook",
      precoDiaria: 590.90,
      idTipoEquipamento: 3
    }]);
    return this.http.get<ListarEquipamentoModel[]>(`${environment.apiUrl}/equipamentos`);
  }

  listarReservas(): Observable<ListarReservaModel[]> {
    return of<ListarReservaModel[]>([{
      id:1,
      idCliente:1,
      idSala:2,
      dataIni:"15/05/2020",
      dataFim:"20/05/2020",
      total:500
      },
      {
        id:2,
        idCliente:1,
        idSala:1,
        dataIni:"22/06/2020",
        dataFim:"17/07/2020",
        total:20
        },
        {
          id:3,
          idCliente:2,
          idSala:3,
          dataIni:"23/06/2020",
          dataFim:"29/11/2020",
          total:2000
          }])
      // return this.http.get<ListarReservaModel[]>(`${environment.apiUrl}/reservas`);
    
  }
  
  
}
