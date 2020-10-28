import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { EquipamentoRoutingModule } from './equipamento-routing.module';
import { ListarEquipamentosComponent } from './components/listar-equipamentos/listar-equipamentos.component';
import { SharedModule } from 'src/app/shared/shared.module';

import { DialogModule } from 'primeng/dialog';
import { InputNumberModule } from 'primeng/inputnumber';
import {ToastModule} from 'primeng/toast';

@NgModule({
  declarations: [ListarEquipamentosComponent, ],
  imports: [
    CommonModule,
    EquipamentoRoutingModule,
    HttpClientModule,
    SharedModule,
    ReactiveFormsModule,
    FormsModule,
    DialogModule,
    InputNumberModule,
    ToastModule


  ]
})
export class EquipamentoModule { }
