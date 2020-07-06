import { Component, Vue, Inject } from 'vue-property-decorator';

import { required, minLength, maxLength } from 'vuelidate/lib/validators';
import { isUnique } from '@/validators/isUniqueCode';

import InstruccionService from '../instruccion/instruccion.service';
import { IInstruccion, Instruccion } from '@/shared/model/instruccion.model';

import ContextoService from '../contexto/contexto.service';
import { IContexto, Contexto } from '@/shared/model/contexto.model';

import LinkService from '../link/link.service';
import { ILink } from '@/shared/model/link.model';
import { IPaso, Paso } from '@/shared/model/paso.model';

import AlertService from '@/shared/alert/alert.service';
import { IMensajeError, MensajeError } from '@/shared/model/mensaje-error.model';
import MensajeErrorService from './mensaje-error.service';

const validations: any = {
  mensajeError: {
    clave: {
      required,
      minLength: minLength(4),
      maxLength: maxLength(50),
      isUnique
    },
    desc: {
      required,
      minLength: minLength(4),
      maxLength: maxLength(500)
    },
    tipo: {
      required
    },
    instruccion: {
      desc: {
        required,
        minLength: minLength(4),
        maxLength: maxLength(500)
      }
    }
  },
  newStep: {
    desc: {
      required,
      minLength: minLength(4),
      maxLength: maxLength(500)
    }
  }
};

@Component({
  validations
})
export default class MensajeErrorUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('mensajeErrorService') private mensajeErrorService: () => MensajeErrorService;
  public mensajeError: IMensajeError = new MensajeError();

  @Inject('instruccionService') private instruccionService: () => InstruccionService;

  public instruccions: IInstruccion[] = [];

  @Inject('linkService') private linkService: () => LinkService;

  @Inject('contextoService') private contextoService: () => ContextoService;

  public contextos: IContexto[] = [];

  public links: ILink[] = [];
  public isSaving = false;
  public stepId = null;
  public newStep = new Paso();
  public dragOptions = {
    animation: 200,
    group: 'description',
    disabled: false,
    ghostClass: 'ghost'
  };

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

  public initMensajeError() {
    this.mensajeError.instruccion = new Instruccion();
    this.mensajeError.instruccion.pasos = [];
  }

  public retrieveMensajeError(mensajeErrorId): void {
    this.mensajeErrorService()
      .find(mensajeErrorId)
      .then(res => {
        this.mensajeError = res;
      });
  }

  public mounted(): void {
    if (!this.mensajeError.instruccion) {
      this.initMensajeError();
    }
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.contextoService()
      .findAll()
      .then(res => {
        this.contextos = res;
      })
      .catch();
  }

  public removeStep(step: IPaso): void {
    this.stepId = step;
    this.mensajeError.instruccion.pasos.splice(this.mensajeError.instruccion.pasos.indexOf(this.stepId), 1);
    this.orderSteps();
    this.closeDialog();
  }

  public prepareToSave(step: any): void {
    this.stepId = step;
    this.newStep.paso = step.paso;
    this.newStep.desc = step.desc;
    (<any>this.$refs.editStep).show();
  }

  public prepareToAddStep() {
    this.newStep.paso = -1;
    this.newStep.desc = '';
    (<any>this.$refs.addStep).show();
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }

  public saveStep(): void {
    const step = new Paso();
    step.paso = this.newStep.paso;
    step.desc = this.newStep.desc;
    this.mensajeError.instruccion.pasos.splice(this.mensajeError.instruccion.pasos.indexOf(this.stepId), 1, step);
    this.closeEditStepDialog();
  }

  public addStep(): void {
    const step = new Paso();
    step.paso = Infinity;
    step.desc = this.newStep.desc;
    this.mensajeError.instruccion.pasos.push(step);
    this.orderSteps();
    this.closeEditStepDialog();
    this.closeAddStepDialog();
  }

  public closeEditStepDialog(): void {
    (<any>this.$refs.editStep).hide();
  }

  public closeAddStepDialog(): void {
    (<any>this.$refs.addStep).hide();
  }

  public orderSteps(): void {
    let count = 1;
    this.mensajeError.instruccion.pasos.forEach(paso => {
      paso.paso = count++;
    });
  }

  get clave() {
    return this.mensajeError.clave;
  }
  set clave(text: string) {
    this.mensajeError.clave = text.toUpperCase();
  }
}
