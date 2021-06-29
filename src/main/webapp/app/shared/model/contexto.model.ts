import { IMensaje } from '@/shared/model/mensaje.model';

export interface IContexto {
  id?: string;
  nombre?: string;
  clave?: string;
  desc?: string;
  descEn?: string;
  organizacion?: string;
  mensajes?: IMensaje[];
}

export class Contexto implements IContexto {
  constructor(
    public id?: string,
    public nombre?: string,
    public clave?: string,
    public desc?: string,
    public descEn?: string,
    public organizacion?: string,
    public mensajes?: IMensaje[]
  ) {
    this.mensajes = [];
  }
}
