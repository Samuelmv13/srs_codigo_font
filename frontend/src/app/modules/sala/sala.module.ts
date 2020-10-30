import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

import { SharedModule } from 'src/app/shared/shared.module';
import { ListarSalasComponent } from './components/listar-salas/listar-salas.component';
import { SalaRoutingModule } from './sala-routing.module';

import { CardModule } from 'primeng/card';
import { DialogModule } from 'primeng/dialog';
import { MessageModule } from 'primeng/message';
import { MessagesModule } from 'primeng/messages';
import {InputNumberModule} from 'primeng/inputnumber';
import {ToastModule} from 'primeng/toast';


@NgModule({
  declarations: [ListarSalasComponent],
  imports: [
    CommonModule,
    SalaRoutingModule,
    HttpClientModule,
    SharedModule,
    CardModule,
    DialogModule,
    MessageModule,
    MessagesModule,
    InputNumberModule,
    ToastModule
  ]
})
export class SalaModule { }
