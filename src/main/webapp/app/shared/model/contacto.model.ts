export const enum EstadoContacto {
  NUEVO = 'NUEVO',
  PENDIENTE = 'PENDIENTE',
  CONTACTADO = 'CONTACTADO'
}

export interface IContacto {
  id?: string;
  context?: string;
  correo?: string;
  rfc?: string;
  estado?: EstadoContacto;
  desc?: string;
  lastModified?: Date;
}

export class Contacto implements IContacto {
  constructor(
    public id?: string,
    public context?: string,
    public correo?: string,
    public rfc?: string,
    public estado?: EstadoContacto,
    public desc?: string,
    public lastModified?: Date
  ) {}
}
