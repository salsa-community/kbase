/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import ContactoDetailComponent from '@/entities/contacto/contacto-details.vue';
import ContactoClass from '@/entities/contacto/contacto-details.component';
import ContactoService from '@/entities/contacto/contacto.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Contacto Management Detail Component', () => {
    let wrapper: Wrapper<ContactoClass>;
    let comp: ContactoClass;
    let contactoServiceStub: SinonStubbedInstance<ContactoService>;

    beforeEach(() => {
      contactoServiceStub = sinon.createStubInstance<ContactoService>(ContactoService);

      wrapper = shallowMount<ContactoClass>(ContactoDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { contactoService: () => contactoServiceStub }
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundContacto = { id: '123' };
        contactoServiceStub.find.resolves(foundContacto);

        // WHEN
        comp.retrieveContacto('123');
        await comp.$nextTick();

        // THEN
        expect(comp.contacto).toBe(foundContacto);
      });
    });
  });
});
