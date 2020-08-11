import { Component, Inject } from 'vue-property-decorator';

import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import { numeric, required, minLength, maxLength } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';
import { IArchivo, Archivo } from '@/shared/model/archivo.model';
import ArchivoService from './archivo.service';

const validations: any = {
  archivo: {
    nombre: {},
    dato: {}
  }
};

@Component({
  validations
})
export default class ArchivoUpdate extends mixins(JhiDataUtils) {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('archivoService') private archivoService: () => ArchivoService;
  public archivo: IArchivo = new Archivo();
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.archivoId) {
        vm.retrieveArchivo(to.params.archivoId);
      }
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.archivo.id) {
      this.archivoService()
        .update(this.archivo)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('kbaseApp.archivo.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.archivoService()
        .create(this.archivo)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('kbaseApp.archivo.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveArchivo(archivoId): void {
    this.archivoService()
      .find(archivoId)
      .then(res => {
        this.archivo = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
