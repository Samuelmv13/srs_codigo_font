import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ListarClienteModel } from '../../models/listar-cliente.model';
import { ClienteService } from '../../services/cliente.service';

@Component({
  selector: 'app-listar-clientes',
  templateUrl: './listar-clientes.component.html',
  styleUrls: ['./listar-clientes.component.css']
})
export class ListarClientesComponent implements OnInit {
  listaClientes: ListarClienteModel[];

  constructor(
    private clienteSevice: ClienteService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.consultarClientes();
  }

  consultarClientes() {
    this.clienteSevice
      .listarClientes()
      .subscribe(listaClientes => {
        this.listaClientes = listaClientes;
      });
  }

  direcionarCadastrar() {
    this.router.navigate(['../cadastrar-clientes'])
  }

  direcionarEditarCliente(id: number) {
    this.router.navigate([`../editar-clientes/${id}`])
  }

}
