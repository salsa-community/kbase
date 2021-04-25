import Vue from 'vue';
import Component from 'vue-class-component';
import Ribbon from '@/core/ribbon/ribbon.vue';
import JhiFooter from '@/core/jhi-footer/jhi-footer.vue';
import SideBar from '@/core/side-bar/side-bar.vue';
import JhiNavbar from '@/core/jhi-navbar/jhi-navbar.vue';
import LoginForm from '@/account/login-form/login-form.vue';
import { STEPS, OPTIONS } from '@/tour';

@Component({
  components: {
    ribbon: Ribbon,
    'side-bar': SideBar,
    'jhi-navbar': JhiNavbar,
    'login-form': LoginForm,

    'jhi-footer': JhiFooter
  }
})
export default class App extends Vue {
  public tourSteps = STEPS;
  public tourOptions = OPTIONS;
}
