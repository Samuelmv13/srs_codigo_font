import { equipamentoModel } from "./equipamento.model";

export interface salaEditarModel {
    id: number;
    descricao : string;
    idTipoSala : number;
    capacidadePessoas : number;
    precoDiario : number;
    disponivel : number;
    equipamentos : equipamentoModel;
}