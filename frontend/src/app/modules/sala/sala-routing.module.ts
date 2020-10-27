import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CadastrarSalasComponent } from './components/cadastrar-salas/cadastrar-salas.component';
import { ListarSalasComponent } from './components/listar-salas/listar-salas.component';

const routes: Routes = [
  {
    path : "salas",
    component : ListarSalasComponent
  },
  {
    path : "cadastrar-salas",
    component : CadastrarSalasComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SalaRoutingModule { }
