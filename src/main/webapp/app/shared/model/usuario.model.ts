export interface IUsuario {
  id?: string;
  nombre?: string;
  primerApellido?: string;
  segundoApellido?: string;
  rfc?: string;
}

export class Usuario implements IUsuario {
  constructor(
    public id?: string,
    public nombre?: string,
    public primerApellido?: string,
    public segundoApellido?: string,
    public rfc?: string
  ) {}
}
