import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUsine } from 'app/shared/model/usine.model';

@Component({
  selector: 'jhi-usine-detail',
  templateUrl: './usine-detail.component.html',
})
export class UsineDetailComponent implements OnInit {
  usine: IUsine | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ usine }) => (this.usine = usine));
  }

  previousState(): void {
    window.history.back();
  }
}
