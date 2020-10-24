import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { ConfirmationService, MessageService } from 'primeng/api';
import { equipamentoModel } from '../../models/equipamento.model';
import { salaModel } from '../../models/sala.model';
import { salaEditarModel } from '../../models/salaEditar.model';
import { SalaService } from '../../services/sala.service';

@Component({
  selector: 'app-listar-salas',
  templateUrl: './listar-salas.component.html',
  styleUrls: ['./listar-salas.component.css'],
  providers: [ConfirmationService, MessageService],
})
export class ListarSalasComponent implements OnInit {

  listaSalaDialog: boolean;

  selecionarEquipamentoDialog: boolean;

  selecionarSalaEquipamentoDialog: boolean;

  listaSalas: salaModel[];

  listaSala: salaModel;

  salas: salaEditarModel[];

  sala: salaEditarModel;

  selectedSala: salaEditarModel[];

  equipamentoSala: equipamentoModel[];

  displayForm = false;

  constructor(
    private salaServices: SalaService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService
  ) { }

  ngOnInit(): void {
    this.listar();
  }

  listar() {
    this.salaServices.listarSalas()
      .subscribe(listaSalas => {
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

  editarSala(listaSala) {
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

  handleSubmit(value) {
    this.salaServices.editarSala(value).subscribe();

    if (!value.id) {
      this.salas.push(value);
    } else {
      const index = this.salas.findIndex((e) => e.id === value.id);
      this.salas[index] = value;
    }

    this.displayForm = false;

    this.salaForm.reset();
  }

  adicionarSala(listaSala) {
    this.salaServices.salvarSala(listaSala).subscribe();

    if (!listaSala.id) {
      this.salas.push(listaSala);
    } else {
      const index = this.salas.findIndex((e) => e.id === listaSala.id);
      this.salas[index] = listaSala;
    }
    this.displayForm = false;

    this.salaForm.reset();
  }

  deletarSala(sala): void {
    // this.confirmationService.confirm({
    //     message:
    //         "Tem certeza que desejar excluir a sala com a descrição " + sala.descricao,
    //     header: "Confirmar exclusão",
    //     icon: "pi pi-exclamation-triangle",
    //     accept: () => {
            this.salaServices.deletarSala(sala.id).subscribe(() => {});
            this.listaSalas = this.listaSalas.filter(
                (valor) => valor.id !== sala.id
            );
            this.messageService.add({severity:"success" , summary: "Sala Deletada"})
        // },
    // });
}

  showForm() {

    this.listaSalaDialog = !this.listaSalaDialog
    this.salaForm.reset();
    this.displayForm = true;
  }

  getTipo(n: number) {
    switch (n) {
      case 1:
        return "Reunião";
      case 2:
        return "Trabalho";
      case 3:
        return "Vídeo";
      case 4:
        return "Palestra";
      case 5:
        return "Auditório";
      default:
        return "Selecionar Tipo";
    }
  }
}
