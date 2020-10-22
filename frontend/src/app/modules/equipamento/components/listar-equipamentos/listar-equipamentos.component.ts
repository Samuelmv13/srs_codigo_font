import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { ListarEquipamentoModel } from '../../models/listar-equipamento.model';
import { EquipamentoService } from '../../services/equipamento.service';

@Component({
  selector: 'app-listar-equipamentos',
  templateUrl: './listar-equipamentos.component.html',
  styleUrls: ['./listar-equipamentos.component.css']
})
export class ListarEquipamentosComponent implements OnInit {

  listaEquipamentos: ListarEquipamentoModel[];
  equipamentoModel: ListarEquipamentoModel;
  cadastro: boolean;
  formEquipamento: FormGroup;
  idDelete: number;



  constructor(
    private messageService: MessageService,
    private equipamentoService: EquipamentoService,
    private router: Router,
    private formBuilder: FormBuilder,
    private actRouter: ActivatedRoute,
  ) { }

  ngOnInit(): void {
    this.consultarEquipamentos();
    this.criarFormulario();
    this.recuperarIdRota();
  }

  consultarEquipamentos() {
    this.equipamentoService
      .listarEquipamentos()
      .subscribe(listaEquipamentos => {
        this.listaEquipamentos = listaEquipamentos;
      });
  }

  deletarEquipamento(id: number) {
    this.equipamentoService.deletarEquipamento(id).subscribe(
      () => {
        this.messageService.add({ severity: 'success', summary: 'Sucesso', detail: 'Reserva removida com sucesso.' });
        this.consultarEquipamentos();
      }, response => {
        this.messageService.add({ severity: 'error', summary: 'Erro', detail: response.error.message })
      });
  }

  recuperarIdRota() {
    const id = this.actRouter.snapshot.params['id'];
    if (id) {
      this.recuperarEquipamento(id);
    }
  }

  recuperarEquipamento(id: number) {
    this.equipamentoService.recuperarEquipamento(id)
      .subscribe(equipamento => {
        this.equipamentoModel = equipamento;
        this.formEquipamento.patchValue(equipamento);
      })
  }

  criarFormulario() {
    this.formEquipamento = this.formBuilder.group({
      nome: [null, Validators.required, Validators.minLength(3)],
      precoDiaria: [null, Validators.required],
      idTipoEquipamento: [null, Validators.required]
    })
  }

  verificaForm(formGroup: FormGroup) {
    Object.keys(formGroup.controls).forEach(form => {
      const control = this.formEquipamento.get(form);
      control.markAsDirty();
      if (control instanceof FormGroup) {
        this.verificaForm(control);
      }
    })
  }

  editarEquipamento() {
    this.equipamentoService.editarEquipamento(
      {
        id: this.equipamentoModel.id,
        nome: this.equipamentoModel.nome,
        idTipoEquipamento: this.equipamentoModel.idTipoEquipamento,
        precoDiaria: this.equipamentoModel.precoDiaria
      }
    ).subscribe(
      () => {
        console.log('Equipamento Atualizado');
        this.router.navigate(['../equipamentos']);
      },
      () => {
        console.log('Erro ao chamar serviço');
      })
      
  }

  cadastrarEquipamento(value) {
    this.equipamentoService.cadastrarEquipamento(value).subscribe(
      () => {
        this.consultarEquipamentos();
        this.cadastro = false;
        this.messageService.add({ severity: 'success', summary: 'Sucesso', detail: 'Equipamento cadastrado com sucesso.' });
        this.router.navigate(['../equipamentos']);
      }, response => {
        if (this.nome.invalid && this.tipoEquipamento.invalid && this.precoDiaria.invalid) {
          this.messageService.add({ severity: 'error', summary: 'Erro', detail: 'Preencha o cadastro.' })
        } else if (this.nome.invalid) {
          this.messageService.add({ severity: 'error', summary: 'Erro', detail: 'Nome informado inválido.' });
        } else if (this.tipoEquipamento.invalid) {
          this.messageService.add({ severity: 'error', summary: 'Erro', detail: 'Tipo de equipamento inválido.' });
        } else if (this.precoDiaria.invalid) {
          this.messageService.add({ severity: 'error', summary: 'Erro', detail: 'Preço informado inválido.' });
        } else {
          this.messageService.add({ severity: 'error', summary: 'Erro', detail: response.error.message })
        }
      })
  }

  isEditar(): boolean {
    return this.equipamentoModel != null;
  }

  resetar() {
    this.formEquipamento.reset();
  }

  deleteConfirm() {
    this.messageService.clear('c');
    this.deletarEquipamento(this.idDelete);
  }

  deleteReject() {
    this.messageService.clear('c');
  }

  showConfirm() {
    this.messageService.clear();
    this.messageService.add({ key: 'c', sticky: true, severity: 'warn', summary: 'apagar', detail: 'Confirm to proceed' });
  }

  get nome() {
    return this.formEquipamento.get('nome');
  }

  get tipoEquipamento() {
    return this.formEquipamento.get('idTipoEquipamento');
  }

  get precoDiaria() {
    return this.formEquipamento.get('precoDiaria');
  }

  getTipo(n: number) {
    switch (n) {
      case 1:
        return "Móvel";
      case 2:
        return "Eletrodoméstico";
      case 3:
        return "Informática";
    }
    this.consultarEquipamentos();
  }

}
