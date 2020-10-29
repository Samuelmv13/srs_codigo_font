import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ReservaService } from 'src/app/modules/reserva/services/reserva.service';
import { InfoReservaModel } from '../models/info-reserva.model';
import { ListarReservaModel } from '../models/listar-reserva.model';

@Component({
  selector: 'app-cadastrar-reserva',
  templateUrl: './cadastrar-reserva.component.html',
  styleUrls: ['./cadastrar-reserva.component.css']
})
export class CadastrarReservaComponent implements OnInit {
  formreserva: FormGroup;
  reserva: InfoReservaModel;
  

  constructor(
    private formBuilder: FormBuilder,
    private reservaService: ReservaService,
    private router: Router,
    private actRouter: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.criarFormulario();
    this.recuperarIdRota();
  }

  recuperarIdRota() {
    const id = this.actRouter.snapshot.params['id'];
    if (id) {
      this.recuperarreserva(id);
    }
  }

  recuperarreserva(id: number) {
    this.reservaService.recuperarReserva(id)
      .subscribe(reserva => {
        this.reserva = reserva;
        this.formreserva.patchValue(reserva);
      })
  }

  criarFormulario() {
    this.formreserva = this.formBuilder.group(
      {
        nome: [null, [Validators.required]],
        idade: [null, [Validators.required]]
      }
    );
  }

  salvar() {

    if (this.formreserva.valid) {
      if (this.isEditar()) {
        this.editarreserva();
      } else {
        this.cadastrarreserva();
      }
    } else {
      console.log('Formulario invalido');
    }

  }

  private editarreserva() {
    
  }

  private cadastrarreserva() {
    this.reservaService.cadastrarReserva(
      {
        dataIni: this.formreserva.get('dataIni').value,
        dataFim: this.formreserva.get('dataFim').value,
        total: this.formreserva.get('total').value,
        idCliente:1,
        idSala:2
      }
    ).subscribe(
      () => {
        console.log('reserva Cadastrado');
        this.router.navigate(['../reservas']);
      },
      () => {
        console.log('Erro ao chamar serviço');
      });
  }

  isEditar(): boolean {
    return this.reserva != null;
  }

}
