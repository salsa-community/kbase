import { Component, Vue, Inject } from 'vue-property-decorator';

import { IUnknowIntents } from '@/shared/model/unknow-intents.model';
import UnknowIntentsService from './unknow-intents.service';

@Component
export default class UnknowIntentsDetails extends Vue {
  @Inject('unknowIntentsService') private unknowIntentsService: () => UnknowIntentsService;
  public unknowIntents: IUnknowIntents = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.unknowIntentsId) {
        vm.retrieveUnknowIntents(to.params.unknowIntentsId);
      }
    });
  }

  public retrieveUnknowIntents(unknowIntentsId) {
    this.unknowIntentsService()
      .find(unknowIntentsId)
      .then(res => {
        this.unknowIntents = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
