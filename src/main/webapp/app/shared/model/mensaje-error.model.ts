import { ILink } from '@/shared/model/link.model';
import { Instruccion, IInstruccion } from '@/shared/model/instruccion.model';
import { IMensaje } from '@/shared/model/mensaje.model';

export interface IMensajeError {
  id?: string;
  clave?: string;
  instruccion?: IInstruccion;
  links?: ILink[];
  contextos?: string[];
  hears?: string[];
  orden?: number;
  tipo?: string;
  mensajes?: IMensaje[];
}

export class MensajeError implements IMensajeError {
  constructor(
    public id?: string,
    public clave?: string,
    public desc?: string,
    public descEn?: string,
    public instruccion?: Instruccion,
    public links?: ILink[],
    public contextos?: string[],
    public orden?: number,
    public tipo?: string,
    public hears?: string[],
    public mensajes?: IMensaje[]
  ) {
    this.contextos = [];
    this.hears = [];
    this.mensajes = [];
  }
}
