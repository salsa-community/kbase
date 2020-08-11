import { Component, Inject } from 'vue-property-decorator';

import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import { IArchivo } from '@/shared/model/archivo.model';
import ArchivoService from './archivo.service';

@Component
export default class ArchivoDetails extends mixins(JhiDataUtils) {
  @Inject('archivoService') private archivoService: () => ArchivoService;
  public archivo: IArchivo = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.archivoId) {
        vm.retrieveArchivo(to.params.archivoId);
      }
    });
  }

  public retrieveArchivo(archivoId) {
    this.archivoService()
      .find(archivoId)
      .then(res => {
        this.archivo = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
