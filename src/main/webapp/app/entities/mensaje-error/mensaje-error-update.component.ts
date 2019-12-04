import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength } from 'vuelidate/lib/validators';

import InstruccionService from '../instruccion/instruccion.service';
import { IInstruccion } from '@/shared/model/instruccion.model';

import LinkService from '../link/link.service';
import { ILink } from '@/shared/model/link.model';
import { IPaso, Paso } from '@/shared/model/paso.model';

import AlertService from '@/shared/alert/alert.service';
import { IMensajeError, MensajeError } from '@/shared/model/mensaje-error.model';
import MensajeErrorService from './mensaje-error.service';

const validations: any = {
  mensajeError: {
    clave: {
      required
    },
    desc: {
      required
    }
  }
};

@Component({
  validations
})
export default class MensajeErrorUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('mensajeErrorService') private mensajeErrorService: () => MensajeErrorService;
  public mensajeError: any = new MensajeError();

  @Inject('instruccionService') private instruccionService: () => InstruccionService;

  public instruccions: IInstruccion[] = [];

  @Inject('linkService') private linkService: () => LinkService;

  public links: ILink[] = [];
  public isSaving = false;
  public stepId = null;
  public newStep = null;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.mensajeErrorId) {
        vm.retrieveMensajeError(to.params.mensajeErrorId);
      }
      vm.initRelationships();
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.mensajeError.id) {
      this.mensajeErrorService()
        .update(this.mensajeError)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('kbaseApp.mensajeError.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.mensajeErrorService()
        .create(this.mensajeError)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('kbaseApp.mensajeError.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveMensajeError(mensajeErrorId): void {
    this.mensajeErrorService()
      .find(mensajeErrorId)
      .then(res => {
        this.mensajeError = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}

  public prepareRemove(step: IPaso): void {
    this.stepId = step;
  }

  public prepareToSave(step: any): void {
    this.newStep = step;
  }

  public removeStep(): void {
    this.mensajeError.instruccion.pasos.splice(this.mensajeError.instruccion.pasos.indexOf(this.stepId), 1);
    this.closeDialog();
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }

  public saveStep(): void {
    this.mensajeError.instruccion.pasos.splice(this.mensajeError.instruccion.pasos.indexOf(this.stepId), 1, this.newStep);
    this.closeEditStepDialog();
  }

  public closeEditStepDialog(): void {
    (<any>this.$refs.editStep).hide();
  }
}
