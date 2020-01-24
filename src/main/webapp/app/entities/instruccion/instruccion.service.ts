import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { IInstruccion } from '@/shared/model/instruccion.model';

const baseApiUrl = 'api/instruccions';

export default class InstruccionService {
  public find(id: string): Promise<IInstruccion> {
    return new Promise<IInstruccion>(resolve => {
      axios.get(`${baseApiUrl}/${id}`).then(function(res) {
        resolve(res.data);
      });
    });
  }

  public retrieve(paginationQuery?: any): Promise<any> {
    return new Promise<any>(resolve => {
      axios.get(baseApiUrl + `?${buildPaginationQueryOpts(paginationQuery)}`).then(function(res) {
        resolve(res);
      });
    });
  }

  public delete(id: string): Promise<any> {
    return new Promise<any>(resolve => {
      axios.delete(`${baseApiUrl}/${id}`).then(function(res) {
        resolve(res);
      });
    });
  }

  public create(entity: IInstruccion): Promise<IInstruccion> {
    return new Promise<IInstruccion>(resolve => {
      axios.post(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }

  public update(entity: IInstruccion): Promise<IInstruccion> {
    return new Promise<IInstruccion>(resolve => {
      axios.put(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }
}
