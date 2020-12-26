import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { GestionUsineSharedModule } from 'app/shared/shared.module';
import { GestionUsineCoreModule } from 'app/core/core.module';
import { GestionUsineAppRoutingModule } from './app-routing.module';
import { GestionUsineHomeModule } from './home/home.module';
import { GestionUsineEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    GestionUsineSharedModule,
    GestionUsineCoreModule,
    GestionUsineHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    GestionUsineEntityModule,
    GestionUsineAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent],
})
export class GestionUsineAppModule {}
