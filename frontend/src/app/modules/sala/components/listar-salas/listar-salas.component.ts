import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ConfirmationService, MessageService } from 'primeng/api';
import { ListarEquipamentoModel } from 'src/app/modules/equipamento/models/listar-equipamento.model';
import { EquipamentoService } from 'src/app/modules/equipamento/services/equipamento.service';
import { EquipamentoModel, EquipamentoQtdModel } from '../../models/equipamento.model';
import { salaModel } from '../../models/sala.model';
import { salaEditarModel } from '../../models/salaEditar.model';
import { salaEquipamentoModel } from '../../models/salaEquipamento.model';
import { SalaEquipamentoService } from '../../services/sala-equipamento.service';
import { SalaService } from '../../services/sala.service';

@Component({
  selector: 'app-listar-salas',
  templateUrl: './listar-salas.component.html',
  styleUrls: ['./listar-salas.component.css'],
  providers: [ConfirmationService, MessageService],
})
export class ListarSalasComponent implements OnInit {

  listaSalaDialog: boolean;


  EquipamentoDialog: boolean;

  selecionarSalaEquipamentoDialog: boolean;

  listaSalas: salaModel[];

  listaEquipamentosQtd: EquipamentoQtdModel[];

  listaEquipamentosSelecionados: EquipamentoQtdModel[];

  listaSalaEquip: salaEquipamentoModel[];

  eqOb: EquipamentoQtdModel;
  
  sala: salaModel;

  salaSelected: salaModel;

  salaSelectedEquips: salaEquipamentoModel[];

  displayForm = false;

  salaForm: FormGroup;

  idRemover: number;

  displayRemover: boolean;


  constructor(
    private salaServices: SalaService,
    private equipamentoService: EquipamentoService,
    private formBuilder: FormBuilder,
    private salaEquipamentoService: SalaEquipamentoService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService
  ) { }

  ngOnInit(): void {
    this.listaSalas = [];
    this.listaSalaEquip = [];
    this.iniciarSala();
    this.listar();
    this.criarFormulario();
  }

  criarFormulario() {
    this.salaForm = this.formBuilder.group(
    {
      id: [null],
      descricao: [null, [Validators.required]],
      idTipoSala: [null, [Validators.required]],
      capacidadePessoas: [null, [Validators.required]],
      precoDiario: [null, [Validators.required]],
      equipamentos: [null]
    });
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

  setEquipSelected(){
    this.salaSelectedEquips = [];
    this.salaSelectedEquips = this.salaSelected.equipamentos;
  }

  listar() {
    this.salaServices.listarSalas()
      .subscribe(listaSalas => {
        this.listaSalas = listaSalas;        
      });
  }
  
  listarEquipamentosSala(){
    this.listaEquipamentosQtd= [];
    this.equipamentoService.listarEquipamentos().subscribe(
      (listaEquipamentos: ListarEquipamentoModel[]) => {
        for (let equip of listaEquipamentos){
          this.salaEquipamentoService.setEquipamento(equip);
          this.listaEquipamentosQtd.push(this.salaEquipamentoService.getEquipamentoQtd());
        }
      });
  }

  isEditar(): boolean {
    return this.sala != null;
  }

  addSalaEquip(equip: EquipamentoQtdModel){
    this.salaEquipamentoService.setSalaEquipamento(equip);
    var salaEquip = this.salaEquipamentoService.getSalaEquipamento();
    this.listaSalaEquip.push(salaEquip);
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
        this.listar();
        this.showForm();
        console.log(this.listaSalaEquip)
      },
      () => {
        console.log("Error ao chamar serviço");
      }
    )
  }

  editar(){
    this.salaServices.salvarSala(
      {
        id: this.salaForm.get("id").value,
        descricao: this.salaForm.get("descricao").value,
        idTipoSala: this.salaForm.get("idTipoSala").value,
        capacidadePessoas: this.salaForm.get("capacidadePessoas").value,
        precoDiario: this.salaForm.get("precoDiario").value,
        equipamentos: this.salaForm.get("equipamentos").value
      }
    ).subscribe(
      () => {
        console.log("Cliente Cadastrado");
      },
      () => {
        console.log("Error ao chamar serviço");
      }
    )
  }

  deletar() {
    this.salaServices.deletarSala(this.idRemover)
    .subscribe(
      () => {
        console.log("Sala Removida");
        this.messageService.add({severity: 'success', summary: 'Sala Deletada'});
        this.criarFormulario();
        this.listar();
        this.displayRemover = false;
      },
      () => {
        console.log("Erro ao chamar o serviço");
      });
  }

  showForm() {
    this.listaSalaDialog = !this.listaSalaDialog
    this.listarEquipamentosSala();
    this.salaForm.reset();
    this.displayForm = true;
  }

  turnEquipamentoDialog(sala: salaModel){
    this.EquipamentoDialog = !this.EquipamentoDialog;
    this.salaSelected = sala;
    this.setEquipSelected();
  }

  turnDisplayRemover(id: number){
    this.idRemover = id;
    this.displayRemover = !this.displayRemover
  }
}
