import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUsine } from 'app/shared/model/usine.model';
import { UsineService } from './usine.service';

@Component({
  templateUrl: './usine-delete-dialog.component.html',
})
export class UsineDeleteDialogComponent {
  usine?: IUsine;

  constructor(protected usineService: UsineService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.usineService.delete(id).subscribe(() => {
      this.eventManager.broadcast('usineListModification');
      this.activeModal.close();
    });
  }
}
