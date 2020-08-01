export interface IEvento {
  id?: string;
  nombre?: string;
  desc?: string;
}

export class Evento implements IEvento {
  constructor(public id?: string, public nombre?: string, public desc?: string) {}
}
