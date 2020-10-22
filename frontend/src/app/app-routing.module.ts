import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginSuccessComponent } from '@nuvem/angular-base';
import { DiarioErrosComponent } from './components/diario-erros/diario-erros.component';
import { CadastrarReservaComponent } from './modules/reserva/cadastrar-reserva/cadastrar-reserva.component';
import { ListarReservasComponent } from './modules/reserva/listar-reservas/listar-reservas.component';
import { ListarClientesComponent } from './modules/cliente/components/listar-clientes/listar-clientes.component';


const routes: Routes = [
    { path: 'diario-erros', component: DiarioErrosComponent, data: { breadcrumb: 'Diário de Erros'} },
    { path: 'login-success', component: LoginSuccessComponent },
    { path: 'clientes', component: ListarClientesComponent, data: { breadcrumb: 'Clientes'}},
    {path: 'listar-reserva', component: ListarReservasComponent},
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
