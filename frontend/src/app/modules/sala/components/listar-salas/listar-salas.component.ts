import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { MessageService } from 'primeng/api';
import { InfoEquipamentoModel } from 'src/app/modules/equipamento/models/info-equipamento.model';
import { ListarEquipamentoModel } from 'src/app/modules/equipamento/models/listar-equipamento.model';
import { EquipamentoService } from 'src/app/modules/equipamento/services/equipamento.service';
import { EquipamentoQtdModel } from '../../models/equipamento.model';
import { salaModel } from '../../models/sala.model';
import { salaEquipamentoModel } from '../../models/salaEquipamento.model';
import { SalaEquipamentoService } from '../../services/sala-equipamento.service';
import { SalaService } from '../../services/sala.service';

@Component({
  selector: 'app-listar-salas',
  templateUrl: './listar-salas.component.html',
  styleUrls: ['./listar-salas.component.css'],
  providers: [MessageService],
})
export class ListarSalasComponent implements OnInit {

  listaSalaDialog: boolean;

  selecionarSalaEquipamentoDialog: boolean;

  selectedTipo: any;
  
  equipamentoDialog: boolean;
  
  listaSalas: salaModel[];
  
  listaEquipamentosQtd: EquipamentoQtdModel[];
  
  listaSalaEquip: salaEquipamentoModel[];
  
  sala: salaModel;
  
  salaSelected: salaModel;
  
  salaSelectedEquips: EquipamentoQtdModel[];
  
  displayForm = false;
  
  salaForm: FormGroup;
  
  editEquipamentoDialog: boolean;

  editEquipsSala: EquipamentoQtdModel[];

  editSalaEquips: salaEquipamentoModel[];

  equipEdit: salaEquipamentoModel;

  idRemover: number;

  displayRemover: boolean;

  editQuant: number;

  constructor(
    private salaServices: SalaService,
    private equipamentoService: EquipamentoService,
    private formBuilder: FormBuilder,
    private salaEquipamentoService: SalaEquipamentoService,
    private messageService: MessageService,
  ) { }

  ngOnInit(): void {
    this.listaSalas = [];
    this.listaSalaEquip = [];
    this.iniciarSala();
    this.listar();
  }

  iniciarSala(){
    this.sala = {
      id: null,
      descricao: null,
      capacidadePessoas: null,
      idTipoSala: null,
      precoDiario: null,
      equipamentos: null
    }
  }

  getTipo(n: number) {
    switch (n) {
      case 1:
        return "Reunião";
      case 2:
        return "Trabalho";
      case 3:
        return "Vídeo";
      case 4:
        return "Palestra";
      case 5:
        return "Auditório";
      default:
        return "Selecionar Tipo";
    }
  }
  selecionarTipo() {
    this.selectedTipo = [
      { label: 'Selecionar ', value: null },
      { label: 'Reunião', value: '1' },
      { label: 'Trabalho', value: '2' },
      { label: 'Vídeo', value: '3' },
      { label: 'Palestra', value: '4' },
      { label: 'Auditório', value: '5' }];
    return this.selectedTipo;
  }

  getTipoEquipamento(n: number) {
    switch (n) {
      case 1:
        return "Móvel";
      case 2:
        return "Eletrodoméstico";
      case 3:
        return "Informática";
    }
  }

  // Exibir equipamentos da sala (button equipamentos)
  setEquipSelected(){
    let equips = null;
    this.salaSelectedEquips = [];
      for (let equip of this.salaSelected.equipamentos){
        this.equipamentoService.recuperarEquipamento(equip.idEquipamento).subscribe(
        (equipamento: InfoEquipamentoModel) =>{
          equips = equipamento;
          this.salaEquipamentoService.setEquipamento(equips, equip.quantidade);
          let equipQtd = this.salaEquipamentoService.getEquipamentoQtd();
          this.salaSelectedEquips.push(equipQtd);
        });
    };
  }

  listar() {
    this.salaServices.listarSalas()
      .subscribe(listaSalas => {
        this.listaSalas = listaSalas;        
      });
  }
  
  // Set todos os equips com o tipo da relação sala-equipamento (cadastro)
  listarEquipamentosSala(){
    this.listaEquipamentosQtd= [];
    this.equipamentoService.listarEquipamentos().subscribe(
      (listaEquipamentos: ListarEquipamentoModel[]) => {
        for (let equip of listaEquipamentos){
          this.salaEquipamentoService.setEquipamento(equip, 0);
          this.listaEquipamentosQtd.push(this.salaEquipamentoService.getEquipamentoQtd());
        }
      });
  }

