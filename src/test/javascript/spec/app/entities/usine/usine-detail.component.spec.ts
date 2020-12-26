import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GestionUsineTestModule } from '../../../test.module';
import { UsineDetailComponent } from 'app/entities/usine/usine-detail.component';
import { Usine } from 'app/shared/model/usine.model';

describe('Component Tests', () => {
  describe('Usine Management Detail Component', () => {
    let comp: UsineDetailComponent;
    let fixture: ComponentFixture<UsineDetailComponent>;
    const route = ({ data: of({ usine: new Usine(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionUsineTestModule],
        declarations: [UsineDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(UsineDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(UsineDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load usine on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.usine).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
