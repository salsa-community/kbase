import { mixins } from 'vue-class-component';

import { Component, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IMensajeError } from '@/shared/model/mensaje-error.model';
import AlertService from '@/shared/alert/alert.service';

import MensajeErrorService from './mensaje-error.service';

@Component
export default class MensajeError extends mixins(Vue2Filters.mixin) {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('mensajeErrorService') private mensajeErrorService: () => MensajeErrorService;
  private removeId: string = null;
  public itemsPerPage = 20;
  public queryCount: number = null;
  public page = 1;
  public previousPage = 1;
  public propOrder = 'id';
  public reverse = false;
  public totalItems = 0;
  public mensajeErrors: IMensajeError[] = [];
  public currentClave: string = null;

  public isFetching = false;
  public dismissCountDown: number = this.$store.getters.dismissCountDown;
  public dismissSecs: number = this.$store.getters.dismissSecs;
  public alertType: string = this.$store.getters.alertType;
  public alertMessage: any = this.$store.getters.alertMessage;

  public tagTypeList: string[] = ['', 'success', 'info', 'warning', 'danger'];

  public resolveTagType(index: number) {
    const i = index % this.tagTypeList.length;
    return this.tagTypeList[i];
  }
  public getAlertFromStore() {
    this.dismissCountDown = this.$store.getters.dismissCountDown;
    this.dismissSecs = this.$store.getters.dismissSecs;
    this.alertType = this.$store.getters.alertType;
    this.alertMessage = this.$store.getters.alertMessage;
  }

  public countDownChanged(dismissCountDown: number) {
    this.alertService().countDownChanged(dismissCountDown);
    this.getAlertFromStore();
  }

  public mounted(): void {
    this.retrieveAllMensajeErrors();
  }

  public clear(): void {
    this.page = 1;
    this.retrieveAllMensajeErrors();
  }

  public retrieveAllMensajeErrors(): void {
    this.isFetching = true;

    const paginationQuery = {
      page: this.page - 1,
      size: this.itemsPerPage,
      sort: this.sort()
    };
    this.mensajeErrorService()
      .retrieve(paginationQuery)
      .then(
        res => {
          this.mensajeErrors = res.data;
          this.totalItems = Number(res.headers['x-total-count']);
          this.queryCount = this.totalItems;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: IMensajeError): void {
    console.log(instance);
    this.removeId = instance.id;
    this.currentClave = instance.clave;
    (<any>this.$refs['removeEntity']).show();
  }

  public removeMensajeError(): void {
    this.mensajeErrorService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('kbaseApp.mensajeError.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();

        this.removeId = null;
        this.retrieveAllMensajeErrors();
        this.closeDialog();
      });
  }

  public sort(): Array<any> {
    const result = [this.propOrder + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.propOrder !== 'id') {
      result.push('id');
    }
    return result;
  }

  public loadPage(page: number): void {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  public transition(): void {
    this.retrieveAllMensajeErrors();
  }

  public changeOrder(propOrder): void {
    this.propOrder = propOrder;
    this.reverse = !this.reverse;
    this.transition();
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
