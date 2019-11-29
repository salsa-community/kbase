import { Component, Vue, Inject } from 'vue-property-decorator';

import { ILink } from '@/shared/model/link.model';
import LinkService from './link.service';

@Component
export default class LinkDetails extends Vue {
  @Inject('linkService') private linkService: () => LinkService;
  public link: ILink = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.linkId) {
        vm.retrieveLink(to.params.linkId);
      }
    });
  }

  public retrieveLink(linkId) {
    this.linkService()
      .find(linkId)
      .then(res => {
        this.link = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
