import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';
import { IContexto, Contexto } from '@/shared/model/contexto.model';
import ContextoService from './contexto.service';

import { highlight, languages } from 'prismjs/components/prism-core';

const validations: any = {
  contexto: {
    nombre: {},
    clave: {},
    desc: {},
    descEn: {},
    objetivo: {},
    objetivoEn: {},
    organizacion: {},
    loginMessage: {},
    loginMessageEn: {},
    welcomeMessage: {},
    welcomeMessageEn: {}
  }
};

@Component({
  validations
})
export default class ContextoUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('contextoService') private contextoService: () => ContextoService;
  public contexto: IContexto = new Contexto();
  public isSaving = false;
  public color = 0;

  public headcodeKey = 0;
  public bodycodeKey = 1;

  get headcode(): string {
    this.headcodeKey = this.headcodeKey + 1;
    return `
  <html>
    <head>
        <link rel="stylesheet" href="/${this.contexto.clave}/css/embed.css?color=34495E" />
        <script src="/embed.js"></script>
    </head>

  </html>
  `;
  }

  get bodycode(): string {
    this.bodycodeKey = this.bodycodeKey + 1;
    return `
  <html>
    <body>
        <div id="embedded_messenger">
          <header id="message_header">
              <div class="circle"></div>
              <img id="avatar-bot" class="avatar_icon" src="" alt="Avatar" >
              <div class="header_text">Botkit.title()</div>
          </header>
          <iframe id="botkit_client" title="bot" ></iframe>
        </div>
        <script id="bot-client-script" bothome=""
        src="/${this.contexto.clave}/scripts/bot.js?color=34495E&culture=es-MX"></script>
    </body>
  </html>
  `;
  }

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.contextoId) {
        vm.retrieveContexto(to.params.contextoId);
      }
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.contexto.id) {
      this.contextoService()
        .update(this.contexto)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('kbaseApp.contexto.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.contextoService()
        .create(this.contexto)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('kbaseApp.contexto.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveContexto(contextoId): void {
    this.contextoService()
      .find(contextoId)
      .then(res => {
        this.contexto = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
