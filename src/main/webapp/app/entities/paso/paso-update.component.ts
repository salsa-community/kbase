import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength } from 'vuelidate/lib/validators';

import InstruccionService from '../instruccion/instruccion.service';
import { IInstruccion } from '@/shared/model/instruccion.model';

import AlertService from '@/shared/alert/alert.service';
import { IPaso, Paso } from '@/shared/model/paso.model';
import PasoService from './paso.service';

const validations: any = {
  paso: {
    paso: {},
    desc: {}
  }
};

@Component({
  validations
})
export default class PasoUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('pasoService') private pasoService: () => PasoService;
  public paso: IPaso = new Paso();

  @Inject('instruccionService') private instruccionService: () => InstruccionService;

  public instruccions: IInstruccion[] = [];
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.pasoId) {
        vm.retrievePaso(to.params.pasoId);
      }
      vm.initRelationships();
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.paso.id) {
      this.pasoService()
        .update(this.paso)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('kbaseApp.paso.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.pasoService()
        .create(this.paso)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('kbaseApp.paso.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrievePaso(pasoId): void {
    this.pasoService()
      .find(pasoId)
      .then(res => {
        this.paso = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.instruccionService()
      .retrieve()
      .then(res => {
        this.instruccions = res.data;
      });
  }
}
