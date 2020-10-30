import { EquipamentoModel } from "./equipamento.model";

export interface CadastrarReservaModel {
    dataIni: String ;
    dataFim: String;
    total: number;
    idCliente: number;
    idSala: number;
    equipamentos: EquipamentoModel[];
}