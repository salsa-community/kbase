import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { IContexto } from '@/shared/model/contexto.model';

const baseApiUrl = 'api/contextos';
const baseCatalog = 'api/catalogos/contextos';

export default class ContextoService {
  public find(id: string): Promise<IContexto> {
    return new Promise<IContexto>(resolve => {
      axios.get(`${baseApiUrl}/${id}`).then(function(res) {
        resolve(res.data);
      });
    });
  }

  public findAll(): Promise<any> {
    return new Promise<any>(resolve => {
      axios.get(`${baseCatalog}`).then(function(res) {
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

  public create(entity: IContexto): Promise<IContexto> {
    return new Promise<IContexto>(resolve => {
      axios.post(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }

  public update(entity: IContexto): Promise<IContexto> {
    return new Promise<IContexto>(resolve => {
      axios.put(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }
}
