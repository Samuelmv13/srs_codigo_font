import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from 'src/app/shared/shared.module';
import { ClienteRoutingModule } from './cliente-routing.module';
import { CadastrarClientesComponent } from './compontents/cadastrar-clientes/cadastrar-clientes.component';
import { ListarClientesComponent } from './compontents/listar-clientes/listar-clientes.component';



@NgModule({
  declarations: [ListarClientesComponent, CadastrarClientesComponent],
  imports: [
    CommonModule,
    ClienteRoutingModule,
    HttpClientModule,
    SharedModule,
    ReactiveFormsModule,
    FormsModule
  ]
})
export class ClienteModule { }
