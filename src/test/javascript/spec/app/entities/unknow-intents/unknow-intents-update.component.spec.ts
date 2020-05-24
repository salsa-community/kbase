/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import UnknowIntentsUpdateComponent from '@/entities/unknow-intents/unknow-intents-update.vue';
import UnknowIntentsClass from '@/entities/unknow-intents/unknow-intents-update.component';
import UnknowIntentsService from '@/entities/unknow-intents/unknow-intents.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('UnknowIntents Management Update Component', () => {
    let wrapper: Wrapper<UnknowIntentsClass>;
    let comp: UnknowIntentsClass;
    let unknowIntentsServiceStub: SinonStubbedInstance<UnknowIntentsService>;

    beforeEach(() => {
      unknowIntentsServiceStub = sinon.createStubInstance<UnknowIntentsService>(UnknowIntentsService);

      wrapper = shallowMount<UnknowIntentsClass>(UnknowIntentsUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          unknowIntentsService: () => unknowIntentsServiceStub
        }
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: '123' };
        comp.unknowIntents = entity;
        unknowIntentsServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(unknowIntentsServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.unknowIntents = entity;
        unknowIntentsServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(unknowIntentsServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
