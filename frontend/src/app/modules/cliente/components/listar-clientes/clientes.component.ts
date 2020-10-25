import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ListarClientesModel } from '../../models/listar-clientes.model';
import { ClienteService } from '../../services/cliente.service';
import { MessageService } from 'primeng/api';
import { EditarClienteModel } from '../../models/editar-cliente.model';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { InfoClienteModel } from '../../models/info-cliente.model';

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
  styleUrls: ['./clientes.component.css']
})
export class ListarClientesComponent implements OnInit {
  listaClientes: ListarClientesModel[];

  cliente: InfoClienteModel;

  clienteValid: InfoClienteModel;

  selectedClientes: ListarClientesModel[];

  clienteDialog: boolean;

  idRemover: number;

  displayRemover: boolean;

  cols: any;

  formCliente: FormGroup


  constructor(
    private clienteService: ClienteService,
    private messageService: MessageService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.listar();
    this.criarFormulario();
  }

  criarFormulario() {
    this.formCliente = this.formBuilder.group(
      {
        id: [null],
        nome: [null, [Validators.required, Validators.maxLength(120)]],
        cpf: [null, [Validators.required, Validators.minLength(14), Validators.maxLength(14)]],
        dtNasc: [null, [Validators.required]],
        endereco: [null, [Validators.required, Validators.maxLength(255)]],
        email: [null, [Validators.required, Validators.email]],
        telefone: [null, [Validators.required, Validators.minLength(18), Validators.maxLength(18)]],
        rg: [null, [Validators.required, Validators.minLength(9), Validators.maxLength(9)]],
      }
    )
  }

  listar() {
    this.clienteService.listarClientes().subscribe(listarClientes => { this.listaClientes = listarClientes });
  }

  salvar() {
    if (this.formCliente.valid) {
      if (this.isEditar()) {
        this.editar();
      } else {
        this.cadastrar();
      }
    } else {
      this.messageService.add({ severity: 'error', summary: 'Formulário Inválido', detail: 'Verifique os campos e tente novamente!' });
      console.log('Formulario invalido')
      this.listar();
      // Object.keys(this.formCliente.controls).forEach(campo => {
      //   const controle = this.formCliente.get(campo);
      //   controle.mar
      // })
    }
  }

  preencherFormulario(clienteEditado: EditarClienteModel) {
    this.formCliente.setValue({
      id: clienteEditado.id,
      nome: clienteEditado.nome,
      cpf: clienteEditado.cpf,
      rg: clienteEditado.rg,
      dtNasc: clienteEditado.dtNasc,
      endereco: clienteEditado.endereco,
      telefone: clienteEditado.telefone,
      email: clienteEditado.email
    });
    this.cliente = clienteEditado;
    this.salvar();
  }

  editar() {
    this.clienteService.editarClientes(
      {
        id: this.cliente.id,
        nome: this.formCliente.get('nome').value,
        cpf: this.formCliente.get('cpf').value,
        dtNasc: this.formCliente.get('dtNasc').value,
        endereco: this.formCliente.get('endereco').value,
        email: this.formCliente.get('email').value,
        telefone: this.formCliente.get('telefone').value,
        rg: this.formCliente.get('rg').value
      }
    ).subscribe(
      () => {
        console.log('Cliente Atualizado');
        this.messageService.add({ severity: 'success', summary: 'Cliente Atualizado' });
      },
      () => {
        console.log('Erro ao chamar serviço');
        this.messageService.add({ severity: 'error', summary: 'Formulário Inválido', detail: 'Verifique os campos e tente novamente!'});
      });
    this.listar()
    this.cliente = null;
  }

  cadastrar() {
    this.clienteService.cadastrarClientes(
      {
        nome: this.formCliente.get('nome').value,
        cpf: this.formCliente.get('cpf').value,
        dtNasc: this.formCliente.get('dtNasc').value,
        endereco: this.formCliente.get('endereco').value,
        email: this.formCliente.get('email').value,
        telefone: this.formCliente.get('telefone').value,
        rg: this.formCliente.get('rg').value
      }
    ).subscribe(
      () => {
        console.log('Cliente Cadastrado');
        this.messageService.add({ severity: 'success', summary: 'Cliente Cadastrado' });
        this.turnDisplayDialog();
        this.listar()      
      },
      () => {
        console.log('Erro ao chamar serviço');
        this.messageService.add({ severity: 'error', summary: 'Formulário Inválido', detail: 'Verifique os campos e tente novamente!' });
      });
  }

  deletar() {
    this.clienteService.deletarClientes(this.idRemover)
      .subscribe(
        () => {
          console.log('Cliente Deletado');
          this.messageService.add({ severity: 'success', summary: 'Cliente Deletado' });
          this.criarFormulario();
          this.listar();
          this.displayRemover = false;
        },
        () => {
          console.log('Erro ao chamar serviço');
        });
  }

  isEditar(): boolean {
    return this.cliente != null;
  }
  turnDisplayDialog() {
    this.clienteDialog = !this.clienteDialog
    this.criarFormulario();
  }

  turnDisplayRemover(id: number) {
    this.idRemover = id;
    this.displayRemover = !this.displayRemover
  }

  cancelEdit() {
    this.listar();
    this.criarFormulario();
  }
}
