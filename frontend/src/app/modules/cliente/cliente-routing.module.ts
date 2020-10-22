import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CadastrarClientesComponent } from './compontents/cadastrar-clientes/cadastrar-clientes.component';
import { ListarClientesComponent } from './compontents/listar-clientes/listar-clientes.component';


const routes: Routes = [
  {
    path: 'clientes',
    component: ListarClientesComponent
  },
  {
    path: 'cadastrar-clientes',
    component: CadastrarClientesComponent
  },
  {
    path: 'editar-clientes/:id',
    component: CadastrarClientesComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClienteRoutingModule { }
