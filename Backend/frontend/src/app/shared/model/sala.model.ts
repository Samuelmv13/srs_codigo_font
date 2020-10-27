import { EquipamentoModel } from './equipamento.model';
export class SalaModel{
    id: number;
    descricao: String;
    idTipoSala: number;
    capacidadePessoa: number;
    precoDiario: number;
    disponivel: number;
    equipamentos: EquipamentoModel[];
}