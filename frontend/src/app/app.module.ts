import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SecurityModule, VersionTagModule } from '@nuvem/angular-base';
import { BreadcrumbModule, ErrorStackModule, MenuModule, PageNotificationModule } from '@nuvem/primeng-components';
import { BlockUIModule } from 'ng-block-ui';
import { DropdownModule, InputTextareaModule, InputTextModule } from 'primeng';
import { ButtonModule } from "primeng/button";
import { CardModule } from 'primeng/card';
import { CheckboxModule } from "primeng/checkbox";
import { DialogModule } from 'primeng/dialog';
import { InputNumberModule } from 'primeng/inputnumber';
import { ListboxModule } from 'primeng/listbox';
import { OrderListModule } from 'primeng/orderlist';
import { PanelModule } from 'primeng/panel';
import { RadioButtonModule } from "primeng/radiobutton";
import { TableModule } from 'primeng/table';
import { TabViewModule } from 'primeng/tabview';
import { ToastModule } from 'primeng/toast';
import { ToolbarModule } from 'primeng/toolbar';
import { environment } from '../environments/environment';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DiarioErrosComponent } from './components/diario-erros/diario-erros.component';
import { AppFooterComponent } from './components/footer/app.footer.component';
import { AppTopbarComponent } from './components/topbar/app.topbar.component';
import { ClienteModule } from './modules/cliente/cliente.module';
import { EquipamentoModule } from './modules/equipamento/equipamento.module';
import { ReservaModule } from './modules/reserva/reserva.module';
import { SalaModule } from './modules/sala/sala.module';
import { SharedModule } from './shared/shared.module';


@NgModule({
    declarations: [
        AppComponent,
        AppTopbarComponent,
        AppFooterComponent,
        DiarioErrosComponent,
    ],
    imports: [
        BlockUIModule.forRoot({
            message: "Carregando..."
          }),
        BrowserModule,
        BrowserAnimationsModule,
        AppRoutingModule,
        SharedModule,
        HttpClientModule,
        PageNotificationModule,
        BreadcrumbModule,
        ErrorStackModule,
        VersionTagModule,
        SecurityModule.forRoot(environment.auth),
        MenuModule,
        ReservaModule,
        TabViewModule,
        PanelModule,
        TableModule,
        ListboxModule,
        ToolbarModule,
        InputTextModule,
        CheckboxModule,
        ButtonModule,
        RadioButtonModule,
        FormsModule,
		InputTextareaModule,
        DropdownModule,
        SalaModule,
        CardModule,
        DialogModule,
        OrderListModule,
        ClienteModule,
        EquipamentoModule,
        InputNumberModule,
        ToastModule

    ],
    providers: [
        { provide: LocationStrategy, useClass: HashLocationStrategy }
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
