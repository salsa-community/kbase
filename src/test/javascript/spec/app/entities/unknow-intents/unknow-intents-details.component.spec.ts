/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import UnknowIntentsDetailComponent from '@/entities/unknow-intents/unknow-intents-details.vue';
import UnknowIntentsClass from '@/entities/unknow-intents/unknow-intents-details.component';
import UnknowIntentsService from '@/entities/unknow-intents/unknow-intents.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('UnknowIntents Management Detail Component', () => {
    let wrapper: Wrapper<UnknowIntentsClass>;
    let comp: UnknowIntentsClass;
    let unknowIntentsServiceStub: SinonStubbedInstance<UnknowIntentsService>;

    beforeEach(() => {
      unknowIntentsServiceStub = sinon.createStubInstance<UnknowIntentsService>(UnknowIntentsService);

      wrapper = shallowMount<UnknowIntentsClass>(UnknowIntentsDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { unknowIntentsService: () => unknowIntentsServiceStub }
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundUnknowIntents = { id: '123' };
        unknowIntentsServiceStub.find.resolves(foundUnknowIntents);

        // WHEN
        comp.retrieveUnknowIntents('123');
        await comp.$nextTick();

        // THEN
        expect(comp.unknowIntents).toBe(foundUnknowIntents);
      });
    });
  });
});
