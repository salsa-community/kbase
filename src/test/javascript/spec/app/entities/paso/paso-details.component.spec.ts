/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import PasoDetailComponent from '@/entities/paso/paso-details.vue';
import PasoClass from '@/entities/paso/paso-details.component';
import PasoService from '@/entities/paso/paso.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Paso Management Detail Component', () => {
    let wrapper: Wrapper<PasoClass>;
    let comp: PasoClass;
    let pasoServiceStub: SinonStubbedInstance<PasoService>;

    beforeEach(() => {
      pasoServiceStub = sinon.createStubInstance<PasoService>(PasoService);

      wrapper = shallowMount<PasoClass>(PasoDetailComponent, { store, i18n, localVue, provide: { pasoService: () => pasoServiceStub } });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundPaso = { id: '123' };
        pasoServiceStub.find.resolves(foundPaso);

        // WHEN
        comp.retrievePaso('123');
        await comp.$nextTick();

        // THEN
        expect(comp.paso).toBe(foundPaso);
      });
    });
  });
});
