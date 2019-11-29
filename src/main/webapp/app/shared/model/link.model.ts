export interface ILink {
  id?: string;
  url?: string;
  mensajeErrorId?: string;
}

export class Link implements ILink {
  constructor(public id?: string, public url?: string, public mensajeErrorId?: string) {}
}
