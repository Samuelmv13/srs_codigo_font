import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListarEquipamentosComponent } from './components/listar-equipamentos/listar-equipamentos.component';


const routes: Routes = [
  {
    path: 'equipamentos',
    component: ListarEquipamentosComponent
  },
  {
    path: 'equipamentos/:id',
    component: ListarEquipamentosComponent
  },
  {
    path: 'cadastrar-equipamentos',
    component: ListarEquipamentosComponent
  },
  {
    path: 'editar-equipamentos/:id',
    component: ListarEquipamentosComponent
  },
  {
    path: 'deletar-equipamentos/:id',
    component: ListarEquipamentosComponent 

  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EquipamentoRoutingModule { }
