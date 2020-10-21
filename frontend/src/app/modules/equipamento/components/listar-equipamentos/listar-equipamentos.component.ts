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
  equipamento: ListarEquipamentoModel;
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
      ()=>{
      this.messageService.add({severity:'success', summary: 'Success', detail: 'Reserva removida com sucesso.'});
      this.consultarEquipamentos();
    },response => {      
      this.messageService.add({severity:'error', summary:'Error',detail:'algo de errado não está certo'})
    });
  }

  getTipo(n: number) {
    switch (n) {
      case 1:
        return "Móvel";
      case 2:
        return "Eletrodoméstico";
      case 3:
        return "Informática";
      default:
        return "Selecionar Tipo";
    }
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
        this.equipamento = equipamento;
        this.formEquipamento.patchValue(equipamento);
      })
  }

  criarFormulario() {
    this.formEquipamento = this.formBuilder.group({
      nome: [null, Validators.required, Validators.minLength(3)],
      preco: [null, Validators.required],
      idTipoEquipamento: [null, Validators.required]
    })
  }

  salvar() {
    if (this.formEquipamento.valid) {
      if (this.isEditar()) {
        this.editarCliente();
      } else {
        this.cadastrarEquipamento
      }
      this.resetar();
    } else {
      this.verificaForm(this.formEquipamento);
    }
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

  resetar() {
    this.formEquipamento.reset();
  }
  private editarCliente() {
    this.equipamentoService.editarEquipamento(
      {
        id: this.equipamento.id,
        nome: this.formEquipamento.get('nome').value,
        idTipoEquipamento: this.formEquipamento.get('idTipoEquipamento').value,
        preco: this.formEquipamento.get('preco').value
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

  edit(id: number) {
    this.equipamentoService.recuperarEquipamento(id).forEach(n => {
      this.formEquipamento.setValue({
        nome: n.nome,
        idTipoEquipamento: n.idTipoEquipamento,
        preco: n.preco
      })
    })
  }


  private cadastrarEquipamento() {
    this.equipamentoService.cadastrarEquipamento(
      {
        nome: this.formEquipamento.get('nome').value,
        idTipoEquipamento: this.formEquipamento.get('idTipoEquipamento').value,
        preco: this.formEquipamento.get('preco').value
      }
    ).subscribe(
      () => {
        console.log('Equipamento Cadastrado');
        this.router.navigate(['../equipamentos']);
      },
      () => {
        console.log('Erro ao chamar serviço');
      })
  }

  isEditar(): boolean {
    return this.equipamento != null;
  }

  onConfirm() {
    this.messageService.clear('c');
    this.deletarEquipamento(this.idDelete);
  }

  onReject() {
    this.messageService.clear('c');
  }


  showConfirm() {
    this.messageService.clear();
    this.messageService.add({ key: 'c', sticky: true, severity: 'warn', summary: 'apagar', detail: 'Confirm to proceed' });
  }

}
