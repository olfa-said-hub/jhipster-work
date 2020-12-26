import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GestionUsineTestModule } from '../../../test.module';
import { UsineUpdateComponent } from 'app/entities/usine/usine-update.component';
import { UsineService } from 'app/entities/usine/usine.service';
import { Usine } from 'app/shared/model/usine.model';

describe('Component Tests', () => {
  describe('Usine Management Update Component', () => {
    let comp: UsineUpdateComponent;
    let fixture: ComponentFixture<UsineUpdateComponent>;
    let service: UsineService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GestionUsineTestModule],
        declarations: [UsineUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(UsineUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UsineUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UsineService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Usine(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Usine();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
