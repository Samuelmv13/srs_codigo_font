export interface EquipamentoModel {
    id: number,
    nome: string;
    precoDiaria: number;
    idTipoEquipamento: number;
}

export interface EquipamentoQtdModel extends EquipamentoModel{
    quantidade: number;
}