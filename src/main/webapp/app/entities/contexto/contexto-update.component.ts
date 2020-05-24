import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';
import { IContexto, Contexto } from '@/shared/model/contexto.model';
import ContextoService from './contexto.service';

const validations: any = {
  contexto: {
    nombre: {},
    clave: {},
    desc: {}
  }
};

@Component({
  validations
})
export default class ContextoUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('contextoService') private contextoService: () => ContextoService;
  public contexto: IContexto = new Contexto();
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.contextoId) {
        vm.retrieveContexto(to.params.contextoId);
      }
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.contexto.id) {
      this.contextoService()
        .update(this.contexto)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('kbaseApp.contexto.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.contextoService()
        .create(this.contexto)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('kbaseApp.contexto.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveContexto(contextoId): void {
    this.contextoService()
      .find(contextoId)
      .then(res => {
        this.contexto = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
