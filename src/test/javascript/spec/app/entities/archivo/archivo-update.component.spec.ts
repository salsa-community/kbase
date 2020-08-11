/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import ArchivoUpdateComponent from '@/entities/archivo/archivo-update.vue';
import ArchivoClass from '@/entities/archivo/archivo-update.component';
import ArchivoService from '@/entities/archivo/archivo.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('Archivo Management Update Component', () => {
    let wrapper: Wrapper<ArchivoClass>;
    let comp: ArchivoClass;
    let archivoServiceStub: SinonStubbedInstance<ArchivoService>;

    beforeEach(() => {
      archivoServiceStub = sinon.createStubInstance<ArchivoService>(ArchivoService);

      wrapper = shallowMount<ArchivoClass>(ArchivoUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          archivoService: () => archivoServiceStub
        }
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: '123' };
        comp.archivo = entity;
        archivoServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(archivoServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.archivo = entity;
        archivoServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(archivoServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
