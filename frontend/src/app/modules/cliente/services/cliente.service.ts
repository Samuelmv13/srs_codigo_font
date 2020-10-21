import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CadastrarClienteModel } from '../models/cadastrar-cliente.model';
import { ListarClienteModel } from '../models/listar-cliente.model';
import { InfoClienteModel } from '../models/info-cliente.model';
import { EditarClienteModel } from '../models/editar-cliente.model';
@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  constructor(
    private http: HttpClient
  ) { }

  listarClientes(): Observable<ListarClienteModel[]> {
    return of<ListarClienteModel[]>([
      {
        id: 1,
        nome: 'Teste 1',
        idade: 23
      },
      {
        id: 2,
        idade: 35,
        nome: 'Teste 2'
      }
    ]);
    return this.http.get<ListarClienteModel[]>(`${environment.apiUrl}/clientes`);
  }

  cadastrarCliente(cadastroCliente: CadastrarClienteModel): Observable<InfoClienteModel> {
    return of<any>(true);
    return this.http.post<InfoClienteModel>(`${environment.apiUrl}/clientes`, cadastroCliente);
  }

  editarCliente(editarClienteModel: EditarClienteModel): Observable<InfoClienteModel> {
    return of<any>(true);
    return this.http.put<InfoClienteModel>(`${environment.apiUrl}/clientes`, editarClienteModel);
  }


  recuperarCliente(id: number): Observable<InfoClienteModel> {
    return of<InfoClienteModel>({
      id,
      idade: 23,
      nome: 'Usuario Recuperado'
    })
    return this.http.get<InfoClienteModel>(`${environment.apiUrl}/clientes/${id}`);
  }

}
