import { Component, OnInit } from '@angular/core';
import { ListarClientesModel } from '../../models/listar-clientes.model';
import { ClienteService } from '../../services/cliente.service';
import { MessageService } from 'primeng/api';
import { EditarClienteModel } from '../../models/editar-cliente.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
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
        nome: [null, 
          [Validators.required, Validators.maxLength(120)]],
        cpf: [null, 
          [Validators.required]],
        dtNasc: [null,
          [Validators.required]],
        endereco: [null, 
          [Validators.required, Validators.maxLength(255)]],
        email: [null, 
          [Validators.required, Validators.email]],
        telefone: [null, 
          [Validators.required, Validators.minLength(13), Validators.maxLength(17)]],
        rg: [null, 
          [Validators.required, Validators.minLength(7), Validators.maxLength(9)]]
      }
    )
  }

  listar() {
    this.clienteService.listarClientes().subscribe(listarClientes => { this.listaClientes = listarClientes });
  }

  salvar() {
    if (this.formCliente.valid) {
      this.maskRemove();
      if (this.isEditar()) {
        this.editar();
      } else {
        this.cadastrar();
      }
    } else {
      this.messageService.add({ severity: 'error', summary: 'Formulário Inválido', detail: 'Verifique os campos e tente novamente!' });
      console.log('Formulario invalido')
    }
  }

  preencherFormulario(clienteEditado: EditarClienteModel) {
    this.criarFormulario();
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
        this.listar();
        this.cliente = null;
      },
      error => {
        this.validacoesSalvar(error);
      });
  }

  cpfInvalido(cpf){
    cpf = cpf.replace(/\D/g, '');
    if(cpf.toString().length != 11 || /^(\d)\1{10}$/.test(cpf)) return false;
    var result = true;
    [9,10].forEach(function(j){
        var soma = 0, r;
        cpf.split(/(?=)/).splice(0,j).forEach(function(e, i){
            soma += parseInt(e) * ((j+2)-(i+1));
        });
        r = soma % 11;
        r = (r <2)?0:11-r;
        if(r != cpf.substring(j, j+1)) result = false;
    });
    return result;
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
      error => {
        this.validacoesSalvar(error);
      });
  }

  validacoesSalvar(error){
    if (error.status == 400){
      if (!this.cpfInvalido(this.formCliente.get('cpf').value)){
        this.messageService.add({ severity: 'error', summary: 'Formulário invalido', detail: 'Cpf invalido!' });        
      } else{
        this.messageService.add({ severity: 'error', summary: 'Formulário invalido', detail: 'Dados já cadastrados no sistema!' });        
      }
      this.listar();
    } else{
      console.log('Erro ao chamar serviço');
    }
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
        error => {
          if (error.status == 400){
            this.messageService.add({ severity: 'error', summary: 'Cliente com reserva', detail: 'O cliente possui uma reserva cadastrada!' });
          } else{
            console.log('Erro ao chamar serviço');
          }
          this.displayRemover = false;
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

  maskRemove() {
    var cpfNoMask = this.formCliente.get('cpf').value.replace('.', '').replace('.', '').replace('-', '');
    var rgNoMask = this.formCliente.get('rg').value.replace('.', '').replace('-', '');
    var telefoneNoMask = this.formCliente.get('telefone').value.replace('+', '').replace(' ', '').replace(' ', '').replace('-', '');

    this.formCliente.setValue({
      id: null,
      nome: this.formCliente.get('nome').value,
      cpf: cpfNoMask,
      dtNasc: this.formCliente.get('dtNasc').value,
      endereco: this.formCliente.get('endereco').value,
      email: this.formCliente.get('email').value,
      telefone: telefoneNoMask,
      rg: rgNoMask
    })
  }
}
