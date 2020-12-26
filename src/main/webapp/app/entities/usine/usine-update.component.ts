import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IUsine, Usine } from 'app/shared/model/usine.model';
import { UsineService } from './usine.service';

@Component({
  selector: 'jhi-usine-update',
  templateUrl: './usine-update.component.html',
})
export class UsineUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nom: [null, [Validators.required]],
    adresse: [null, [Validators.required]],
    pays: [null, [Validators.required]],
  });

  constructor(protected usineService: UsineService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ usine }) => {
      this.updateForm(usine);
    });
  }

  updateForm(usine: IUsine): void {
    this.editForm.patchValue({
      id: usine.id,
      nom: usine.nom,
      adresse: usine.adresse,
      pays: usine.pays,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const usine = this.createFromForm();
    if (usine.id !== undefined) {
      this.subscribeToSaveResponse(this.usineService.update(usine));
    } else {
      this.subscribeToSaveResponse(this.usineService.create(usine));
    }
  }

  private createFromForm(): IUsine {
    return {
      ...new Usine(),
      id: this.editForm.get(['id'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      adresse: this.editForm.get(['adresse'])!.value,
      pays: this.editForm.get(['pays'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUsine>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
