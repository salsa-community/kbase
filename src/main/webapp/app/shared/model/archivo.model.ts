export interface IArchivo {
  id?: string;
  nombre?: string;
  datoContentType?: string;
  dato?: any;
}

export class Archivo implements IArchivo {
  constructor(public id?: string, public nombre?: string, public datoContentType?: string, public dato?: any) {}
}
