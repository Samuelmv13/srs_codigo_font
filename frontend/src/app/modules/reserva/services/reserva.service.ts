import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, of } from "rxjs";
import { environment } from "src/environments/environment";
import { CadastrarReservaModel } from "../models/cadastrar-reserva.model";
import { EditarReservaModel } from "../models/editar-reserva.model";
import { InfoReservaModel } from "../models/info-reserva.model";
import { ListarReservaModel } from "../models/listar-reserva.model";

@Injectable({
  providedIn: 'root'
})
export class ReservaService {

  constructor(
    private http: HttpClient
  ) { }

  listarReservas(): Observable<ListarReservaModel[]> {
    // return of<ListarReservaModel[]>([{
    // id:1,
    // idCliente:1,
    // idSala:2,
    // dataIni:"15/05/2020",
    // dataFim:"20/05/2020",
    // total:500
    // },
    // {
    //   id:2,
    //   idCliente:1,
    //   idSala:1,
    //   dataIni:"22/06/2020",
    //   dataFim:"17/07/2020",
    //   total:20
    //   },
    //   {
    //     id:3,
    //     idCliente:2,
    //     idSala:3,
    //     dataIni:"23/06/2020",
    //     dataFim:"29/11/2020",
    //     total:2000
    //     }])
    return this.http.get<ListarReservaModel[]>(`${environment.apiUrl}/reservas`);
  }

  cadastrarReserva(cadastroReserva: CadastrarReservaModel): Observable<InfoReservaModel> {
    return of<any>(true);
    return this.http.post<InfoReservaModel>(`${environment.apiUrl}/reservas`, cadastroReserva);
  }

  editarReserva(editarReservaModel: EditarReservaModel): Observable<InfoReservaModel> {
    return of<any>(true);
    return this.http.put<InfoReservaModel>(`${environment.apiUrl}/reservas`, editarReservaModel);
  }


  recuperarReserva(id: number): Observable<InfoReservaModel> {
    return of<InfoReservaModel>({
      id,
      dataIni:"25/09/2020",
      dataFim: "28/09/2020",
      total:232
    })
    return this.http.get<InfoReservaModel>(`${environment.apiUrl}/reservas/${id}`);
  }

  removerReserva(id: number): Observable<any> {
    return this.http.delete<any>(`${environment.apiUrl}/reservas/${id}`);
  }

}
