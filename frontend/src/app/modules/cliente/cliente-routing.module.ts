import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
// import { ManterClienteComponent } from './components/manter-cliente/manter-cliente.component';
import { ListarClientesComponent } from './components/listar-clientes/listar-clientes.component';

const routes: Routes = [
  {
    path: 'clientes',
    component: ListarClientesComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClienteRoutingModule { }
