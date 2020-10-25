import { Variable } from '@angular/compiler/src/render3/r3_ast';
import { Component, OnInit } from '@angular/core';
import { ListarReservasComponent } from '../../reserva/listar-reservas/listar-reservas.component';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  data: any;
  reservaSalas: ListarReservasComponent;
  trabalho: number;
  auditorio: number;
  multimidia: number;
  reuniao: number;
  palestra: number;
  barChartData: any;
  jan:number;
  fev:number;
  mar:number;
  abr:number;
  mai:number;
  jun:number;
  jul:number;
  ago:number;
  set:number;
  out:number;
  nov:number;
  dez:number;



  ngOnInit() {
 
    this.quantSalas();
   
  }

  grafico() {
    this.data = {
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
      this.barChartData = {
        labels: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],
        datasets: [
          {
            label: 'Faturamento',
            backgroundColor: '#9CCC65',
            borderColor: '#7CB342',
            data: [28, 48, 40, 19, 86, 27, 90, 4, 5, 6, 5, 3]
          }
        ]
      }
    }

    graficCat(){
      this.reservaSalas.listaReservas.forEach(n => {
      })
    }
}
