import { Component, OnInit } from '@angular/core';
import { ListarClientesComponent } from '../../../cliente/components/listar-clientes/listar-clientes.component';
import { ListarEquipamentosComponent } from '../../../equipamento/components/listar-equipamentos/listar-equipamentos.component';
import { ListarReservasComponent } from '../../../reserva/listar-reservas/listar-reservas.component';
import { ListarSalasComponent } from '../../../sala/components/listar-salas/listar-salas.component';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  grafico1: any;
  reservaSalas: ListarReservasComponent;
  salas: ListarSalasComponent
  equipamentos: ListarEquipamentosComponent
  clientes: ListarClientesComponent;
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

  constructor() {
    this.grafico();
    this.graficBarras();
  }

  ngOnInit() {
    this.faturamento();
    this.quantSalas();
    this.salas.listar();
    this.equipamentos.consultarEquipamentos();
    this.reservaSalas.listarReservas();
    this.clientes.listar();
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
        "Multimídia",
        "Palestra"
      ]
    }
  }


  



  quantSalas() {
    this.reservaSalas.listaReservas.forEach(n => {
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
    this.reservaSalas.listaReservas.forEach(n => {
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
