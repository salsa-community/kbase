/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import MensajeErrorDetailComponent from '@/entities/mensaje-error/mensaje-error-details.vue';
import MensajeErrorClass from '@/entities/mensaje-error/mensaje-error-details.component';
import MensajeErrorService from '@/entities/mensaje-error/mensaje-error.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('MensajeError Management Detail Component', () => {
    let wrapper: Wrapper<MensajeErrorClass>;
    let comp: MensajeErrorClass;
    let mensajeErrorServiceStub: SinonStubbedInstance<MensajeErrorService>;

    beforeEach(() => {
      mensajeErrorServiceStub = sinon.createStubInstance<MensajeErrorService>(MensajeErrorService);

      wrapper = shallowMount<MensajeErrorClass>(MensajeErrorDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { mensajeErrorService: () => mensajeErrorServiceStub }
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundMensajeError = { id: '123' };
        mensajeErrorServiceStub.find.resolves(foundMensajeError);

        // WHEN
        comp.retrieveMensajeError('123');
        await comp.$nextTick();

        // THEN
        expect(comp.mensajeError).toBe(foundMensajeError);
      });
    });
  });
});
