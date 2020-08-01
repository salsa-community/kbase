/* tslint:disable max-line-length */
import axios from 'axios';
import { format } from 'date-fns';

import * as config from '@/shared/config/config';
import { DATE_TIME_FORMAT } from '@/shared/date/filters';
import ActividadService from '@/entities/actividad/actividad.service';
import { Actividad } from '@/shared/model/actividad.model';

const mockedAxios: any = axios;
jest.mock('axios', () => ({
  get: jest.fn(),
  post: jest.fn(),
  put: jest.fn(),
  delete: jest.fn()
}));

describe('Service Tests', () => {
  describe('Actividad Service', () => {
    let service: ActividadService;
    let elemDefault;
    let currentDate: Date;
    beforeEach(() => {
      service = new ActividadService();
      currentDate = new Date();

      elemDefault = new Actividad('ID', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            fecha: format(currentDate, DATE_TIME_FORMAT)
          },
          elemDefault
        );
        mockedAxios.get.mockReturnValue(Promise.resolve({ data: returnedFromService }));

        return service.find('123').then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });
      it('should create a Actividad', async () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
            fecha: format(currentDate, DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            fecha: currentDate
          },
          returnedFromService
        );

        mockedAxios.post.mockReturnValue(Promise.resolve({ data: returnedFromService }));
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should update a Actividad', async () => {
        const returnedFromService = Object.assign(
          {
            contexto: 'BBBBBB',
            valor: 'BBBBBB',
            desc: 'BBBBBB',
            fecha: format(currentDate, DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fecha: currentDate
          },
          returnedFromService
        );
        mockedAxios.put.mockReturnValue(Promise.resolve({ data: returnedFromService }));

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });
      it('should return a list of Actividad', async () => {
        const returnedFromService = Object.assign(
          {
            contexto: 'BBBBBB',
            valor: 'BBBBBB',
            desc: 'BBBBBB',
            fecha: format(currentDate, DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            fecha: currentDate
          },
          returnedFromService
        );
        mockedAxios.get.mockReturnValue(Promise.resolve([returnedFromService]));
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });
      it('should delete a Actividad', async () => {
        mockedAxios.delete.mockReturnValue(Promise.resolve({ ok: true }));
        return service.delete('123').then(res => {
          expect(res.ok).toBeTruthy();
        });
      });
    });
  });
});
