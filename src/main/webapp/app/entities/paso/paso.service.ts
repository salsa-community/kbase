import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { IPaso } from '@/shared/model/paso.model';

const baseApiUrl = 'api/pasos';

export default class PasoService {
  public find(id: string): Promise<IPaso> {
    return new Promise<IPaso>(resolve => {
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

  public create(entity: IPaso): Promise<IPaso> {
    return new Promise<IPaso>(resolve => {
      axios.post(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }

  public update(entity: IPaso): Promise<IPaso> {
    return new Promise<IPaso>(resolve => {
      axios.put(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }
}
