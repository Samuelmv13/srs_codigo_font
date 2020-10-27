import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CadastrarReservaComponent } from './cadastrar-reserva/cadastrar-reserva.component';
import { ListarReservasComponent } from './listar-reservas/listar-reservas.component';

const routes: Routes = [
  {
    path: 'reservas',
    component: ListarReservasComponent
  },
  {
    path: 'cadastrar-reservas',
    component: CadastrarReservaComponent
  },
  {
    path: 'editar-reservas/:id',
    component: CadastrarReservaComponent
  }
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ReservaRoutingModule { }
