import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';
import { IContacto, Contacto } from '@/shared/model/contacto.model';
import ContactoService from './contacto.service';

const validations: any = {
  contacto: {
    context: {},
    correo: {},
    rfc: {},
    estado: {},
    desc: {},
    lastModified: {}
  }
};

@Component({
  validations
})
export default class ContactoUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('contactoService') private contactoService: () => ContactoService;
  public contacto: IContacto = new Contacto();
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.contactoId) {
        vm.retrieveContacto(to.params.contactoId);
      }
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.contacto.id) {
      this.contactoService()
        .update(this.contacto)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('kbaseApp.contacto.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.contactoService()
        .create(this.contacto)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('kbaseApp.contacto.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveContacto(contactoId): void {
    this.contactoService()
      .find(contactoId)
      .then(res => {
        this.contacto = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
