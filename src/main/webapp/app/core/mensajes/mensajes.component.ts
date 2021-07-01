import { Component, Prop, PropSync, Vue } from 'vue-property-decorator';
import { IMensaje, Mensaje } from '@/shared/model/mensaje.model';

@Component
export default class Mensajes extends Vue {
  @PropSync('value', { required: true, type: Array })
  public mensajes!: Array<IMensaje>;

  public dragOptions = {
    animation: 200,
    group: 'description',
    disabled: false,
    ghostClass: 'ghost'
  };

  public orderSteps(): void {
    let count = 1;
    this.mensajes.forEach(paso => {
      paso.orden = count++;
    });
  }
  public add() {
    let mensaje = new Mensaje();
    mensaje.orden = this.mensajes.length - 1;
    this.mensajes.push(mensaje);
    this.orderSteps();
  }

  public remove(step: IMensaje): void {
    this.mensajes.splice(this.mensajes.indexOf(step), 1);
    this.orderSteps();
  }
}
