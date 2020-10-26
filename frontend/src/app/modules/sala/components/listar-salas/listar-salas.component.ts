import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ConfirmationService, MessageService } from 'primeng/api';
import { ListarEquipamentoModel } from 'src/app/modules/equipamento/models/listar-equipamento.model';
import { EquipamentoService } from 'src/app/modules/equipamento/services/equipamento.service';
import { salaModel } from '../../models/sala.model';
import { salaEditarModel } from '../../models/salaEditar.model';
import { salaEquipamentoModel } from '../../models/salaEquipamento.model';
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

  listaSalas: salaModel[];

  listaEquipamentos: ListarEquipamentoModel[];

  listaEquipamentosSelecionados: ListarEquipamentoModel[];

  listaEquipamentosObr: salaEquipamentoModel[];

  salaEquipamento: salaEquipamentoModel;

  quantidade: number;

  listaQuantidades: number[];
  
  equipamentosSala: salaEquipamentoModel[];

  sala: salaEditarModel;

  displayForm = false;

  salaForm: FormGroup

  constructor(
    private salaServices: SalaService,
    private equipamentoService: EquipamentoService,
    private formBuilder: FormBuilder,
    private messageService: MessageService,
    private confirmationService: ConfirmationService
  ) { }

  ngOnInit(): void {
    this.listar();
    this.criarFormulario();
    this.listarEquipamentos();
    // this.convertEquip();
  }

  criarFormulario() {
    this.salaForm = this.formBuilder.group(
    {
      id: [null],
      descricao: [null, [Validators.required]],
      idTipoSala: [null, [Validators.required]],
      capacidadePessoas: [null, [Validators.required]],
      precoDiario: [null, [Validators.required]],
      equipamentos: [null, [Validators.required]]
    })
  }

  convertEquip(){
    this.listaEquipamentos.forEach(equipamento => {
    });
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
        return "Eletrodoméstico"
      case 3:
        return "Informática"
    }
  }

  listar() {
    this.salaServices.listarSalas()
      .subscribe(listaSalas => {
        this.listaSalas = listaSalas;
      });
  }

  listarEquipamentos(){
    this.equipamentoService
    .listarEquipamentos()
    .subscribe(listaEquipamentos => {
      this.listaEquipamentos = listaEquipamentos;
    });
  }

  // converterEquip(){
  //   if (this.listaEquipamentos != null){
  //     this.listaEquipamentos.forEach(equipamento => {
  //       this.salaEquipamento.idEquipamento = equipamento.id,
  //       this.salaEquipamento.idSala = null,
  //       this.salaEquipamento.quantidade = 0

  //       this.listaEquipamentosObr.push(this.salaEquipamento)
  //     });
  //   }
  //   else{
  //     console.log("error list");
  //   }
  // }

  salvar(){
    if (this.salaForm.valid) {
      if (this.isEditar()){
        this.editar();
      } else {
        this. cadastrar();
      }
    } else {
      console.log('Formulario invalido')
    }
  }

  isEditar(): boolean {
    return this.sala != null;
  }

  cadastrar(){
    this.salaServices.salvarSala(
      {
        id: null,
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

  deletar(id:number) {
    this.salaServices.deletarSala(id)
    .subscribe(
      () => {
        console.log("Sala Removida")
      },
      () => {
        console.log("Erro ao chamar o serviço");
      });
  }

  showForm() {

    this.listaSalaDialog = !this.listaSalaDialog
    this.salaForm.reset();
    this.displayForm = true;
  }

  turnEquipamentoDialog(){
    this.EquipamentoDialog = !this.EquipamentoDialog;
  }

}
