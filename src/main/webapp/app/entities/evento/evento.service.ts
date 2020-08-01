import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { IEvento } from '@/shared/model/evento.model';

const baseApiUrl = 'api/eventos';

export default class EventoService {
  public find(id: string): Promise<IEvento> {
    return new Promise<IEvento>(resolve => {
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

  public create(entity: IEvento): Promise<IEvento> {
    return new Promise<IEvento>(resolve => {
      axios.post(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }

  public update(entity: IEvento): Promise<IEvento> {
    return new Promise<IEvento>(resolve => {
      axios.put(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }
}
