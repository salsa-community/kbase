import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength } from 'vuelidate/lib/validators';
import format from 'date-fns/format';
import parse from 'date-fns/parse';
import parseISO from 'date-fns/parseISO';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import EventoService from '../evento/evento.service';
import { IEvento } from '@/shared/model/evento.model';

import AlertService from '@/shared/alert/alert.service';
import { IActividad, Actividad } from '@/shared/model/actividad.model';
import ActividadService from './actividad.service';

const validations: any = {
  actividad: {
    contexto: {},
    valor: {},
    desc: {},
    fecha: {}
  }
};

@Component({
  validations
})
export default class ActividadUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('actividadService') private actividadService: () => ActividadService;
  public actividad: IActividad = new Actividad();

  @Inject('eventoService') private eventoService: () => EventoService;

  public eventos: IEvento[] = [];
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.actividadId) {
        vm.retrieveActividad(to.params.actividadId);
      }
      vm.initRelationships();
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.actividad.id) {
      this.actividadService()
        .update(this.actividad)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('kbaseApp.actividad.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.actividadService()
        .create(this.actividad)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('kbaseApp.actividad.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public convertDateTimeFromServer(date: Date): string {
    if (date) {
      return format(date, DATE_TIME_LONG_FORMAT);
    }
    return null;
  }

  public updateInstantField(field, event) {
    if (event.target.value) {
      this.actividad[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.actividad[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.actividad[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.actividad[field] = null;
    }
  }

  public retrieveActividad(actividadId): void {
    this.actividadService()
      .find(actividadId)
      .then(res => {
        res.fecha = new Date(res.fecha);
        this.actividad = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.eventoService()
      .retrieve()
      .then(res => {
        this.eventos = res.data;
      });
  }
}
