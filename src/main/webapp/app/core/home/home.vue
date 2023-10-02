<template>
  <b-overlay :show="!imgLoaded">
    <div class="row">
      <div class="col-md">
        <div v-if="authenticated" class="text-center">
          <img src="content/images/kbase-security.png" alt="ciencia-loader" @load="onImgLoad" width="25%" />
          <div class="col-md text-center mt-4">
            <b-button size="lg" :to="'/admin/user-management'" variant="outline-primary">Gestión de usuarios</b-button>
          </div>
        </div>
        <div v-if="!authenticated" class="text-center">
          <img src="content/images/kbase-logo.png" alt="ciencia-loader" @load="onImgLoad" style="max-width: 800px; width: 100%" />
        </div>
        <div v-if="!authenticated && imgLoaded" class="col-md text-center mt-4">
          <b-button size="lg" v-on:click="openLogin()" variant="outline-primary">Iniciar sesión</b-button>
        </div>
      </div>
    </div>
  </b-overlay>
</template>

<script lang="ts" src="./home.component.ts"></script>
