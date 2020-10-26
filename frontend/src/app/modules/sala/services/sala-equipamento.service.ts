import { Injectable } from '@angular/core';
import { InfoEquipamentoModel } from '../../equipamento/models/info-equipamento.model';
import { EquipamentoService } from '../../equipamento/services/equipamento.service';
import { EquipamentoQtdModel } from '../models/equipamento.model';

@Injectable({
  providedIn: 'root'
})
export class SalaEquipamentoService {

  equipamentoQtd: EquipamentoQtdModel;

  constructor(
    private equipamentoService: EquipamentoService 
  ) {
  }

  public getEquipamento(id:number): EquipamentoQtdModel{
    this.equipamentoService.recuperarEquipamento(id).subscribe(
      (equipamento: any) =>{
        this.equipamentoQtd = equipamento;
        this.equipamentoQtd.quantidade = 0;
      }
    )
    return this.equipamentoQtd;
  }
}
