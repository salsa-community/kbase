import { Component, Prop, PropSync, Vue } from 'vue-property-decorator';
import { IMensaje, Mensaje } from '@/shared/model/mensaje.model';

@Component
export default class Mensajes extends Vue {
  @Prop({ required: true, type: Array })
  public value!: Array<IMensaje>;

  public dragOptions = {
    animation: 200,
    group: 'description',
    disabled: false,
    ghostClass: 'ghost'
  };

  get mensajes(): Array<IMensaje> {
    return this.value;
  }
  set mensajes(newValue: Array<IMensaje>) {
    this.$emit('input', newValue);
  }

  public manageChange(moved): void {
    this.orderSteps();
  }

  public orderSteps(): void {
    let count = 1;
    this.value.forEach(paso => {
      paso.orden = count++;
    });
  }
  public add() {
    let mensaje = new Mensaje();
    mensaje.orden = this.value.length - 1;
    this.value.push(mensaje);
    this.orderSteps();
  }

  public remove(step: IMensaje): void {
    this.value.splice(this.value.indexOf(step), 1);
    this.orderSteps();
  }
}
