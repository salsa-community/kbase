import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength } from 'vuelidate/lib/validators';

import MensajeErrorService from '../mensaje-error/mensaje-error.service';
import { IMensajeError } from '@/shared/model/mensaje-error.model';

import AlertService from '@/shared/alert/alert.service';
import { ILink, Link } from '@/shared/model/link.model';
import LinkService from './link.service';

const validations: any = {
  link: {
    url: {
      required
    }
  }
};

@Component({
  validations
})
export default class LinkUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('linkService') private linkService: () => LinkService;
  public link: ILink = new Link();

  @Inject('mensajeErrorService') private mensajeErrorService: () => MensajeErrorService;

  public mensajeErrors: IMensajeError[] = [];
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.linkId) {
        vm.retrieveLink(to.params.linkId);
      }
      vm.initRelationships();
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.link.id) {
      this.linkService()
        .update(this.link)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('kbaseApp.link.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.linkService()
        .create(this.link)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('kbaseApp.link.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveLink(linkId): void {
    this.linkService()
      .find(linkId)
      .then(res => {
        this.link = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.mensajeErrorService()
      .retrieve()
      .then(res => {
        this.mensajeErrors = res.data;
      });
  }
}
