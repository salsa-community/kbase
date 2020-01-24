/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import MensajeErrorUpdateComponent from '@/entities/mensaje-error/mensaje-error-update.vue';
import MensajeErrorClass from '@/entities/mensaje-error/mensaje-error-update.component';
import MensajeErrorService from '@/entities/mensaje-error/mensaje-error.service';

import InstruccionService from '@/entities/instruccion/instruccion.service';

import LinkService from '@/entities/link/link.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('MensajeError Management Update Component', () => {
    let wrapper: Wrapper<MensajeErrorClass>;
    let comp: MensajeErrorClass;
    let mensajeErrorServiceStub: SinonStubbedInstance<MensajeErrorService>;

    beforeEach(() => {
      mensajeErrorServiceStub = sinon.createStubInstance<MensajeErrorService>(MensajeErrorService);

      wrapper = shallowMount<MensajeErrorClass>(MensajeErrorUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          mensajeErrorService: () => mensajeErrorServiceStub,

          instruccionService: () => new InstruccionService(),

          linkService: () => new LinkService()
        }
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: '123' };
        comp.mensajeError = entity;
        mensajeErrorServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(mensajeErrorServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.mensajeError = entity;
        mensajeErrorServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(mensajeErrorServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
