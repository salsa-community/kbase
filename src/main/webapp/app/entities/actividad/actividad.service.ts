import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { IActividad } from '@/shared/model/actividad.model';

const baseApiUrl = 'api/actividads';

export default class ActividadService {
  public find(id: string): Promise<IActividad> {
    return new Promise<IActividad>(resolve => {
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

  public create(entity: IActividad): Promise<IActividad> {
    return new Promise<IActividad>(resolve => {
      axios.post(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }

  public update(entity: IActividad): Promise<IActividad> {
    return new Promise<IActividad>(resolve => {
      axios.put(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }

  public loadReporte(): Promise<any> {
    return new Promise<IActividad>(resolve => {
      axios.get(`${baseApiUrl}/reportes`).then(function(res) {
        resolve(res.data);
      });
    });
  }

  public loadExcel(paginationQuery?: any): Promise<any> {
    return new Promise<any>(resolve => {
      axios.get(baseApiUrl + `/reportes/excel?${buildPaginationQueryOpts(paginationQuery)}`).then(function(res) {
        resolve(res);
      });
    });
  }
}
