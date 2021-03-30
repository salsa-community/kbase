import { IPaso } from '@/shared/model/paso.model';

export interface IInstruccion {
  id?: string;
  desc?: string;
  descEn?: string;
  pasos?: IPaso[];
}

export class Instruccion implements IInstruccion {
  constructor(public id?: string, public desc?: string, public descEn?: string, public pasos?: IPaso[]) {}
}
