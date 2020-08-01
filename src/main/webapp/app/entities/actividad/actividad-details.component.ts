import { Component, Vue, Inject } from 'vue-property-decorator';

import { IActividad } from '@/shared/model/actividad.model';
import ActividadService from './actividad.service';

@Component
export default class ActividadDetails extends Vue {
  @Inject('actividadService') private actividadService: () => ActividadService;
  public actividad: IActividad = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.actividadId) {
        vm.retrieveActividad(to.params.actividadId);
      }
    });
  }

  public retrieveActividad(actividadId) {
    this.actividadService()
      .find(actividadId)
      .then(res => {
        this.actividad = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
