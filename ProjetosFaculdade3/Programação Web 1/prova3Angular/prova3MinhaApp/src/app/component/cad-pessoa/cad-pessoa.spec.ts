import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadPessoa } from './cad-pessoa';

describe('CadPessoa', () => {
  let component: CadPessoa;
  let fixture: ComponentFixture<CadPessoa>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CadPessoa]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CadPessoa);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
