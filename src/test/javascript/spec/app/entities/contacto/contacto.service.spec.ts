/* tslint:disable max-line-length */
import axios from 'axios';
import { format } from 'date-fns';

import * as config from '@/shared/config/config';
import { DATE_FORMAT } from '@/shared/date/filters';
import ContactoService from '@/entities/contacto/contacto.service';
import { Contacto, EstadoContacto } from '@/shared/model/contacto.model';

const mockedAxios: any = axios;
jest.mock('axios', () => ({
  get: jest.fn(),
  post: jest.fn(),
  put: jest.fn(),
  delete: jest.fn()
}));

describe('Service Tests', () => {
  describe('Contacto Service', () => {
    let service: ContactoService;
    let elemDefault;
    let currentDate: Date;
    beforeEach(() => {
      service = new ContactoService();
      currentDate = new Date();

      elemDefault = new Contacto('ID', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', EstadoContacto.NUEVO, 'AAAAAAA', currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            lastModified: format(currentDate, DATE_FORMAT)
          },
          elemDefault
        );
        mockedAxios.get.mockReturnValue(Promise.resolve({ data: returnedFromService }));

        return service.find('123').then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });
      it('should create a Contacto', async () => {
        const returnedFromService = Object.assign(
          {
            id: 'ID',
            lastModified: format(currentDate, DATE_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            lastModified: currentDate
          },
          returnedFromService
        );

        mockedAxios.post.mockReturnValue(Promise.resolve({ data: returnedFromService }));
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should update a Contacto', async () => {
        const returnedFromService = Object.assign(
          {
            context: 'BBBBBB',
            correo: 'BBBBBB',
            rfc: 'BBBBBB',
            estado: 'BBBBBB',
            desc: 'BBBBBB',
            lastModified: format(currentDate, DATE_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            lastModified: currentDate
          },
          returnedFromService
        );
        mockedAxios.put.mockReturnValue(Promise.resolve({ data: returnedFromService }));

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });
      it('should return a list of Contacto', async () => {
        const returnedFromService = Object.assign(
          {
            context: 'BBBBBB',
            correo: 'BBBBBB',
            rfc: 'BBBBBB',
            estado: 'BBBBBB',
            desc: 'BBBBBB',
            lastModified: format(currentDate, DATE_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            lastModified: currentDate
          },
          returnedFromService
        );
        mockedAxios.get.mockReturnValue(Promise.resolve([returnedFromService]));
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });
      it('should delete a Contacto', async () => {
        mockedAxios.delete.mockReturnValue(Promise.resolve({ ok: true }));
        return service.delete('123').then(res => {
          expect(res.ok).toBeTruthy();
        });
      });
    });
  });
});
