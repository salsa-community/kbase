export interface IContexto {
  id?: string;
  nombre?: string;
  clave?: string;
  desc?: string;
}

export class Contexto implements IContexto {
  constructor(public id?: string, public nombre?: string, public clave?: string, public desc?: string) {}
}
