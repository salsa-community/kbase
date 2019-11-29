/* tslint:disable max-line-length */
import axios from 'axios';

import * as config from '@/shared/config/config';
import {} from '@/shared/date/filters';
import MensajeErrorService from '@/entities/mensaje-error/mensaje-error.service';
import { MensajeError } from '@/shared/model/mensaje-error.model';

const mockedAxios: any = axios;
jest.mock('axios', () => ({
  get: jest.fn(),
  post: jest.fn(),
  put: jest.fn(),
  delete: jest.fn()
}));

describe('Service Tests', () => {
  describe('MensajeError Service', () => {
    let service: MensajeErrorService;
    let elemDefault;
    beforeEach(() => {
      service = new MensajeErrorService();

      elemDefault = new MensajeError('ID', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign({}, elemDefault);
        mockedAxios.get.mockReturnValue(Promise.resolve({ data: returnedFromService }));

        return service.find('123').then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });
      it('should create a MensajeError', async () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID'
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);

        mockedAxios.post.mockReturnValue(Promise.resolve({ data: returnedFromService }));
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should update a MensajeError', async () => {
        const returnedFromService = Object.assign(
          {
            clave: 'BBBBBB',
            desc: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        mockedAxios.put.mockReturnValue(Promise.resolve({ data: returnedFromService }));

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });
      it('should return a list of MensajeError', async () => {
        const returnedFromService = Object.assign(
          {
            clave: 'BBBBBB',
            desc: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        mockedAxios.get.mockReturnValue(Promise.resolve([returnedFromService]));
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });
      it('should delete a MensajeError', async () => {
        mockedAxios.delete.mockReturnValue(Promise.resolve({ ok: true }));
        return service.delete('123').then(res => {
          expect(res.ok).toBeTruthy();
        });
      });
    });
  });
});
