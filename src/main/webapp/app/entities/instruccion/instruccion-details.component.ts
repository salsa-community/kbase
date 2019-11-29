import { Component, Vue, Inject } from 'vue-property-decorator';

import { IInstruccion } from '@/shared/model/instruccion.model';
import InstruccionService from './instruccion.service';

@Component
export default class InstruccionDetails extends Vue {
  @Inject('instruccionService') private instruccionService: () => InstruccionService;
  public instruccion: IInstruccion = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.instruccionId) {
        vm.retrieveInstruccion(to.params.instruccionId);
      }
    });
  }

  public retrieveInstruccion(instruccionId) {
    this.instruccionService()
      .find(instruccionId)
      .then(res => {
        this.instruccion = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
