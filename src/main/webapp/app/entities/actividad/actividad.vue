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
                title="CFDI33110"
              ></b-card>
              <div class="col-sm-1"></div>
              <b-card
                text-variant="white"
                body-bg-variant="purple"
                border-variant="white"
                footer="Número de contactos"
                footer-text-variant="purple"
                footer-bg-variant="white"
                class="text-center col-sm-5"
                title="587"
              ></b-card>
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
              <select class="form-control" id="actividad-evento" name="evento">
                <option v-bind:value="null"></option>
                <option>Contexto 1</option>
                <option>Contexto 2</option>
              </select>
            </div>
          </div>
          <div class="col-sm-2">
            <div class="form-group">
              <select name id class="form-control">
                <option>Evento 1</option>
                <option>Evento 2</option>
              </select>
            </div>
          </div>

          <div class="col-sm-3">
            <div class="form-group">
              <el-date-picker
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
          <div class="col-sm-6">
            <el-button type="primary" icon="el-icon-delete">Limpiar</el-button>
            <el-button icon="el-icon-search" type="primary" :loading="false">Buscar</el-button>
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
    <div class="alert alert-warning" v-if="!isFetching && actividads && actividads.length === 0">
      <span v-text="$t('kbaseApp.actividad.home.notFound')">No actividads found</span>
    </div>
    <el-table v-loading="false" :data="actividads" style="width: 100%">
      <el-table-column prop="contexto" :label="$t('kbaseApp.actividad.contexto')">
        <template slot-scope="scope">
          <el-tag size="medium">{{ scope.row.contexto }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column sortable prop="eventoNombre" :label="$t('kbaseApp.actividad.evento')"></el-table-column>
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
