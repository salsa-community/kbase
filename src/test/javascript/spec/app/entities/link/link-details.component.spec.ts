/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import LinkDetailComponent from '@/entities/link/link-details.vue';
import LinkClass from '@/entities/link/link-details.component';
import LinkService from '@/entities/link/link.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Link Management Detail Component', () => {
    let wrapper: Wrapper<LinkClass>;
    let comp: LinkClass;
    let linkServiceStub: SinonStubbedInstance<LinkService>;

    beforeEach(() => {
      linkServiceStub = sinon.createStubInstance<LinkService>(LinkService);

      wrapper = shallowMount<LinkClass>(LinkDetailComponent, { store, i18n, localVue, provide: { linkService: () => linkServiceStub } });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundLink = { id: '123' };
        linkServiceStub.find.resolves(foundLink);

        // WHEN
        comp.retrieveLink('123');
        await comp.$nextTick();

        // THEN
        expect(comp.link).toBe(foundLink);
      });
    });
  });
});
