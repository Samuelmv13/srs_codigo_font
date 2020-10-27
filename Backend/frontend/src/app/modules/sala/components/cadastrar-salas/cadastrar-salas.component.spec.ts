import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastrarSalasComponent } from './cadastrar-salas.component';

describe('CadastrarSalasComponent', () => {
  let component: CadastrarSalasComponent;
  let fixture: ComponentFixture<CadastrarSalasComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CadastrarSalasComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CadastrarSalasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
