
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
  filterTipos: any[];
  selectTipo: any[];
  displayDelete: boolean;
  filtroPreco: any[];
  filterP: any[];

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

  selecionarTipo() {
    this.selectTipo = [
      { label: 'Selecionar ', value: null },
      { label: 'Móvel', value: '1' },
      { label: 'Eletromésticos', value: '2' },
      { label: 'Informática', value: '3' }];
    return this.selectTipo;
  }

  buscarTipo() {
    this.filterTipos = [
      { label: 'Buscar por categoria', value: null },
      { label: 'Móvel', value: '1' },
      { label: 'Eletromésticos', value: '2' },
      { label: 'Informática', value: '3' }];
    return this.filterTipos;
  }

  buscarPreco() {
    this.filterP = [
      { label: 'Buscar Preço', value: this.faixaPreco(0) },
      { label: '0-100', value: this.faixaPreco(1) },
      { label: '100-200', value: this.faixaPreco(2) },
      { label: '200-500', value: this.faixaPreco(3) },
      { label: '500-800', value: this.faixaPreco(4) },
      { label: '800-1000', value: this.faixaPreco(5) }];
    return this.filterP;
  }

  faixaPreco(i: number) {
    this.listaEquipamentos.forEach(n => {
      let vlr = n.precoDiaria;
      switch (i) {

        case 1:
          if (vlr >= 0 && vlr <= 100)
            this.filtroPreco.push(n);
        case 2:
          if (vlr > 100 && vlr <= 200)
            this.filtroPreco.push(n);
        case 3:
          if (vlr > 200 && vlr <= 500)
            this.filtroPreco.push(n);
        case 4:
          if (vlr > 500 && vlr <= 800)
            this.filtroPreco.push(n);
        case 5:
          if (vlr > 800 && vlr <= 1000)
            this.filtroPreco.push(n);
      }
      if (i != 0) {
        return this.filtroPreco;
      } else {
        return this.consultarEquipamentos();
      }
    })
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
        this.consultarEquipamentos();
        this.messageService.add({ severity: 'success', summary: 'Sucesso', detail: 'Equipamento alterado com sucesso.' });
        this.router.navigate(['../equipamentos']);
      }, response => {
        let msg = response.error.message;
        if (this.validNameEditor() && this.validTypeEditor() && this.validPriceEditor()) {
          msg = 'Preencha o cadastro.';
        } else if (this.validNameEditor()) {
          msg = 'Nome informado inválido.';
        } else if (this.validTypeEditor()) {
          msg = 'Tipo de equipamento inválido.';
        } else if (this.validPriceEditor()) {
          msg = 'Preço informado inválido.';
        }
        this.messageService.add({ severity: 'error', summary: 'Erro', detail: msg })
      })

  }

  validNameEditor(): boolean {
    if (this.equipamentoModel.nome == null || this.equipamentoModel.nome.length < 3) {
      return true;
    }
    return false;
  }

  validTypeEditor(): boolean {
    if (this.equipamentoModel.idTipoEquipamento == null || this.equipamentoModel.idTipoEquipamento > 3 || this.equipamentoModel.idTipoEquipamento < 1) {
      return true;
    }
    return false;
  }

  validPriceEditor(): boolean {
    if (this.equipamentoModel.precoDiaria == null) {
      return true;
    }
    return false;
  }

  cadastrarEquipamento(value) {
    this.equipamentoService.cadastrarEquipamento(value).subscribe(
      () => {
        this.consultarEquipamentos();
        this.cadastro = false;
        this.messageService.add({ severity: 'success', summary: 'Sucesso', detail: 'Equipamento cadastrado com sucesso.' });
        this.router.navigate(['../equipamentos']);
      }, response => {
        let msg = response.error.message;
        if (this.nome.invalid &&
          this.tipoEquipamento.invalid
          && this.precoDiaria.invalid) {
          msg = 'Preencha o cadastro.';
        } else if (this.nome.invalid) {
          msg = 'Nome informado inválido.';
        } else if (this.tipoEquipamento.invalid) {
          msg = 'Tipo de equipamento inválido.';
        } else if (this.precoDiaria.invalid) {
          msg = 'Preço informado inválido.';
        }
        this.messageService.add({ severity: 'error', summary: 'Erro', detail: msg })
      })
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

}
