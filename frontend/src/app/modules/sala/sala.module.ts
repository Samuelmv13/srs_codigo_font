import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

import { SharedModule } from 'src/app/shared/shared.module';
import { ListarSalasComponent } from './components/listar-salas/listar-salas.component';
import { SalaEquipamentoComponent } from './components/sala-equipamento/sala-equipamento.component';
import { SalaRoutingModule } from './sala-routing.module';

import { CardModule } from 'primeng/card';
import { DialogModule } from 'primeng/dialog';
import { MessageModule } from 'primeng/message';
import { MessagesModule } from 'primeng/messages';
import {InputNumberModule} from 'primeng/inputnumber';


@NgModule({
  declarations: [ListarSalasComponent, SalaEquipamentoComponent],
  imports: [
    CommonModule,
    SalaRoutingModule,
    HttpClientModule,
    SharedModule,
    CardModule,
    DialogModule,
    MessageModule,
    MessagesModule,
    InputNumberModule
  ]
})
export class SalaModule { }
