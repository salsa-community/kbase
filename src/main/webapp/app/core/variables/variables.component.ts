import { Component, Prop, PropSync, Vue } from 'vue-property-decorator';

@Component
export default class Variables extends Vue {
  @Prop({ required: false, type: Array })
  public elements!: Array<string>;
}
