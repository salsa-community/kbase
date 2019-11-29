/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import PasoUpdateComponent from '@/entities/paso/paso-update.vue';
import PasoClass from '@/entities/paso/paso-update.component';
import PasoService from '@/entities/paso/paso.service';

import InstruccionService from '@/entities/instruccion/instruccion.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('Paso Management Update Component', () => {
    let wrapper: Wrapper<PasoClass>;
    let comp: PasoClass;
    let pasoServiceStub: SinonStubbedInstance<PasoService>;

    beforeEach(() => {
      pasoServiceStub = sinon.createStubInstance<PasoService>(PasoService);

      wrapper = shallowMount<PasoClass>(PasoUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          pasoService: () => pasoServiceStub,

          instruccionService: () => new InstruccionService()
        }
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: '123' };
        comp.paso = entity;
        pasoServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(pasoServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.paso = entity;
        pasoServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(pasoServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
