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
import { InfoReservaModel } from '../models/info-reserva.model';
@Component({
  selector: 'app-listar-reservas',
  templateUrl: './listar-reservas.component.html',
  styleUrls: ['./listar-reservas.component.css']
})
export class ListarReservasComponent implements OnInit {
  listaReservas: ListarReservaModel[] = [];
  config: boolean;
  salas: SalaModel[];
  salaAtual: SalaModel;
  clientes: ClienteModel[];
  clienteAtual: ClienteModel;
  formulario: FormGroup;
  listaEquipamentosAtual: EquipamentoModel[];
  equipamentos: ListarEquipamentoModel[];
  totalAtual: number;
  reservaAtual: InfoReservaModel;

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
    this.listarSalas();
    this.listarClientes();
    this.listarEquipamentos();



  }
  criarFormulario() {
    this.formulario = this.formBuilder.group({
      idCliente: [null, [Validators.required]],
      idSala: [null, [Validators.required]],
      intervaloDatas: [null, [Validators.required]],
      equipamentos: [null]
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
    });


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

  iniciarCadastro() {
    this.listaEquipamentosAtual = [];
    this.formulario.reset();
    this.abrirModal();
    this.totalAtual = 0;
  }

  abrirModal() {
    this.config = true;
  }

  editarReserva(id: number) {
    this.reservaService.recuperarReserva(id).subscribe(infoReserva => {
      this.reservaAtual = infoReserva;
      this.formulario.get('idSala').setValue(this.reservaAtual.idSala);
      this.formulario.get('idCliente').setValue(this.reservaAtual.idCliente);
      this.abrirModal();

      const dataInicialParse = parseISO(this.reservaAtual.dataIni);
      const dataFinalParse = parseISO(this.reservaAtual.dataFim);
      const intervaloDatas = [dataInicialParse, dataFinalParse];
      this.formulario.get('intervaloDatas').setValue(intervaloDatas);
      if (this.reservaAtual.equipamentos?.length) {
        this.reservaAtual.equipamentos = [];
      }
      this.reservaAtual.equipamentos = this.reservaAtual.equipamentos?.filter(eq => !this.listaEquipamentosAtual.some(e => e.idEquipamento == eq.idEquipamento));
      this.formulario.get('equipamentos').setValue(this.reservaAtual.equipamentos.map(eq => {
        return {
          ...eq,
          id: eq.idEquipamento,
          nome: this.equipamentos.find(e => e.id === eq.idEquipamento)?.nome
        }
      }));
    })
  }

  enviar() {
    if (this.formulario.valid) {
      const equipamentosOpcionais: ListarEquipamentoModel[] = this.formulario.get('equipamentos').value || [];
      const [dataIni, dataFim] = this.formulario.get('intervaloDatas').value;
      const CadastrarReservaModel: CadastrarReservaModel = {
        dataFim: format(dataFim, 'yyyy-MM-dd'),
        dataIni: format(dataIni, 'yyyy-MM-dd'),
        total: this.totalAtual,
        idCliente: this.formulario.get('idCliente').value,
        idSala: this.formulario.get('idSala').value,
        equipamentos: [...this.listaEquipamentosAtual, ...equipamentosOpcionais.map<EquipamentoModel>(eq => {
          return {
            idEquipamento: eq.id,
            quantidade: 1,
            idSala: Number(this.formulario.get('idSala').value)
          }
        })]
      }

      if (this.reservaAtual) {
        this.atualizarReserva(CadastrarReservaModel);
      } else {
        this.inserirReserva(CadastrarReservaModel);
      }
    } else {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Formulário inválido, verifique os campos e tente novamente.' })
    }
  }

  private atualizarReserva(CadastrarReservaModel: CadastrarReservaModel) {
    this.reservaService.editarReserva({
      ...CadastrarReservaModel,
      id: this.reservaAtual.id
    }).subscribe(() => {
      this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Reserva atualizada com sucesso.' });
      this.fecharModal();
      this.listarReservas();
    }, response => {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: "Dados inválidos, Tente novamente." });
    });
  }

  private inserirReserva(CadastrarReservaModel: CadastrarReservaModel) {
    this.reservaService.cadastrarReserva(CadastrarReservaModel).subscribe(() => {
      this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Reserva inserida com sucesso.' });
      this.fecharModal();
      this.listarReservas();
    }, response => {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: "Dados inválidos, Tente novamente." });
    });
  }

  fecharModal() {
    this.formulario.reset();
    this.listaEquipamentosAtual = [];
    this.config = false;
  }

  getNomeCliente(id: number) {
    return this.clientes?.find(c => c.id == id)?.nome;
  }

  getSalaDescricao(id: number) {
    return this.salas?.find(sala => sala.id == id)?.descricao;
  }

  visualizarCliente(id: number) {
    const cliente = this.clientes.find(cliente => cliente.id == id);
    if (cliente) {
      this.clienteAtual = cliente;
    }
  }

  visualizarSala(id: number) {
    const sala = this.salas.find(sala => sala.id == id);
    if (sala) {
      this.salaAtual = sala;
    }
  }

}
