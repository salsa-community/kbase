import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { IUsuario } from '@/shared/model/usuario.model';

const baseApiUrl = 'api/usuarios';

export default class UsuarioService {
  public find(id: string): Promise<IUsuario> {
    return new Promise<IUsuario>(resolve => {
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

  public create(entity: IUsuario): Promise<IUsuario> {
    return new Promise<IUsuario>(resolve => {
      axios.post(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }

  public update(entity: IUsuario): Promise<IUsuario> {
    return new Promise<IUsuario>(resolve => {
      axios.put(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }
}
