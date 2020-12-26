import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GestionUsineSharedModule } from 'app/shared/shared.module';
import { UsineComponent } from './usine.component';
import { UsineDetailComponent } from './usine-detail.component';
import { UsineUpdateComponent } from './usine-update.component';
import { UsineDeleteDialogComponent } from './usine-delete-dialog.component';
import { usineRoute } from './usine.route';

@NgModule({
  imports: [GestionUsineSharedModule, RouterModule.forChild(usineRoute)],
  declarations: [UsineComponent, UsineDetailComponent, UsineUpdateComponent, UsineDeleteDialogComponent],
  entryComponents: [UsineDeleteDialogComponent],
})
export class GestionUsineUsineModule {}
