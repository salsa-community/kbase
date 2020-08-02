import { mixins } from 'vue-class-component';

import { Component, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IActividad } from '@/shared/model/actividad.model';
import AlertService from '@/shared/alert/alert.service';
import EventoService from '../evento/evento.service';
import { IEvento } from '@/shared/model/evento.model';
import { IContexto } from '@/shared/model/contexto.model';
import ContextoService from '../contexto/contexto.service';

import ActividadService from './actividad.service';

@Component
export default class Actividad extends mixins(Vue2Filters.mixin) {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('actividadService') private actividadService: () => ActividadService;
  @Inject('eventoService') private eventoService: () => EventoService;
  @Inject('contextoService') private contextoService: () => ContextoService;

  private removeId: string = null;
  public itemsPerPage = 20;
  public queryCount: number = null;
  public page = 1;
  public previousPage = 1;
  public propOrder = 'fecha';
  public reverse = false;
  public totalItems = 0;
  public actividads: IActividad[] = [];
  public eventos: IEvento[] = [];
  public contextos: IContexto[] = [];
  public filtro: any = { contextos: [], eventos: [], rangoFechas: [] };

  public isFetching = false;
  public dismissCountDown: number = this.$store.getters.dismissCountDown;
  public dismissSecs: number = this.$store.getters.dismissSecs;
  public alertType: string = this.$store.getters.alertType;
  public alertMessage: any = this.$store.getters.alertMessage;

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
    this.retrieveAllActividads();
    this.initFiltros();
  }

  public search() {
    this.retrieveAllActividads();
  }

  public initFiltros(): void {
    this.eventoService()
      .retrieve()
      .then(res => {
        this.eventos = res.data;
      });
    this.contextoService()
      .retrieve()
      .then(res => {
        this.contextos = res.data;
      });
  }

  public clearFiltro() {
    this.filtro.contextos = [];
    this.filtro.eventos = [];
    this.filtro.rangoFechas = [];
  }

  public clear(): void {
    this.page = 1;
    this.retrieveAllActividads();
  }

  public retrieveAllActividads(): void {
    this.isFetching = true;

    const paginationQuery = {
      page: this.page - 1,
      size: this.itemsPerPage,
      sort: this.sort()
    };
    this.actividadService()
      .retrieve(paginationQuery)
      .then(
        res => {
          this.actividads = res.data;
          this.totalItems = Number(res.headers['x-total-count']);
          this.queryCount = this.totalItems;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: IActividad): void {
    this.removeId = instance.id;
  }

  public removeActividad(): void {
    this.actividadService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('kbaseApp.actividad.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();

        this.removeId = null;
        this.retrieveAllActividads();
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
    this.retrieveAllActividads();
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
