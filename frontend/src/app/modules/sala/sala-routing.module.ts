import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListarSalasComponent } from './components/listar-salas/listar-salas.component';

const routes: Routes = [
  {
    path : "salas",
    component : ListarSalasComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SalaRoutingModule { }
