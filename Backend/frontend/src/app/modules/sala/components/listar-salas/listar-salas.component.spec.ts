import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarSalasComponent } from './listar-salas.component';

describe('ListarSalasComponent', () => {
  let component: ListarSalasComponent;
  let fixture: ComponentFixture<ListarSalasComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListarSalasComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListarSalasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
