/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import UnknowIntentsComponent from '@/entities/unknow-intents/unknow-intents.vue';
import UnknowIntentsClass from '@/entities/unknow-intents/unknow-intents.component';
import UnknowIntentsService from '@/entities/unknow-intents/unknow-intents.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-alert', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {}
  }
};

describe('Component Tests', () => {
  describe('UnknowIntents Management Component', () => {
    let wrapper: Wrapper<UnknowIntentsClass>;
    let comp: UnknowIntentsClass;
    let unknowIntentsServiceStub: SinonStubbedInstance<UnknowIntentsService>;

    beforeEach(() => {
      unknowIntentsServiceStub = sinon.createStubInstance<UnknowIntentsService>(UnknowIntentsService);
      unknowIntentsServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<UnknowIntentsClass>(UnknowIntentsComponent, {
        store,
        i18n,
        localVue,
        stubs: { jhiItemCount: true, bPagination: true, bModal: bModalStub as any },
        provide: {
          alertService: () => new AlertService(store),
          unknowIntentsService: () => unknowIntentsServiceStub
        }
      });
      comp = wrapper.vm;
    });

    it('should be a Vue instance', () => {
      expect(wrapper.isVueInstance()).toBeTruthy();
    });

    it('Should call load all on init', async () => {
      // GIVEN
      unknowIntentsServiceStub.retrieve.resolves({ headers: {}, data: [{ id: '123' }] });

      // WHEN
      comp.retrieveAllUnknowIntentss();
      await comp.$nextTick();

      // THEN
      expect(unknowIntentsServiceStub.retrieve.called).toBeTruthy();
      expect(comp.unknowIntents[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });

    it('should load a page', async () => {
      // GIVEN
      unknowIntentsServiceStub.retrieve.resolves({ headers: {}, data: [{ id: '123' }] });
      comp.previousPage = 1;

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();

      // THEN
      expect(unknowIntentsServiceStub.retrieve.called).toBeTruthy();
      expect(comp.unknowIntents[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });

    it('should not load a page if the page is the same as the previous page', () => {
      // GIVEN
      unknowIntentsServiceStub.retrieve.reset();
      comp.previousPage = 1;

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(unknowIntentsServiceStub.retrieve.called).toBeFalsy();
    });

    it('should re-initialize the page', async () => {
      // GIVEN
      unknowIntentsServiceStub.retrieve.reset();
      unknowIntentsServiceStub.retrieve.resolves({ headers: {}, data: [{ id: '123' }] });

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();
      comp.clear();
      await comp.$nextTick();

      // THEN
      expect(unknowIntentsServiceStub.retrieve.callCount).toEqual(3);
      expect(comp.page).toEqual(1);
      expect(comp.unknowIntents[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,desc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // GIVEN
      comp.propOrder = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,desc', 'id']);
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      unknowIntentsServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: '123' });
      comp.removeUnknowIntents();
      await comp.$nextTick();

      // THEN
      expect(unknowIntentsServiceStub.delete.called).toBeTruthy();
      expect(unknowIntentsServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
