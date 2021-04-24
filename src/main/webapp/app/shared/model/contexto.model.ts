export interface IContexto {
  id?: string;
  nombre?: string;
  clave?: string;
  desc?: string;
  descEn?: string;
  objetivo?: string;
  objetivoEn?: string;
  organizacion?: string;
  loginMessage?: string;
  loginMessageEn?: string;
  welcomeMessage?: string;
  welcomeMessageEn?: string;
}

export class Contexto implements IContexto {
  constructor(
    public id?: string,
    public nombre?: string,
    public clave?: string,
    public desc?: string,
    public descEn?: string,
    public objetivo?: string,
    public objetivoEn?: string,
    public organizacion?: string,
    public loginMessage?: string,
    public loginMessageEn?: string,
    public welcomeMessage?: string,
    public welcomeMessageEn?: string
  ) {}
}
