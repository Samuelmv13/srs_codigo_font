import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute,  } from '@angular/router';
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
  equipamentoDeEdicao: ListarEquipamentoModel;
  cadastro: boolean;
  formEquipamento: FormGroup;
  idDelete: number;
  selectTipo: any[];
  displayDelete: boolean;
  filtroPreco: any[];
  filterP: any[];

  constructor(
    private messageService: MessageService,
    private equipamentoService: EquipamentoService,
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

  recuperarEquipamento(id: number) {
    this.equipamentoService.recuperarEquipamento(id)
      .subscribe(equipamento => {
        this.equipamentoDeEdicao = equipamento;
        this.formEquipamento.patchValue(equipamento);
      })
  }

  cadastrarEquipamento(value) {
    this.equipamentoService.cadastrarEquipamento(value).subscribe(
      () => {
        this.consultarEquipamentos();
        this.cadastro = false;
        this.messageService.add({ severity: 'success', summary: 'Sucesso', detail: 'Equipamento cadastrado com sucesso.' });
      }, response => {
        let msg = response.error.message;
        if (this.nome.invalid &&
          this.tipoEquipamento.invalid
          && this.precoDiaria.invalid) {
          msg = 'Preencha o cadastro.';
        } else if (this.nome.invalid) {
          msg = 'Nome inválido.';
        } else if (this.tipoEquipamento.invalid) {
          msg = 'Tipo de equipamento inválido.';
        } else if (this.precoDiaria.invalid) {
          msg = 'Preço inválido.';
        }
        this.messageService.add({ severity: 'error', summary: 'Erro', detail: msg })
      })
  }

  editarEquipamento() {
    this.equipamentoService.editarEquipamento(
      {
        id: this.equipamentoDeEdicao.id,
        nome: this.equipamentoDeEdicao.nome,
        idTipoEquipamento: this.equipamentoDeEdicao.idTipoEquipamento,
        precoDiaria: this.equipamentoDeEdicao.precoDiaria
      }
    ).subscribe(
      () => {
        this.consultarEquipamentos();
        this.messageService.add({ severity: 'success', summary: 'Sucesso', detail: 'Equipamento alterado com sucesso.' });
        this.equipamentoDeEdicao=null;
      }, response => {
        let msg = response.error.message;
        if (this.validNameEditor() && this.validTypeEditor() && this.validPriceEditor()) {
          msg = 'Preencha o cadastro.';
        } else if (this.validNameEditor()) {
          msg = 'Nome inválido.';
        } else if (this.validTypeEditor()) {
          msg = 'Tipo de equipamento inválido.';
        } else if (this.validPriceEditor()) {
          msg = 'Preço não informado.';
        }
        this.messageService.add({ severity: 'error', summary: 'Erro', detail: msg })
      })

  }

  deletarEquipamento(id: number) {
    this.equipamentoService.deletarEquipamento(id).subscribe(
      () => {
        this.messageService.add({ severity: 'success', summary: 'Sucesso', detail: 'Equipamento removido com sucesso.' });
        this.consultarEquipamentos();
      }, response => {
        this.messageService.add({ severity: 'error', summary: 'Remoção Inválida', detail: 'Equipamento já cadastrado em uma reserva ou sala.'})
      });
  }

  recuperarIdRota() {
    const id = this.actRouter.snapshot.params['id'];
    if (id) {
      this.recuperarEquipamento(id);
    }
  }

  criarFormulario() {
    this.formEquipamento = this.formBuilder.group({
      nome: [null, Validators.required, Validators.minLength(3)],
      precoDiaria: [null, Validators.required],
      idTipoEquipamento: [null, Validators.required]
    })
  }

  validNameEditor(): boolean {
    if (this.equipamentoDeEdicao.nome == null || this.equipamentoDeEdicao.nome.length < 3) {
      return true;
    }
    return false;
  }

  validTypeEditor(): boolean {
    if (this.equipamentoDeEdicao.idTipoEquipamento == null || this.equipamentoDeEdicao.idTipoEquipamento > 3 || this.equipamentoDeEdicao.idTipoEquipamento < 1) {
      return true;
    }
    return false;
  }

  validPriceEditor(): boolean {
    if (this.equipamentoDeEdicao.precoDiaria == null) {
      return true;
    }
    return false;
  }

  


  resetar() {
    this.formEquipamento.reset();
  }

  deleteConfirm() {
    this.messageService.clear('c');
    this.deletarEquipamento(this.idDelete);
    this.displayDelete = false;
  }

  deleteReject() {
    this.messageService.clear('c');
    this.displayDelete = false;
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

  selecionarTipo() {
    this.selectTipo = [
      { label: 'Selecionar ', value: null },
      { label: 'Móvel', value: '1' },
      { label: 'Eletrodomésticos', value: '2' },
      { label: 'Informática', value: '3' }];
    return this.selectTipo;
  }

  buscarTipo() {
    this.selectTipo = [
      { label: 'Buscar por categoria', value: null },
      { label: 'Móvel', value: '1' },
      { label: 'Eletrodomésticos', value: '2' },
      { label: 'Informática', value: '3' }];
    return this.selectTipo;
  }
}
