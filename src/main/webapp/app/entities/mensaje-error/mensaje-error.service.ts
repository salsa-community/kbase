import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { IMensajeError } from '@/shared/model/mensaje-error.model';

const baseApiUrl = 'api/mensaje-errors';

export default class MensajeErrorService {
  public find(id: string): Promise<IMensajeError> {
    return new Promise<IMensajeError>(resolve => {
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

  public create(entity: IMensajeError): Promise<IMensajeError> {
    return new Promise<IMensajeError>(resolve => {
      axios.post(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }

  public update(entity: IMensajeError): Promise<IMensajeError> {
    return new Promise<IMensajeError>(resolve => {
      axios.put(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }
}
