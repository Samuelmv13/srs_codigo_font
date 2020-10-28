import { EquipamentoModel } from "src/app/shared/model/equipamento.model";

export interface ListarSalaModel {
    id : number;
    descricao : string;
    idTipoSala : number;
    capacidadePessoas : number;
    precoDiario : number;
    disponivel : number;
    equipamentos : EquipamentoModel;
}