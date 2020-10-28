import { Validators } from "@angular/forms";

export interface CadastrarReservaModel {
    dataIni: String ;
    dataFim: String;
    total: number;
    idSala: number;
    idCliente: number;
}