/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import ContextoDetailComponent from '@/entities/contexto/contexto-details.vue';
import ContextoClass from '@/entities/contexto/contexto-details.component';
import ContextoService from '@/entities/contexto/contexto.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Contexto Management Detail Component', () => {
    let wrapper: Wrapper<ContextoClass>;
    let comp: ContextoClass;
    let contextoServiceStub: SinonStubbedInstance<ContextoService>;

    beforeEach(() => {
      contextoServiceStub = sinon.createStubInstance<ContextoService>(ContextoService);

      wrapper = shallowMount<ContextoClass>(ContextoDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { contextoService: () => contextoServiceStub }
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundContexto = { id: '123' };
        contextoServiceStub.find.resolves(foundContexto);

        // WHEN
        comp.retrieveContexto('123');
        await comp.$nextTick();

        // THEN
        expect(comp.contexto).toBe(foundContexto);
      });
    });
  });
});
