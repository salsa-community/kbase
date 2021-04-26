<template>
  <div id="app" class="wrapper d-flex align-items-stretch">
    <v-tour name="kbase-tour" :steps="tourSteps" :options="tourOptions">
      <template slot-scope="tour">
        <transition name="fade">
          <v-step
            v-if="tour.steps[tour.currentStep]"
            :key="tour.currentStep"
            :step="tour.steps[tour.currentStep]"
            :previous-step="tour.previousStep"
            :next-step="tour.nextStep"
            :stop="tour.stop"
            :skip="tour.skip"
            :highlight="tour.highlight"
            :is-first="tour.isFirst"
            :is-last="tour.isLast"
            :labels="tour.labels"
          >
            <template v-if="tour.isFirst">
              <div slot="actions">
                <button @click="tour.skip" class="v-step__button v-step__button-skip">Quitar tutorial</button>
                <button @click="tour.nextStep" class="v-step__button v-step__button-next">Siguiente</button>
              </div>
            </template>
            <template v-else-if="tour.isLast">
              <div slot="actions">
                <button @click="tour.stop" class="v-step__button v-step__button-stop">Terminar</button>
              </div>
            </template>
            <template v-else>
              <div slot="actions">
                <button @click="tour.skip" class="v-step__button v-step__button-skip">Quitar tutorial</button>
                <button @click="tour.nextStep" class="v-step__button v-step__button-next">Siguiente</button>
              </div>
            </template>
          </v-step>
        </transition>
      </template>
    </v-tour>
    <ribbon></ribbon>
    <side-bar></side-bar>
    <div class="container-fluid">
      <div class="jh-card">
        <transition name="fade" mode="out-in">
          <router-view></router-view>
        </transition>
      </div>
      <b-modal header-text-variant="light" header-bg-variant="primary" id="login-page" hide-footer lazy>
        <span slot="modal-title" id="login-title" v-text="$t('login.title')">Sign in</span>
        <login-form></login-form>
      </b-modal>
    </div>
  </div>
</template>

<script lang="ts" src="./app.component.ts">
</script>

<style scoped>
.wrapper {
  width: 100%;
}
.d-flex {
  display: -webkit-box !important;
  display: -ms-flexbox !important;
  display: flex !important;
}
.align-items-stretch {
  -webkit-box-align: stretch !important;
  -ms-flex-align: stretch !important;
  align-items: stretch !important;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s;
}
.fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
  opacity: 0;
}
</style>
