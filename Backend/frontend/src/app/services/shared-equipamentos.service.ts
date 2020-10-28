import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ListarEquipamentoModel } from '../shared/model/listar-equipamento.model';

@Injectable({
  providedIn: 'root'
})
export class SharedEquipamentosService {

  constructor(
    private http: HttpClient
  ) { }

  listarEquipamentos():Observable<ListarEquipamentoModel[]>{
    return this.http.get<ListarEquipamentoModel[]>(`${environment.apiUrl}/equipamentos`);
  }
}
