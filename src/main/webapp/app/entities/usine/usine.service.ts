import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IUsine } from 'app/shared/model/usine.model';

type EntityResponseType = HttpResponse<IUsine>;
type EntityArrayResponseType = HttpResponse<IUsine[]>;

@Injectable({ providedIn: 'root' })
export class UsineService {
  public resourceUrl = SERVER_API_URL + 'api/usines';

  constructor(protected http: HttpClient) {}

  create(usine: IUsine): Observable<EntityResponseType> {
    return this.http.post<IUsine>(this.resourceUrl, usine, { observe: 'response' });
  }

  update(usine: IUsine): Observable<EntityResponseType> {
    return this.http.put<IUsine>(this.resourceUrl, usine, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IUsine>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IUsine[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
