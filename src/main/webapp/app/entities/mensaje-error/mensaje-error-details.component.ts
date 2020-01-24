import { Component, Vue, Inject } from 'vue-property-decorator';

import { IMensajeError } from '@/shared/model/mensaje-error.model';
import MensajeErrorService from './mensaje-error.service';

@Component
export default class MensajeErrorDetails extends Vue {
  @Inject('mensajeErrorService') private mensajeErrorService: () => MensajeErrorService;
  public mensajeError: IMensajeError = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.mensajeErrorId) {
        vm.retrieveMensajeError(to.params.mensajeErrorId);
      }
    });
  }

  public retrieveMensajeError(mensajeErrorId) {
    this.mensajeErrorService()
      .find(mensajeErrorId)
      .then(res => {
        this.mensajeError = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
