import { Component, Vue, Inject } from 'vue-property-decorator';

import { IContexto } from '@/shared/model/contexto.model';
import ContextoService from './contexto.service';

@Component
export default class ContextoDetails extends Vue {
  @Inject('contextoService') private contextoService: () => ContextoService;
  public contexto: IContexto = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.contextoId) {
        vm.retrieveContexto(to.params.contextoId);
      }
    });
  }

  public retrieveContexto(contextoId) {
    this.contextoService()
      .find(contextoId)
      .then(res => {
        this.contexto = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
