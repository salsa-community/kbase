/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import ActividadDetailComponent from '@/entities/actividad/actividad-details.vue';
import ActividadClass from '@/entities/actividad/actividad-details.component';
import ActividadService from '@/entities/actividad/actividad.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Actividad Management Detail Component', () => {
    let wrapper: Wrapper<ActividadClass>;
    let comp: ActividadClass;
    let actividadServiceStub: SinonStubbedInstance<ActividadService>;

    beforeEach(() => {
      actividadServiceStub = sinon.createStubInstance<ActividadService>(ActividadService);

      wrapper = shallowMount<ActividadClass>(ActividadDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { actividadService: () => actividadServiceStub }
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundActividad = { id: '123' };
        actividadServiceStub.find.resolves(foundActividad);

        // WHEN
        comp.retrieveActividad('123');
        await comp.$nextTick();

        // THEN
        expect(comp.actividad).toBe(foundActividad);
      });
    });
  });
});
