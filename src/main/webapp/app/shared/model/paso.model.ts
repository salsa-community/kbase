export interface IPaso {
  id?: string;
  paso?: number;
  desc?: string;
  instruccionId?: string;
}

export class Paso implements IPaso {
  constructor(public id?: string, public paso?: number, public desc?: string, public instruccionId?: string) {}
}
