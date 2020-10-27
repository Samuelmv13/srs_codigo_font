import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginSuccessComponent } from '@nuvem/angular-base';
import { DiarioErrosComponent } from './components/diario-erros/diario-erros.component';
import { ListarReservasComponent } from './modules/reserva/listar-reservas/listar-reservas.component';
import { ListarClientesComponent } from './modules/cliente/components/listar-clientes/clientes.component';
import { ListarEquipamentosComponent } from './modules/equipamento/components/listar-equipamentos/listar-equipamentos.component';
import { ListarSalasComponent } from './modules/sala/components/listar-salas/listar-salas.component';


const routes: Routes = [
    { path: 'diario-erros', component: DiarioErrosComponent, data: { breadcrumb: 'Diário de Erros'} },
    { path: 'login-success', component: LoginSuccessComponent },
    { path: 'clientes', component: ListarClientesComponent, data: { breadcrumb: 'Clientes'} },
    { path: 'reservas', component: ListarReservasComponent, data: { breadcrumb: 'Reservas'} },
    { path: 'equipamentos', component: ListarEquipamentosComponent, data: { breadcrumb: 'Equipamentos'} },
    { path: 'salas', component: ListarSalasComponent, data: { breadcrumb: 'Salas'} }
];


@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
