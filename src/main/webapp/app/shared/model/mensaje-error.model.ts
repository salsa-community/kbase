import { ILink } from '@/shared/model/link.model';
import { Instruccion, IInstruccion } from '@/shared/model/instruccion.model';

export interface IMensajeError {
  id?: string;
  clave?: string;
  desc?: string;
  instruccionId?: string;
  instruccion?: IInstruccion;
  links?: ILink[];
}

export class MensajeError implements IMensajeError {
  constructor(
    public id?: string,
    public clave?: string,
    public desc?: string,
    public instruccionId?: string,
    public instruccion?: Instruccion,
    public links?: ILink[]
  ) {}
}
