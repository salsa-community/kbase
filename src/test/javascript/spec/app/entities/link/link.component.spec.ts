/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import LinkComponent from '@/entities/link/link.vue';
import LinkClass from '@/entities/link/link.component';
import LinkService from '@/entities/link/link.service';

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
  describe('Link Management Component', () => {
    let wrapper: Wrapper<LinkClass>;
    let comp: LinkClass;
    let linkServiceStub: SinonStubbedInstance<LinkService>;

    beforeEach(() => {
      linkServiceStub = sinon.createStubInstance<LinkService>(LinkService);
      linkServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<LinkClass>(LinkComponent, {
        store,
        i18n,
        localVue,
        stubs: { jhiItemCount: true, bPagination: true, bModal: bModalStub as any },
        provide: {
          alertService: () => new AlertService(store),
          linkService: () => linkServiceStub
        }
      });
      comp = wrapper.vm;
    });

    it('should be a Vue instance', () => {
      expect(wrapper.isVueInstance()).toBeTruthy();
    });

    it('Should call load all on init', async () => {
      // GIVEN
      linkServiceStub.retrieve.resolves({ headers: {}, data: [{ id: '123' }] });

      // WHEN
      comp.retrieveAllLinks();
      await comp.$nextTick();

      // THEN
      expect(linkServiceStub.retrieve.called).toBeTruthy();
      expect(comp.links[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });

    it('should load a page', async () => {
      // GIVEN
      linkServiceStub.retrieve.resolves({ headers: {}, data: [{ id: '123' }] });
      comp.previousPage = 1;

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();

      // THEN
      expect(linkServiceStub.retrieve.called).toBeTruthy();
      expect(comp.links[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });

    it('should not load a page if the page is the same as the previous page', () => {
      // GIVEN
      linkServiceStub.retrieve.reset();
      comp.previousPage = 1;

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(linkServiceStub.retrieve.called).toBeFalsy();
    });

    it('should re-initialize the page', async () => {
      // GIVEN
      linkServiceStub.retrieve.reset();
      linkServiceStub.retrieve.resolves({ headers: {}, data: [{ id: '123' }] });

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();
      comp.clear();
      await comp.$nextTick();

      // THEN
      expect(linkServiceStub.retrieve.callCount).toEqual(3);
      expect(comp.page).toEqual(1);
      expect(comp.links[0]).toEqual(jasmine.objectContaining({ id: '123' }));
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
      linkServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: '123' });
      comp.removeLink();
      await comp.$nextTick();

      // THEN
      expect(linkServiceStub.delete.called).toBeTruthy();
      expect(linkServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
