import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadAlbumComponent } from './cad-album.component';

describe('CadAlbumComponent', () => {
  let component: CadAlbumComponent;
  let fixture: ComponentFixture<CadAlbumComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CadAlbumComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CadAlbumComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
