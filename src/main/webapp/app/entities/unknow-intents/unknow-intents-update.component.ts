import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';
import { IUnknowIntents, UnknowIntents } from '@/shared/model/unknow-intents.model';
import UnknowIntentsService from './unknow-intents.service';

const validations: any = {
  unknowIntents: {
    word: {},
    lastModified: {}
  }
};

@Component({
  validations
})
export default class UnknowIntentsUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('unknowIntentsService') private unknowIntentsService: () => UnknowIntentsService;
  public unknowIntents: IUnknowIntents = new UnknowIntents();
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.unknowIntentsId) {
        vm.retrieveUnknowIntents(to.params.unknowIntentsId);
      }
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.unknowIntents.id) {
      this.unknowIntentsService()
        .update(this.unknowIntents)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('kbaseApp.unknowIntents.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.unknowIntentsService()
        .create(this.unknowIntents)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('kbaseApp.unknowIntents.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveUnknowIntents(unknowIntentsId): void {
    this.unknowIntentsService()
      .find(unknowIntentsId)
      .then(res => {
        this.unknowIntents = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
