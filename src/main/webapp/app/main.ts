// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.common with an alias.
import Vue from 'vue';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import App from './app.vue';
import Vue2Filters from 'vue2-filters';
import router from './router';
import * as config from './shared/config/config';
import * as bootstrapVueConfig from './shared/config/config-bootstrap-vue';
import * as elementVueConfig from './shared/config/config-element-vue';
import * as codehighlightVueConfig from './shared/config/config-code-highlight-vue';

import JhiItemCountComponent from './shared/jhi-item-count.vue';
import AuditsService from './admin/audits/audits.service';

import HealthService from './admin/health/health.service';
import MetricsService from './admin/metrics/metrics.service';
import LogsService from './admin/logs/logs.service';
import ActivateService from './account/activate/activate.service';
import RegisterService from './account/register/register.service';
import UserManagementService from '@/admin/user-management/user-management.service';

import LoginService from './account/login.service';
import AccountService from './account/account.service';

import '../content/scss/vendor.scss';
import AlertService from '@/shared/alert/alert.service';
import TranslationService from '@/locale/translation.service';
import ConfigurationService from '@/admin/configuration/configuration.service';

import MensajeErrorService from '@/entities/mensaje-error/mensaje-error.service';
import InstruccionService from '@/entities/instruccion/instruccion.service';
import LinkService from '@/entities/link/link.service';
import PasoService from '@/entities/paso/paso.service';
import UsuarioService from '@/entities/usuario/usuario.service';
import ContextoService from '@/entities/contexto/contexto.service';
import UnknowIntentsService from '@/entities/unknow-intents/unknow-intents.service';
import ContactoService from '@/entities/contacto/contacto.service';
import EventoService from '@/entities/evento/evento.service';
import ActividadService from '@/entities/actividad/actividad.service';
import ArchivoService from '@/entities/archivo/archivo.service';
// jhipster-needle-add-entity-service-to-main-import - JHipster will import entities services here

import draggable from 'vuedraggable';
import VueTour from 'vue-tour';
import 'vue-tour/dist/vue-tour.css';

Vue.config.productionTip = false;
config.initVueApp(Vue);
config.initFortAwesome(Vue);
bootstrapVueConfig.initBootstrapVue(Vue);
elementVueConfig.initElementVue(Vue);
codehighlightVueConfig.initCodeHighlightVue(Vue);
Vue.use(Vue2Filters);
Vue.component('font-awesome-icon', FontAwesomeIcon);
Vue.component('jhi-item-count', JhiItemCountComponent);
Vue.component('draggable', draggable);
Vue.use(VueTour);
const i18n = config.initI18N(Vue);
const store = config.initVueXStore(Vue);

const alertService = new AlertService(store);
const translationService = new TranslationService(store, i18n);
const loginService = new LoginService();
const accountService = new AccountService(store, translationService, router);

router.beforeEach((to, from, next) => {
  if (!to.matched.length) {
    next('/not-found');
  }

  if (to.meta && to.meta.authorities && to.meta.authorities.length > 0) {
    if (!accountService.hasAnyAuthority(to.meta.authorities)) {
      sessionStorage.setItem('requested-url', to.fullPath);
      next('/forbidden');
    } else {
      window.scrollTo(0, 0);
      next();
    }
  } else {
    window.scrollTo(0, 0);
    // no authorities, so just proceed
    next();
  }
});

/* tslint:disable */
new Vue({
  el: '#app',
  components: { App },
  template: '<App/>',
  router,
  provide: {
    loginService: () => loginService,
    activateService: () => new ActivateService(),
    registerService: () => new RegisterService(),
    userService: () => new UserManagementService(),

    auditsService: () => new AuditsService(),

    healthService: () => new HealthService(),

    configurationService: () => new ConfigurationService(),
    logsService: () => new LogsService(),
    metricsService: () => new MetricsService(),
    alertService: () => alertService,
    translationService: () => translationService,
    mensajeErrorService: () => new MensajeErrorService(),
    instruccionService: () => new InstruccionService(),
    linkService: () => new LinkService(),
    pasoService: () => new PasoService(),
    usuarioService: () => new UsuarioService(),
    contextoService: () => new ContextoService(),
    unknowIntentsService: () => new UnknowIntentsService(),
    contactoService: () => new ContactoService(),
    eventoService: () => new EventoService(),
    actividadService: () => new ActividadService(),
    archivoService: () => new ArchivoService(),
    // jhipster-needle-add-entity-service-to-main - JHipster will import entities services here
    accountService: () => accountService
  },
  i18n,
  store
});
