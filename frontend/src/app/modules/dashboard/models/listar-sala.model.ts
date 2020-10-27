import { equipamentoModel } from "../../sala/models/equipamento.model";

export interface ListarSalaModel {
    id : number;
    descricao : string;
    idTipoSala : number;
    capacidadePessoas : number;
    precoDiario : number;
    disponivel : number;
    equipamentos : equipamentoModel;
}