import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';
import { IEvento, Evento } from '@/shared/model/evento.model';
import EventoService from './evento.service';

const validations: any = {
  evento: {
    nombre: {},
    desc: {}
  }
};

@Component({
  validations
})
export default class EventoUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('eventoService') private eventoService: () => EventoService;
  public evento: IEvento = new Evento();
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.eventoId) {
        vm.retrieveEvento(to.params.eventoId);
      }
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.evento.id) {
      this.eventoService()
        .update(this.evento)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('kbaseApp.evento.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.eventoService()
        .create(this.evento)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('kbaseApp.evento.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveEvento(eventoId): void {
    this.eventoService()
      .find(eventoId)
      .then(res => {
        this.evento = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
