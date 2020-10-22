import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ListarClientesModel } from '../models/listar-clientes.model';
import { environment } from 'src/environments/environment';
import { InfoClienteModel } from '../models/info-cliente.model';
import { CadastrarClienteModel } from '../models/cadastrar-cliente.model';
import { EditarClienteModel } from '../models/editar-cliente.model';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {  

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
  
  cadastrarClientes(cadastroCliente: CadastrarClienteModel): Observable<InfoClienteModel> {
    return this.http.post<InfoClienteModel>(`${environment.apiUrl}/clientes`, JSON.stringify(cadastroCliente), this.httpOptions);
  }

  buscarClientes(id:number): Observable<ListarClientesModel[]> {
    return this.http.get<ListarClientesModel[]>(`${environment.apiUrl}/clientes/${id}`);
  }

  editarClientes(editarCliente: EditarClienteModel): Observable<InfoClienteModel[]> {
    return this.http.put<InfoClienteModel[]>(`${environment.apiUrl}/clientes`, JSON.stringify(editarCliente), this.httpOptions);
  }

  deletarClientes(id:number){
    return this.http.delete<ListarClientesModel[]>(`${environment.apiUrl}/clientes/${id}`);
  }

  recuperarCliente(id: number): Observable<InfoClienteModel>{
    return this.http.get<InfoClienteModel>(`${environment.apiUrl}/clientes/${id}`)
  }
}
