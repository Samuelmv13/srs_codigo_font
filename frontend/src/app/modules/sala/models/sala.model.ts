import { salaEquipamentoModel } from "./salaEquipamento.model";


export interface salaModel {
    id : number;
    descricao : string;
    idTipoSala : number;
    capacidadePessoas : number;
    precoDiario : number;
    equipamentos : salaEquipamentoModel[];

}