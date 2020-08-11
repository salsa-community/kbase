/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import ArchivoDetailComponent from '@/entities/archivo/archivo-details.vue';
import ArchivoClass from '@/entities/archivo/archivo-details.component';
import ArchivoService from '@/entities/archivo/archivo.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Archivo Management Detail Component', () => {
    let wrapper: Wrapper<ArchivoClass>;
    let comp: ArchivoClass;
    let archivoServiceStub: SinonStubbedInstance<ArchivoService>;

    beforeEach(() => {
      archivoServiceStub = sinon.createStubInstance<ArchivoService>(ArchivoService);

      wrapper = shallowMount<ArchivoClass>(ArchivoDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { archivoService: () => archivoServiceStub }
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundArchivo = { id: '123' };
        archivoServiceStub.find.resolves(foundArchivo);

        // WHEN
        comp.retrieveArchivo('123');
        await comp.$nextTick();

        // THEN
        expect(comp.archivo).toBe(foundArchivo);
      });
    });
  });
});
