import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarEquipamentosComponent } from './listar-equipamentos.component';

describe('ListarEquipamentosComponent', () => {
  let component: ListarEquipamentosComponent;
  let fixture: ComponentFixture<ListarEquipamentosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListarEquipamentosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListarEquipamentosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
