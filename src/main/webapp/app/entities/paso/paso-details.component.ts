import { Component, Vue, Inject } from 'vue-property-decorator';

import { IPaso } from '@/shared/model/paso.model';
import PasoService from './paso.service';

@Component
export default class PasoDetails extends Vue {
  @Inject('pasoService') private pasoService: () => PasoService;
  public paso: IPaso = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.pasoId) {
        vm.retrievePaso(to.params.pasoId);
      }
    });
  }

  public retrievePaso(pasoId) {
    this.pasoService()
      .find(pasoId)
      .then(res => {
        this.paso = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
