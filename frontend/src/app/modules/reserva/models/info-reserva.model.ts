import { EquipamentoModel } from "src/app/shared/model/equipamento.model";

export interface InfoReservaModel {
    id: number;
    idCliente: number;
    idSala: number;
    dataIni: string;
    dataFim: string;
    total: number;
    equipamentos: EquipamentoModel[];
}