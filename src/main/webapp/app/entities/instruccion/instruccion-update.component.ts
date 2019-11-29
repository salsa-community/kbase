import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength } from 'vuelidate/lib/validators';

import PasoService from '../paso/paso.service';
import { IPaso } from '@/shared/model/paso.model';

import AlertService from '@/shared/alert/alert.service';
import { IInstruccion, Instruccion } from '@/shared/model/instruccion.model';
import InstruccionService from './instruccion.service';

const validations: any = {
  instruccion: {
    desc: {}
  }
};

@Component({
  validations
})
export default class InstruccionUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('instruccionService') private instruccionService: () => InstruccionService;
  public instruccion: IInstruccion = new Instruccion();

  @Inject('pasoService') private pasoService: () => PasoService;

  public pasos: IPaso[] = [];
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.instruccionId) {
        vm.retrieveInstruccion(to.params.instruccionId);
      }
      vm.initRelationships();
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.instruccion.id) {
      this.instruccionService()
        .update(this.instruccion)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('kbaseApp.instruccion.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.instruccionService()
        .create(this.instruccion)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('kbaseApp.instruccion.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveInstruccion(instruccionId): void {
    this.instruccionService()
      .find(instruccionId)
      .then(res => {
        this.instruccion = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.pasoService()
      .retrieve()
      .then(res => {
        this.pasos = res.data;
      });
  }
}
