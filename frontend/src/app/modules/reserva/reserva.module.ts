import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from 'primeng';
import { CadastrarReservaComponent } from './cadastrar-reserva/cadastrar-reserva.component';
import { ListarReservasComponent } from './listar-reservas/listar-reservas.component';
import { ReservaRoutingModule } from './reserva-routing.module';
import {TabViewModule} from 'primeng/tabview';
import {ToolbarModule} from 'primeng/toolbar';
import {PanelModule} from 'primeng/panel';
import {ButtonModule} from 'primeng/button';
import {TableModule} from 'primeng/table';
import {ToastModule} from 'primeng/toast';
import {DialogModule} from 'primeng/dialog';
import {ListboxModule} from 'primeng/listbox';

@NgModule({
  declarations: [ListarReservasComponent, CadastrarReservaComponent],
  imports: [
    CommonModule,
    ReservaRoutingModule,
    HttpClientModule,
    SharedModule,
    ReactiveFormsModule,
    FormsModule,
    ToolbarModule,
    TabViewModule,
    PanelModule,
    ButtonModule,
    TableModule,
    ToastModule,
    DialogModule,
    ListboxModule
    
    
  ]
})
export class ReservaModule { }
