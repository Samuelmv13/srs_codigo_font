import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { InfoClienteModel } from '../../models/info-cliente.model';
import { ClienteService } from '../../services/cliente.service';

@Component({
  selector: 'app-cadastrar-clientes',
  templateUrl: './cadastrar-clientes.component.html',
  styleUrls: ['./cadastrar-clientes.component.css']
})
export class CadastrarClientesComponent implements OnInit {
  formCliente: FormGroup;
  cliente: InfoClienteModel;

  constructor(
    private formBuilder: FormBuilder,
    private clienteService: ClienteService,
    private router: Router,
    private actRouter: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.criarFormulario();
    this.recuperarIdRota();
  }

  recuperarIdRota() {
    const id = this.actRouter.snapshot.params['id'];
    if (id) {
      this.recuperarCliente(id);
    }
  }

  recuperarCliente(id: number) {
    this.clienteService.recuperarCliente(id)
      .subscribe(cliente => {
        this.cliente = cliente;
        this.formCliente.patchValue(cliente);
      })
  }

  criarFormulario() {
    this.formCliente = this.formBuilder.group(
      {
        nome: [null, [Validators.required]],
        idade: [null, [Validators.required]]
      }
    );
  }

  salvar() {

    if (this.formCliente.valid) {
      if (this.isEditar()) {
        this.editarCliente();
      } else {
        this.cadastrarCliente();
      }
    } else {
      console.log('Formulario invalido');
    }

  }

  private editarCliente() {
    this.clienteService.editarCliente(
      {
        id: this.cliente.id,
        nome: this.formCliente.get('nome').value,
        idade: this.formCliente.get('idade').value
      }
    ).subscribe(
      () => {
        console.log('Cliente Atualizado');
        this.router.navigate(['../clientes']);
      },
      () => {
        console.log('Erro ao chamar serviço');
      });
  }

  private cadastrarCliente() {
    this.clienteService.cadastrarCliente(
      {
        nome: this.formCliente.get('nome').value,
        idade: this.formCliente.get('idade').value
      }
    ).subscribe(
      () => {
        console.log('Cliente Cadastrado');
        this.router.navigate(['../clientes']);
      },
      () => {
        console.log('Erro ao chamar serviço');
      });
  }

  isEditar(): boolean {
    return this.cliente != null;
  }

}
