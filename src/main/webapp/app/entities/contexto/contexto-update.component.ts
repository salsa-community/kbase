import { Component, Vue, Inject } from 'vue-property-decorator';

import { required, minLength, maxLength } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';
import { IContexto, Contexto } from '@/shared/model/contexto.model';
import { IMensaje, Mensaje } from '@/shared/model/mensaje.model';

import ContextoService from './contexto.service';

import { CONTEXTO_STEPS, OPTIONS } from '@/tour';

const validations: any = {
  contexto: {
    nombre: {
      required,
      minLength: minLength(4),
      maxLength: maxLength(50)
    },
    clave: {
      required,
      minLength: minLength(4),
      maxLength: maxLength(20)
    },
    desc: {
      required,
      minLength: minLength(4),
      maxLength: maxLength(150)
    },
    descEn: {
      required,
      minLength: minLength(4),
      maxLength: maxLength(150)
    },
    organizacion: {
      required,
      minLength: minLength(4),
      maxLength: maxLength(50)
    }
  },
  newStep: {
    desc: {
      required,
      minLength: minLength(4),
      maxLength: maxLength(500)
    },
    descEn: {
      required,
      minLength: minLength(4),
      maxLength: maxLength(500)
    }
  }
};

@Component({
  validations
})
export default class ContextoUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('contextoService') private contextoService: () => ContextoService;
  public contexto: IContexto = new Contexto();
  public newStep = new Mensaje();
  public stepId = null;
  public isSaving = false;
  public color = 0;
  public dragOptions = {
    animation: 200,
    group: 'description',
    disabled: false,
    ghostClass: 'ghost'
  };

  public showReplies = false;
  public headcodeKey = 0;
  public bodycodeKey = 1;
  public tourSteps = CONTEXTO_STEPS;
  public tourOptions = OPTIONS;

  public initTour() {
    this.$tours['contexto-tour'].start();
  }

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

  public saveStep(): void {
    const step = new Mensaje();
    step.orden = this.newStep.orden;
    step.desc = this.newStep.desc;
    step.descEn = this.newStep.descEn;
    step.replies = this.newStep.replies;
    this.contexto.mensajes.splice(this.contexto.mensajes.indexOf(this.stepId), 1, step);
    this.closeEditStepDialog();
  }

  public addStep(): void {
    const step = new Mensaje();
    step.orden = Infinity;
    step.desc = this.newStep.desc;
    step.descEn = this.newStep.descEn;
    step.replies = this.newStep.replies;
    this.contexto.mensajes.push(step);
    this.orderSteps();
    this.closeEditStepDialog();
    this.closeAddStepDialog();
  }

  public removeStep(step: IMensaje): void {
    console.log(step);
    this.stepId = step;
    this.contexto.mensajes.splice(this.contexto.mensajes.indexOf(this.stepId), 1);
    this.orderSteps();
    this.closeDialog();
  }

  public orderSteps(): void {
    let count = 1;
    this.contexto.mensajes.forEach(paso => {
      paso.orden = count++;
    });
  }

  public closeDialog(): void {
    this.showReplies = false;
    (<any>this.$refs.removeEntity).hide();
  }

  public closeEditStepDialog(): void {
    this.showReplies = false;
    (<any>this.$refs.editStep).hide();
  }

  public closeAddStepDialog(): void {
    this.showReplies = false;
    (<any>this.$refs.addStep).hide();
  }

  public prepareToSave(step: any): void {
    this.stepId = step;
    this.newStep.orden = step.orden;
    this.newStep.desc = step.desc;
    this.newStep.descEn = step.descEn;
    this.newStep.replies = step.replies;
    if (step.orden == this.contexto.mensajes.length) {
      this.showReplies = true;
    }
    (<any>this.$refs.editStep).show();
  }

  public prepareToAddStep() {
    this.newStep.orden = -1;
    this.newStep.desc = '';
    this.newStep.descEn = '';
    this.newStep.replies = [];
    if (this.contexto.mensajes && this.contexto.mensajes.length > 0) {
      let newReplies = [];
      let tempMensaje = this.contexto.mensajes[this.contexto.mensajes.length - 1];
      for (let index = 0; index < tempMensaje.replies.length; index++) {
        newReplies.push(tempMensaje.replies[index]);
      }
      this.newStep.replies = newReplies;
    }
    (<any>this.$refs.addStep).show();
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
