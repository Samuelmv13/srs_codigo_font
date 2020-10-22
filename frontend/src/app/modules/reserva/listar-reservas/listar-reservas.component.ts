import { Component, OnInit } from '@angular/core';
import { MessageService } from 'primeng';
import { ListarReservaModel } from '../models/listar-reserva.model';
import { ReservaService } from '../services/reserva.service';

@Component({
  selector: 'app-listar-reservas',
  templateUrl: './listar-reservas.component.html',
  styleUrls: ['./listar-reservas.component.css']
})
export class ListarReservasComponent implements OnInit {
  listaReservas: ListarReservaModel[]=[];
  constructor(
    private reservaService: ReservaService,
    private messageService: MessageService
  ) {}

  ngOnInit(): void {
    this.listarReservas();
  }

  listarReservas() {
    this.reservaService.listarReservas().subscribe(listaReservas =>{
        this.listaReservas = listaReservas;
    });
  }

  removerReserva(id: number){
    this.reservaService.removerReserva(id)
    .subscribe(
      ()=>{
      this.messageService.add({severity:'success', summary: 'Success', detail: 'Reserva removida com sucesso.'});
      this.listarReservas();
    },response => {      
      this.messageService.add({severity:'error', summary:'Error',detail:response.error.message})
    });
  }


}
