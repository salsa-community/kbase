import { ILink } from '@/shared/model/link.model';

export interface IMensajeError {
  id?: string;
  clave?: string;
  desc?: string;
  instruccionId?: string;
  links?: ILink[];
}

export class MensajeError implements IMensajeError {
  constructor(public id?: string, public clave?: string, public desc?: string, public instruccionId?: string, public links?: ILink[]) {}
}
