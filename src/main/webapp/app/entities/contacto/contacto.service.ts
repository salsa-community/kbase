import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { IContacto } from '@/shared/model/contacto.model';

const baseApiUrl = 'api/contactos';

export default class ContactoService {
  public find(id: string): Promise<IContacto> {
    return new Promise<IContacto>(resolve => {
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

  public create(entity: IContacto): Promise<IContacto> {
    return new Promise<IContacto>(resolve => {
      axios.post(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }

  public update(entity: IContacto): Promise<IContacto> {
    return new Promise<IContacto>(resolve => {
      axios.put(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }
}
