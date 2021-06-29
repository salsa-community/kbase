import { ILink } from '@/shared/model/link.model';
import { Instruccion, IInstruccion } from '@/shared/model/instruccion.model';

export interface IMensajeError {
  id?: string;
  clave?: string;
  desc?: string;
  descEn?: string;
  instruccionId?: string;
  instruccion?: IInstruccion;
  links?: ILink[];
  contextos?: string[];
  hears?: string[];
  orden?: number;
  tipo?: string;
}

export class MensajeError implements IMensajeError {
  constructor(
    public id?: string,
    public clave?: string,
    public desc?: string,
    public descEn?: string,
    public instruccionId?: string,
    public instruccion?: Instruccion,
    public links?: ILink[],
    public contextos?: string[],
    public orden?: number,
    public tipo?: string,
    public hears?: string[]
  ) {
    this.contextos = [];
    this.hears = [];
  }
}
