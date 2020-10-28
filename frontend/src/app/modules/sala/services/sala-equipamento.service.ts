import { Injectable } from '@angular/core';
import { InfoEquipamentoModel } from '../../equipamento/models/info-equipamento.model';
import { EquipamentoService } from '../../equipamento/services/equipamento.service';
import { EquipamentoQtdModel } from '../models/equipamento.model';
import { salaEquipamentoModel } from '../models/salaEquipamento.model';

@Injectable({
  providedIn: 'root'
})
export class SalaEquipamentoService {

  equipamentoQtd: EquipamentoQtdModel = {
    id: null,
    nome: null,
    precoDiaria: null,
    idTipoEquipamento: null,
    quantidade: null
  };

  salaEquip: salaEquipamentoModel = {
    idSala: null,
    idEquipamento: null,
    quantidade: null
  };

  constructor(
    private equipamentoService: EquipamentoService 
  ) {
  }

  private limparEquipamentoQtd(){
    this.equipamentoQtd = {
      id: null,
      nome: null,
      precoDiaria: null,
      idTipoEquipamento: null,
      quantidade: null
    };
  }

  private limparSalaEquip(){
    this.salaEquip = {
      idSala: null,
      idEquipamento: null,
      quantidade: null
    };
  }

  public setEquipamento(equipamento: InfoEquipamentoModel){
    this.equipamentoQtd.id = equipamento.id;
    this.equipamentoQtd.nome = equipamento.nome;
    this.equipamentoQtd.precoDiaria = equipamento.precoDiaria;
    this.equipamentoQtd.idTipoEquipamento = equipamento.idTipoEquipamento;
    this.equipamentoQtd.quantidade = 0;
  }

  public getEquipamentoQtd(): EquipamentoQtdModel{
    var equip = this.equipamentoQtd;
    this.limparEquipamentoQtd();
    return equip;
  }

  public setSalaEquipamento(equipamento: EquipamentoQtdModel){
    this.salaEquip.idEquipamento = equipamento.id;
    this.salaEquip.quantidade = equipamento.quantidade;
  }

  public getSalaEquipamento(): salaEquipamentoModel{
    var salaEquip = this.salaEquip;
    this.limparSalaEquip
    return salaEquip;
  }
}
