import { salaEquipamentoModel } from "./salaEquipamento.model";


export interface salaEditarModel {
    id: number;
    descricao : string;
    idTipoSala : number;
    capacidadePessoas : number;
    precoDiario : number;
    disponivel : number;
    equipamentos : salaEquipamentoModel[];
}