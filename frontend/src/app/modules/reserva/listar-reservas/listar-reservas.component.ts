import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MessageService } from 'primeng';
import { SharedClientesService } from 'src/app/services/shared-clientes.service';
import { SharedEquipamentosService } from 'src/app/services/shared-equipamentos.service';
import { SharedSalasService } from 'src/app/services/shared-salas.service';
import { ClienteModel } from 'src/app/shared/model/cliente.model';
import { EquipamentoModel } from 'src/app/shared/model/equipamento.model';
import { ListarEquipamentoModel } from 'src/app/shared/model/listar-equipamento.model';
import { SalaModel } from 'src/app/shared/model/sala.model';
import { ListarReservaModel } from '../models/listar-reserva.model';
import { ReservaService } from '../services/reserva.service';
import { intervalToDuration, format, parseISO } from 'date-fns';
import { CadastrarReservaModel } from 'src/app/shared/model/cadastrar-reserva.model';
@Component({
  selector: 'app-listar-reservas',
  templateUrl: './listar-reservas.component.html',
  styleUrls: ['./listar-reservas.component.css']
})
export class ListarReservasComponent implements OnInit {
  listaReservas: ListarReservaModel[] = [];
  config: boolean;
  salas: SalaModel[];
  clientes: ClienteModel[];


  formulario: FormGroup;

  listaEquipamentosAtual: EquipamentoModel[];
  equipamentos: ListarEquipamentoModel[];
  totalAtual: number;

  constructor(
    private reservaService: ReservaService,
    private messageService: MessageService,
    private sharedSalasServices: SharedSalasService,
    private formBuilder: FormBuilder,
    private sharedClientesService: SharedClientesService,
    private sharedEquipamentosService: SharedEquipamentosService
  ) { }

  ngOnInit(): void {
    this.listarReservas();
    this.criarFormulario();
    //this.abrirModal();
    // const dataInicialParse = parseISO('infoReserva.dataInicial');
    // const dataFinalParse = parseISO('infoReserva.dataFinal');
    // const intervaloDatas = [dataInicialParse, dataFinalParse];
    // this.formulario.get('intervaloDatas').setValue(intervaloDatas);
    // console.log(this.formulario.getRawValue());
    

    // const [dataIni, dataFim] = this.formulario.get('intervaloDatas').value;
    // console.log( format(dataIni, 'yyyy-MM-dd'),  format(dataFim, 'yyyy-MM-dd'));
    
    
  }
  criarFormulario() {
    this.formulario = this.formBuilder.group({
      idCliente: [null, [Validators.required]],
      idSala: [null, [Validators.required]],
      intervaloDatas: [null, [Validators.required]]
    });

    this.formulario.get('idSala').valueChanges.subscribe(idSalaAtual => {
      if (this.salas) {
        const salaSelecionada = this.salas.find(sala => sala.id == idSalaAtual);
        if (salaSelecionada) {
          this.listaEquipamentosAtual = salaSelecionada.equipamentos.map(equipamento => {
            return { ...equipamento, nome: this.equipamentos.find(equ => equ.id == equipamento.idEquipamento).nome }
          })
        } else {
          this.listaEquipamentosAtual = [];
        }
        this.calcularTotal();
      }
    })

    this.formulario.get('intervaloDatas').valueChanges.subscribe(intervalo => {
      this.calcularTotal();
    })


  }

  private calcularTotal() {
    const intervalo = this.formulario.get('intervaloDatas').value;
    if (intervalo) {
      const [dataInicial, dataFinal] = intervalo;
      if (dataInicial && dataFinal) {
        const { days } = intervalToDuration({
          start: dataInicial,
          end: dataFinal
        });
        const { idSala } = this.formulario.getRawValue();
        if (idSala) {
          const sala = this.salas?.find(sl => sl.id == idSala);
          if (sala) {
            this.totalAtual = days * sala.precoDiario;

          }
        }
      }

    }
  }

  listarReservas() {
    this.reservaService.listarReservas().subscribe(listaReservas => {
      this.listaReservas = listaReservas;
    });
  }

  removerReserva(id: number) {
    this.reservaService.removerReserva(id)
      .subscribe(
        () => {
          this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Reserva removida com sucesso.' });
          this.listarReservas();
        }, response => {
          this.messageService.add({ severity: 'error', summary: 'Error', detail: response.error.message })
        });
  }

  listarSalas() {
    this.sharedSalasServices.listarSalas().subscribe(listaSalas => {
      this.salas = listaSalas;
    })
  }

  listarClientes() {
    this.sharedClientesService.listarClientes().subscribe(listaClientes => {
      this.clientes = listaClientes;
    })
  }

  listarEquipamentos() {
    this.sharedEquipamentosService.listarEquipamentos().subscribe(listaEquipamentos => {
      this.equipamentos = listaEquipamentos;
    })
  }

  abrirModal() {
    this.formulario.reset();
    this.listaEquipamentosAtual = [];
    this.listarSalas();
    this.listarClientes();
    this.listarEquipamentos();
    this.config = true;
  }

  editarReserva(id: number){
    this.reservaService.recuperarReserva(id).subscribe(infoReserva =>{
      console.log(infoReserva)
      this.formulario.patchValue(infoReserva)
    })
  }

  enviar(){
    if (this.formulario.valid) {
      const [dataIni, dataFim] = this.formulario.get('intervaloDatas').value;
      const cadastrarReservaDto: CadastrarReservaModel = {
        dataFim: format(dataFim, 'yyyy-MM-dd'),
        dataIni: format(dataIni, 'yyyy-MM-dd'),
        idCliente: this.formulario.get('idCliente').value,
        idSala: this.formulario.get('idSala').value,
        total: this.totalAtual

      }
      this.reservaService.cadastrarReserva(cadastrarReservaDto).subscribe(() => {
        this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Reserva inserida com sucesso.' });
        this.fecharModal();
        this.listarReservas();
      }, response => {
        this.messageService.add({ severity: 'error', summary: 'Error', detail: response.error.message })
      });
    } else {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Formulário inválido, verifique os campos e tente novamente.' })
    }
  }

  fecharModal() {
    this.formulario.reset();
    this.listaEquipamentosAtual = [];
    this.config = false;
  }
}
