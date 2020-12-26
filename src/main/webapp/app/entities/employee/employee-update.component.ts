import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEmployee, Employee } from 'app/shared/model/employee.model';
import { EmployeeService } from './employee.service';
import { IUsine } from 'app/shared/model/usine.model';
import { UsineService } from 'app/entities/usine/usine.service';

@Component({
  selector: 'jhi-employee-update',
  templateUrl: './employee-update.component.html',
})
export class EmployeeUpdateComponent implements OnInit {
  isSaving = false;
  usines: IUsine[] = [];
  datedenaissanceDp: any;

  editForm = this.fb.group({
    id: [],
    nom: [null, [Validators.required]],
    prenom: [null, [Validators.required]],
    adresse: [null, [Validators.required]],
    fonction: [null, [Validators.required]],
    telephone: [null, [Validators.required]],
    datedenaissance: [null, [Validators.required]],
    usine: [],
  });

  constructor(
    protected employeeService: EmployeeService,
    protected usineService: UsineService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ employee }) => {
      this.updateForm(employee);

      this.usineService.query().subscribe((res: HttpResponse<IUsine[]>) => (this.usines = res.body || []));
    });
  }

  updateForm(employee: IEmployee): void {
    this.editForm.patchValue({
      id: employee.id,
      nom: employee.nom,
      prenom: employee.prenom,
      adresse: employee.adresse,
      fonction: employee.fonction,
      telephone: employee.telephone,
      datedenaissance: employee.datedenaissance,
      usine: employee.usine,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const employee = this.createFromForm();
    if (employee.id !== undefined) {
      this.subscribeToSaveResponse(this.employeeService.update(employee));
    } else {
      this.subscribeToSaveResponse(this.employeeService.create(employee));
    }
  }

  private createFromForm(): IEmployee {
    return {
      ...new Employee(),
      id: this.editForm.get(['id'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      prenom: this.editForm.get(['prenom'])!.value,
      adresse: this.editForm.get(['adresse'])!.value,
      fonction: this.editForm.get(['fonction'])!.value,
      telephone: this.editForm.get(['telephone'])!.value,
      datedenaissance: this.editForm.get(['datedenaissance'])!.value,
      usine: this.editForm.get(['usine'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEmployee>>): void {
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

  trackById(index: number, item: IUsine): any {
    return item.id;
  }
}
