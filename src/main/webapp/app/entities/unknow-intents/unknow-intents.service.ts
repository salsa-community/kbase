import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { IUnknowIntents } from '@/shared/model/unknow-intents.model';

const baseApiUrl = 'api/unknow-intents';

export default class UnknowIntentsService {
  public find(id: string): Promise<IUnknowIntents> {
    return new Promise<IUnknowIntents>(resolve => {
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

  public create(entity: IUnknowIntents): Promise<IUnknowIntents> {
    return new Promise<IUnknowIntents>(resolve => {
      axios.post(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }

  public update(entity: IUnknowIntents): Promise<IUnknowIntents> {
    return new Promise<IUnknowIntents>(resolve => {
      axios.put(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }
}
