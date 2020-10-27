import { EquipamentoModel } from "./equipamento.model";

export class ReservaModel{
    id: number;
    dataIni: String;
    dataFim: String;
    total: number;
    equipamentos: EquipamentoModel[];
}