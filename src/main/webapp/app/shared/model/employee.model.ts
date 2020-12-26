import { Moment } from 'moment';
import { IUsine } from 'app/shared/model/usine.model';

export interface IEmployee {
  id?: number;
  nom?: string;
  prenom?: string;
  adresse?: string;
  fonction?: string;
  telephone?: string;
  datedenaissance?: Moment;
  usine?: IUsine;
}

export class Employee implements IEmployee {
  constructor(
    public id?: number,
    public nom?: string,
    public prenom?: string,
    public adresse?: string,
    public fonction?: string,
    public telephone?: string,
    public datedenaissance?: Moment,
    public usine?: IUsine
  ) {}
}
