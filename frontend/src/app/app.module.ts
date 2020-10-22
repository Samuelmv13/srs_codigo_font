import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SecurityModule, VersionTagModule } from '@nuvem/angular-base';
import { BreadcrumbModule, ErrorStackModule, MenuModule, PageNotificationModule } from '@nuvem/primeng-components';
import { BlockUIModule } from 'ng-block-ui';
import { OrderListModule } from 'primeng/orderlist';
import { ToolbarModule } from 'primeng/toolbar';
import { environment } from '../environments/environment';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DiarioErrosComponent } from './components/diario-erros/diario-erros.component';
import { BlockUIModule } from 'ng-block-ui';
import { InputTextModule, InputTextareaModule, DropdownModule } from 'primeng';
import { CheckboxModule } from "primeng/checkbox";
import { ButtonModule } from "primeng/button";
import { RadioButtonModule } from "primeng/radiobutton";
import { FormsModule } from '@angular/forms';
import { SalaModule } from './modules/sala/sala.module';
import {CardModule} from 'primeng/card';
import {DialogModule} from 'primeng/dialog';
import { ToolbarModule } from 'primeng/toolbar';
import { OrderListModule } from 'primeng/orderlist';
import { ClienteModule } from './modules/cliente/cliente.module';
import { AppFooterComponent } from './components/footer/app.footer.component';
import { AppTopbarComponent } from './components/topbar/app.topbar.component';
import { EquipamentoModule } from './modules/equipamento/equipamento.module';
import { SharedModule } from './shared/shared.module';
import {InputNumberModule} from 'primeng/inputnumber';
import { ToastModule } from 'primeng/toast';


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
