import { Injectable } from '@angular/core';
import { InfoEquipamentoModel } from '../../equipamento/models/info-equipamento.model';
import { EquipamentoService } from '../../equipamento/services/equipamento.service';
import { EquipamentoQtdModel } from '../models/equipamento.model';

@Injectable({
  providedIn: 'root'
})
export class SalaEquipamentoService {

  equipamentoQtd: EquipamentoQtdModel = null;

  constructor(
    private equipamentoService: EquipamentoService 
  ) {
  }

  public setEquipamento(id:number): any{
    this.equipamentoService.recuperarEquipamento(id).subscribe(
      (equipamento: any) =>{
        this.equipamentoQtd = equipamento;        
      }
      )
  }

  getEquipamentoQtd(){
    return this.equipamentoQtd;
  }
}
