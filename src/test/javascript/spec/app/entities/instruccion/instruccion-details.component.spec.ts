/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import InstruccionDetailComponent from '@/entities/instruccion/instruccion-details.vue';
import InstruccionClass from '@/entities/instruccion/instruccion-details.component';
import InstruccionService from '@/entities/instruccion/instruccion.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Instruccion Management Detail Component', () => {
    let wrapper: Wrapper<InstruccionClass>;
    let comp: InstruccionClass;
    let instruccionServiceStub: SinonStubbedInstance<InstruccionService>;

    beforeEach(() => {
      instruccionServiceStub = sinon.createStubInstance<InstruccionService>(InstruccionService);

      wrapper = shallowMount<InstruccionClass>(InstruccionDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { instruccionService: () => instruccionServiceStub }
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundInstruccion = { id: '123' };
        instruccionServiceStub.find.resolves(foundInstruccion);

        // WHEN
        comp.retrieveInstruccion('123');
        await comp.$nextTick();

        // THEN
        expect(comp.instruccion).toBe(foundInstruccion);
      });
    });
  });
});