  isEditar(): boolean {
    return this.sala != null;
  }

  // Add equipamento na sala (cadastro)
  addSalaEquip(equip: EquipamentoQtdModel){
    this.salaEquipamentoService.setSalaEquipamento(equip);
    var salaEquip = this.salaEquipamentoService.getSalaEquipamento();
    this.listaSalaEquip.push(salaEquip);
    this.addToast('success','Equipamento adicionado','Equipamento adicionado com sucesso!');
  }

  cadastrar(){
    this.salaServices.salvarSala(
      {
        id: null,
        descricao: this.sala.descricao,
        idTipoSala:this.sala.idTipoSala,
        capacidadePessoas: this.sala.capacidadePessoas,
        precoDiario: this.sala.precoDiario,
        equipamentos: this.listaSalaEquip
      }
    ).subscribe(
      () => {
        console.log("Sala Cadastrada");
        this.messageService.add({severity: 'success', summary: 'Sucesso', detail: 'Sala cadastrada com sucesso.'});
        this.listar();
        this.showForm();
        console.log(this.listaSalaEquip)
      },
      () => {
        this.addToast('error','Dados inválidos','Não foi possível cadastrar sala');
        console.log("Error ao chamar serviço");
      }
    )
  }

  editar(sala: salaModel){
    this.salaServices.editarSala(
      {
        id: sala.id,
        descricao: sala.descricao,
        idTipoSala: sala.idTipoSala,
        capacidadePessoas: sala.capacidadePessoas,
        precoDiario: sala.precoDiario,
        equipamentos: sala.equipamentos
      }
    ).subscribe(
      () => {
        console.log("Sala Atualizada");
        this.addToast('success','Sala Atualizada','Sala atualizada com sucesso');
      },
      () => {
        console.log("Error ao chamar serviço");
      }
    )
  }

  addToast(severity, summary, detail){
    this.messageService.add({severity: severity, summary: summary, detail: detail});
  }

  deletar(id:number) {
    this.salaServices.deletarSala(this.idRemover)
    .subscribe(
      () => {
        this.addToast('success','Sala Deletada','Sala deletada com sucesso');
        this.listar();
        this.displayRemover = false;
        console.log("Sala Removida");
      },
      error => {
        if (error.status){
          this.addToast('error','Operação invalida','Sala está reservada');
          console.log("Erro ao chamar o serviço"); 
        }
      });
  }

  showForm() {
    this.listaSalaDialog = !this.listaSalaDialog
    this.listarEquipamentosSala();
    this.salaForm;
    this.displayForm = true;
  }

  turnEquipamentoDialog(sala: salaModel){    
    this.salaSelected = sala;
    this.setEquipSelected();
    this.equipamentoDialog = !this.equipamentoDialog;
  }

  turnDisplayRemover(id: number){
    this.idRemover = id;
    this.displayRemover = !this.displayRemover
  }

  turnDisplayDialog() {
    this.listaSalaDialog = !this.listaSalaDialog
  }

  cancelEdit() {
    this.listar();
  }
  
  validDescriptionEditor(): boolean {
    if(this.descricao == null) {
      return true;
    }
    return false;
  }

  validCapacityPeopleEditor(): boolean {
    if(this.sala.capacidadePessoas == null){
      return true;
    }
    return false;
  }

  validPriceEditor(): boolean {
    if(this.sala.precoDiario == null){
      return true;
    }
    return false;
  }

  validTypeLREditor(): boolean {
    if (this.sala.idTipoSala == null || this.sala.idTipoSala > 5 || this.sala.idTipoSala < 1) {
      return true;
    }
    return false;
  }
  
  validEquipEditor(): boolean {
    if (this.sala.equipamentos == null) {
      return true;
    }
    return false;
  }

  get descricao(){
    return this.salaForm.get('descricao');
  }

  get capacidadePessoas(){
    return this.salaForm.get('capacidadePessoas');
  }

  get precoDiario(){
    return this.salaForm.get('precoDiario');
  }

  get idTipoSala(){
    return this.salaForm.get('idTipoSala');
  }

  get equipamentos(){
    return this.salaForm.get('equipamentos');
  }
}
