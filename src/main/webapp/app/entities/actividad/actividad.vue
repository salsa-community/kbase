<template>
  <div>
    <div class="container">
      <div class="row">
        <div class="col-sm-8">
          <div class="container">
            <b-card-group class="row">
              <b-card
                text-variant="white"
                body-bg-variant="purple"
                border-variant="white"
                footer="Código más consultado"
                footer-text-variant="purple"
                footer-bg-variant="white"
                class="text-center col-sm-5"
                :title="reporte.topCodigoError"
              >{{reporte.countCodigoError}}</b-card>
              <div class="col-sm-1"></div>
              <b-card
                text-variant="white"
                body-bg-variant="purple"
                border-variant="white"
                footer="Número de contactos"
                footer-text-variant="purple"
                footer-bg-variant="white"
                class="text-center col-sm-5"
                title="Contactos"
              >{{reporte.numContactos}}
              </b-card>
            </b-card-group>
          </div>
        </div>
        <div class="col-sm-4"></div>
      </div>
    </div>
    <br />
    <b-tabs no-body content-class="mt-3">
      <b-tab title="Filtros" active>
        <div class="row">
          <div class="col-sm-2">
            <div class="form-group">
              <b-form-group label="Contextos:">
                <b-form-checkbox-group
                  v-model="filtro.contextos"
                  :options="contextos"
                  name="contextos"
                  value-field="clave"
                  text-field="nombre"
                  stacked
                ></b-form-checkbox-group>
              </b-form-group>
            </div>
          </div>
          <div class="col-sm-3">
            <b-form-group label="Eventos:">
              <b-form-checkbox-group
                v-model="filtro.eventos"
                :options="eventos"
                name="eventos"
                value-field="id"
                text-field="nombre"
                stacked
              ></b-form-checkbox-group>
            </b-form-group>
          </div>

          <div class="col-sm-3">
            <div class="form-group">
              <div>
                <p class="text-left">Rango de fechas:</p>
              </div>
              <el-date-picker
                v-model="filtro.rangoFechas"
                class="form-control"
                type="datetimerange"
                range-separator="a"
                start-placeholder="Fecha inicio"
                end-placeholder="Fecha fin"
                align="left"
              ></el-date-picker>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-sm-8">
            <el-button type="default" icon="el-icon-delete" v-on:click="clearFiltro()">Limpiar</el-button>
            <el-button
              icon="el-icon-search"
              type="primary"
              :loading="isFetching"
              v-on:click="search()"
            >Buscar</el-button>
          </div>
          <div class="col-sm-4">
            <el-button
              icon="el-icon-download"
              type="primary"
              :loading="isDowloadingReport"
              v-on:click="downloadReport()"
            >Exportar reporte</el-button>
          </div>
        </div>
      </b-tab>
    </b-tabs>

    <b-alert
      :show="dismissCountDown"
      dismissible
      :variant="alertType"
      @dismissed="dismissCountDown=0"
      @dismiss-count-down="countDownChanged"
    >{{alertMessage}}</b-alert>
    <br />
    <el-table v-loading="isFetching" :data="actividads" style="width: 100%">
      <el-table-column prop="contexto" :label="$t('kbaseApp.actividad.contexto')">
        <template slot-scope="scope">
          <el-tag size="medium">{{ scope.row.contexto }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column sortable prop="evento" :label="$t('kbaseApp.actividad.evento')"></el-table-column>
      <el-table-column sortable prop="valor" :label="$t('kbaseApp.actividad.valor')"></el-table-column>
      <el-table-column sortable prop="fecha" :label="$t('kbaseApp.actividad.fecha')">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">
            <em>{{ scope.row.fecha | timeElapsed}}</em>
          </span>
        </template>
      </el-table-column>
    </el-table>
    <div v-show="actividads && actividads.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination
          size="md"
          :total-rows="totalItems"
          v-model="page"
          :per-page="itemsPerPage"
          :change="loadPage(page)"
        ></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./actividad.component.ts">
</script>
