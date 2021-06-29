export interface IMensaje {
  orden?: number;
  desc?: string;
  descEn?: string;
}

export class Mensaje implements IMensaje {
  constructor(public orden?: number, public desc?: string, public descEn?: string) {}
}
