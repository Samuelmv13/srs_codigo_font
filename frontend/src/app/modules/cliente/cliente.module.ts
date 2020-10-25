import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClienteRoutingModule } from './cliente-routing.module';
import { ListarClientesComponent } from './components/listar-clientes/clientes.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from 'src/app/shared/shared.module';

import {TableModule} from 'primeng/table';
import {ToastModule} from 'primeng/toast';
import {DialogModule} from 'primeng/dialog';
import {ButtonModule} from 'primeng/button';
import {InputTextModule} from 'primeng/inputtext';
import {InputMaskModule} from 'primeng/inputmask';
import {InputNumberModule} from 'primeng/inputnumber';
import {ConfirmDialogModule} from 'primeng/confirmdialog';
import {MenubarModule} from 'primeng/menubar';
import {PaginatorModule} from 'primeng/paginator';
import {CalendarModule} from 'primeng';
import {MessagesModule} from 'primeng/messages';
import {MessageModule} from 'primeng/message';


@NgModule({
  declarations: [ListarClientesComponent],
  imports: [
    CommonModule,
    ClienteRoutingModule,
    HttpClientModule,
    SharedModule,
    ReactiveFormsModule,
    FormsModule,
    TableModule,
    ButtonModule,
    InputMaskModule,
    MenubarModule,
    PaginatorModule,
    ToastModule,
    DialogModule,
    InputTextModule,
    InputNumberModule,
    ConfirmDialogModule,
    CalendarModule,
    MessageModule,
    MessagesModule
  ]
})
export class ClienteModule { }
