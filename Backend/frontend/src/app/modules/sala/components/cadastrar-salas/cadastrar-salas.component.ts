import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ConfirmationService, MessageService } from 'primeng';
import { salaModel } from '../../models/sala.model';
import { salaEditarModel } from '../../models/salaEditar.model';
import { SalaService } from '../../services/sala.service';

@Component({
  selector: 'app-cadastrar-salas',
  templateUrl: './cadastrar-salas.component.html',
  styleUrls: ['./cadastrar-salas.component.css'],
  providers: [ConfirmationService],
})
export class CadastrarSalasComponent implements OnInit {

  listaSalaDialog: boolean;

  listaSalas: salaModel[];

  listaSala: salaModel;

  salas : salaEditarModel[];

  sala : salaEditarModel;

  displayForm = false;

  constructor(
    private  salaServices: SalaService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService
  ) { }

  ngOnInit(): void {
    this.salaServices.listarSalas()
    .subscribe(listaSalas =>{
      this.listaSalas = listaSalas;
    });
  }

  salaForm = new FormGroup({
    id: new FormControl(""),
    descricao: new FormControl(""),
    idTipoSala: new FormControl(""),
    capacidadePessoas: new FormControl(""),
    precoDiario: new FormControl(""),
    disponivel: new FormControl("", Validators.required),
    equipamentos: new FormControl(""),
  });

  editarSala(listaSala){
    this.salaForm.setValue({
      id: listaSala.id,
      descricao: listaSala.descricao,
      idTipoSala: listaSala.idTipoSala,
      capacidadePessoas: listaSala.capacidadePessoas,
      precoDiario: listaSala.precoDiario,
      disponivel: listaSala.disponivel,
      equipamentos: listaSala.equipamentos,
    });
  }

  handleSubmit(value){
    this.salaServices.editarSala(value).subscribe();

    if (!value.id) {this.salas.push(value);
    } else {
      const index = this.salas.findIndex((e) => e.id === value.id);
      this.salas[index] = value;
    }

    this.displayForm = false;

    this.salaForm.reset();
  }

  adicionarSala(listaSala){
    this.salaServices.salvarSala(listaSala).subscribe();

    if(!listaSala.id){
      this.salas.push(listaSala);
    } else {
      const index = this.salas.findIndex((e) => e.id === listaSala.id);
      this.salas[index] = listaSala;
    }
    this.displayForm = false;

    this.salaForm.reset();
  }

  deletarSala(listaSala){
    this.confirmationService.confirm({
      message: "Tem certeza de que deseja excluir a sala",
      header: "Confirmar exclusão",
      icon: "pi pi-exclamation-triangle",
      accept: () => {
        this.salaServices.deletarSala(listaSala.id).subscribe();
        this.salas = this.salas.filter((valor) => valor.id !== this.sala.id);
      }
    })
  }

  showForm(){
    this.salaForm.reset();
    this.displayForm = true;
  }

}
