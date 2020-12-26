import { IEmployee } from 'app/shared/model/employee.model';

export interface IUsine {
  id?: number;
  nom?: string;
  adresse?: string;
  pays?: string;
  employees?: IEmployee[];
}

export class Usine implements IUsine {
  constructor(public id?: number, public nom?: string, public adresse?: string, public pays?: string, public employees?: IEmployee[]) {}
}
