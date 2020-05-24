export interface IUnknowIntents {
  id?: string;
  word?: string;
  lastModified?: Date;
}

export class UnknowIntents implements IUnknowIntents {
  constructor(public id?: string, public word?: string, public lastModified?: Date) {}
}
