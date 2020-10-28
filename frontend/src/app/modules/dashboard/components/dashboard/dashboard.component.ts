import { Component, OnInit } from '@angular/core';
import { SharedClientesService } from 'src/app/services/shared-clientes.service';
import { SharedDashboardService } from 'src/app/services/shared-dashboard.service.ts.service';
import { SharedEquipamentosService } from 'src/app/services/shared-equipamentos.service';
import { SharedSalasService } from 'src/app/services/shared-salas.service';
import { ClienteModel } from 'src/app/shared/model/cliente.model';
import { ListarEquipamentoModel } from 'src/app/shared/model/listar-equipamento.model';
import { ListarReservaModel } from 'src/app/shared/model/listar-reserva.model';
import { SalaModel } from 'src/app/shared/model/sala.model'

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  grafico1: any;
  trabalho: number;
  auditorio: number;
  multimidia: number;
  reuniao: number;
  palestra: number;
  grafico2: any;
  jan: number;
  fev: number;
  mar: number;
  abr: number;
  mai: number;
  jun: number;
  jul: number;
  ago: number;
  set: number;
  out: number;
  nov: number;
  dez: number;
  listaEquipamentos: ListarEquipamentoModel[];
  listaSalas: SalaModel[];
  listaReservas: ListarReservaModel[];
  listaClientes: ClienteModel[];

  constructor(
    private equipamentoService: SharedDashboardService,
    private sharedSalasServices: SharedSalasService,
    private sharedClientesService: SharedClientesService,
    private sharedEquipamentosService: SharedEquipamentosService

  ) {
    this.grafico();
    this.graficBarras();
  }

  ngOnInit() {
    this.faturamento();
    this.quantSalas();
  }

  listarEquipamentos() {
    this.sharedEquipamentosService.listarEquipamentos().subscribe(listaEquipamentos => {
      this.listaEquipamentos = listaEquipamentos;
    })
    return this.listaEquipamentos.length;
  }

  listarSalas() {
    this.sharedSalasServices.listarSalas().subscribe(listaSalas => {
      this.listaSalas = listaSalas;
    })
    return this.listaSalas.length;
  }
  consultarReservas() {
    this.equipamentoService
      .listarReservas()
      .subscribe(listaEquipamentos => {
        this.listaReservas = listaEquipamentos;
      })
      return this.listaReservas.length;
  }

  listarClientes() {
    this.sharedClientesService.listarClientes().subscribe(listaClientes => {
      this.listaClientes = listaClientes;
    })
    return this.listaClientes.length;
  }

  grafico() {
    this.grafico1 = {
      datasets: [{
        data: [
          this.trabalho,
          this.reuniao,
          this.auditorio,
          this.multimidia,
          this.palestra
        ],
        backgroundColor: [
          "#FF6384",
          "#4BC0C0",
          "#FFCE56",
          "#E7E9ED",
          "#36A2EB"
        ],
        label: 'My dataset'
      }],
      labels: [
        "Trabalho",
        "Reunião",
        "Auditório",
        "Vídeo",
        "Palestra"
      ]
    }
  }






  quantSalas() {
    this.listaReservas.forEach(n => {
      switch (n.idSala) {
        case 1:
          this.trabalho + 1;
        case 2:
          this.reuniao += 1;
        case 3:
          this.auditorio += 1;
        case 4:
          this.multimidia += 1;
        case 5:
          this.palestra += 1;
      }
    })
  }

  graficBarras() {
    this.grafico2 = {
      labels: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],
      datasets: [
        {
          label: 'Faturamento',
          backgroundColor: '#9CCC65',
          borderColor: '#7CB342',
          data: [this.jan, this.fev, this.mar, this.abr, this.mai, this.jun,
          this.jul, this.ago, this.set, this.out, this.nov, this.dez]
        }
      ]
    }
  }

  faturamento() {
    this.listaReservas.forEach(n => {
      var mes = n.dataIni.split("/", 2);
      switch (mes[1]) {
        case "01":
          this.jan += n.total;
        case "02":
          this.fev += n.total;
        case "03":
          this.mar += n.total;
        case "04":
          this.abr += n.total;
        case "05":
          this.mai += n.total;
        case "06":
          this.jun += n.total;
        case "07":
          this.jul += n.total;
        case "08":
          this.ago += n.total;
        case "09":
          this.set += n.total;
        case "10":
          this.out += n.total;
        case "11":
          this.nov += n.total;
        case "12":
          this.dez += n.total;

      }
    })
  }
}
