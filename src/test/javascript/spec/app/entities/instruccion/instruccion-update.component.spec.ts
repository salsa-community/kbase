/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import InstruccionUpdateComponent from '@/entities/instruccion/instruccion-update.vue';
import InstruccionClass from '@/entities/instruccion/instruccion-update.component';
import InstruccionService from '@/entities/instruccion/instruccion.service';

import PasoService from '@/entities/paso/paso.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('Instruccion Management Update Component', () => {
    let wrapper: Wrapper<InstruccionClass>;
    let comp: InstruccionClass;
    let instruccionServiceStub: SinonStubbedInstance<InstruccionService>;

    beforeEach(() => {
      instruccionServiceStub = sinon.createStubInstance<InstruccionService>(InstruccionService);

      wrapper = shallowMount<InstruccionClass>(InstruccionUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          instruccionService: () => instruccionServiceStub,

          pasoService: () => new PasoService()
        }
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: '123' };
        comp.instruccion = entity;
        instruccionServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(instruccionServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.instruccion = entity;
        instruccionServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(instruccionServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
