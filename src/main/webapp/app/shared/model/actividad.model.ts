export interface IActividad {
  id?: string;
  contexto?: string;
  valor?: string;
  desc?: string;
  fecha?: Date;
  eventoId?: string;
}

export class Actividad implements IActividad {
  constructor(
    public id?: string,
    public contexto?: string,
    public valor?: string,
    public desc?: string,
    public fecha?: Date,
    public eventoId?: string
  ) {}
}
