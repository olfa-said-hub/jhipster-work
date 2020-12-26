import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IUsine, Usine } from 'app/shared/model/usine.model';
import { UsineService } from './usine.service';
import { UsineComponent } from './usine.component';
import { UsineDetailComponent } from './usine-detail.component';
import { UsineUpdateComponent } from './usine-update.component';

@Injectable({ providedIn: 'root' })
export class UsineResolve implements Resolve<IUsine> {
  constructor(private service: UsineService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IUsine> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((usine: HttpResponse<Usine>) => {
          if (usine.body) {
            return of(usine.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Usine());
  }
}

export const usineRoute: Routes = [
  {
    path: '',
    component: UsineComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'Usines',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: UsineDetailComponent,
    resolve: {
      usine: UsineResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Usines',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: UsineUpdateComponent,
    resolve: {
      usine: UsineResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Usines',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: UsineUpdateComponent,
    resolve: {
      usine: UsineResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Usines',
    },
    canActivate: [UserRouteAccessService],
  },
];
