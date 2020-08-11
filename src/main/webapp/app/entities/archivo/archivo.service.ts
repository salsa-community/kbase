import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { IArchivo } from '@/shared/model/archivo.model';

const baseApiUrl = 'api/archivos';

export default class ArchivoService {
  public find(id: string): Promise<IArchivo> {
    return new Promise<IArchivo>(resolve => {
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

  public create(entity: IArchivo): Promise<IArchivo> {
    return new Promise<IArchivo>(resolve => {
      axios.post(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }

  public update(entity: IArchivo): Promise<IArchivo> {
    return new Promise<IArchivo>(resolve => {
      axios.put(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }
}
