<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <v-tour name="contexto-tour" :steps="tourSteps" :options="tourOptions">
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
            </v-step>
          </transition>
        </template>
      </v-tour>
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <b-card header-text-variant="white" header-bg-variant="primary">
          <template #header>
            <h2 id="kbaseApp.contexto.home.createOrEditLabel">
              {{ $t('kbaseApp.contexto.home.createOrEditLabel') }}
            </h2>
          </template>
          <b-alert show variant="light">
            Si requiere ayuda sobre esta sección, puede dar click en el siguiente icono
            <a v-on:click="initTour()" id="login"> <b-icon icon="info-circle-fill" variant="secondary"></b-icon></a>
          </b-alert>
          <b-tabs content-class="mt-4" fill pills small>
            <b-tab title="Datos generales">
              <template #title>
                <div id="datos-generales-id" ref="dgref">Datos generales</div>
              </template>
              <div class="form-group" v-if="contexto.id">
                <label for="id" v-text="$t('global.field.id')">ID</label>
                <input type="text" class="form-control" id="id" name="id" v-model="contexto.id" readonly />
              </div>
              <div class="form-group">
                <label class="form-control-label" v-text="$t('kbaseApp.contexto.nombre')" for="contexto-nombre">Nombre</label>
                <input
                  type="text"
                  class="form-control"
                  name="nombre"
                  id="contexto-nombre"
                  :class="{ valid: !$v.contexto.nombre.$invalid, invalid: $v.contexto.nombre.$invalid }"
                  v-model="$v.contexto.nombre.$model"
                />
              </div>

              <b-form-group
                description="Con esta clave se puede registrar un bot en un sitio web"
                :label="$t('kbaseApp.contexto.clave')"
                label-for="contexto-clave"
              >
                <input
                  type="text"
                  class="form-control"
                  name="clave"
                  id="contexto-clave"
                  :class="{ valid: !$v.contexto.clave.$invalid, invalid: $v.contexto.clave.$invalid }"
                  v-model="$v.contexto.clave.$model"
                />
              </b-form-group>

              <div class="form-group">
                <label class="form-control-label" v-text="$t('kbaseApp.contexto.desc')" for="contexto-desc">Desc</label>
                <input
                  type="text"
                  class="form-control"
                  name="desc"
                  id="contexto-desc"
                  :class="{ valid: !$v.contexto.desc.$invalid, invalid: $v.contexto.desc.$invalid }"
                  v-model="$v.contexto.desc.$model"
                />
              </div>

              <b-form-group :label="$t('kbaseApp.contexto.descEn')" label-for="contexto-descEn">
                <input
                  type="text"
                  class="form-control"
                  name="descEn"
                  id="contexto-descEn"
                  :class="{ valid: !$v.contexto.descEn.$invalid, invalid: $v.contexto.descEn.$invalid }"
                  v-model="$v.contexto.descEn.$model"
                />
              </b-form-group>
            </b-tab>
            <b-tab>
              <template #title>
                <div id="datos-organizacion-id">Datos de la organización</div>
              </template>
              <div class="form-group">
                <label class="form-control-label" v-text="$t('kbaseApp.contexto.organizacion')" for="contexto-organizacion"
                  >organizacion</label
                >
                <input
                  type="text"
                  class="form-control"
                  name="desc"
                  id="contexto-organizacion"
                  :class="{ valid: !$v.contexto.organizacion.$invalid, invalid: $v.contexto.organizacion.$invalid }"
                  v-model="$v.contexto.organizacion.$model"
                />
              </div>
              <div class="form-group">
                <label class="form-control-label" v-text="$t('kbaseApp.contexto.objetivo')" for="contexto-objetivo">objetivo</label>
                <input
                  type="text"
                  class="form-control"
                  name="objetivo"
                  id="contexto-objetivo"
                  :class="{ valid: !$v.contexto.objetivo.$invalid, invalid: $v.contexto.objetivo.$invalid }"
                  v-model="$v.contexto.objetivo.$model"
                />
              </div>
              <div class="form-group">
                <label class="form-control-label" v-text="$t('kbaseApp.contexto.objetivoEn')" for="contexto-objetivoEn">objetivoEn</label>
                <input
                  type="text"
                  class="form-control"
                  name="objetivoEn"
                  id="contexto-objetivoEn"
                  :class="{ valid: !$v.contexto.objetivoEn.$invalid, invalid: $v.contexto.objetivoEn.$invalid }"
                  v-model="$v.contexto.objetivoEn.$model"
                />
              </div>
            </b-tab>
            <b-tab title="Mensajes del chatbot">
              <template #title>
                <div id="datos-chatbot-id">Mensajes del chatbot</div>
              </template>
              <div class="form-group">
                <label class="form-control-label" v-text="$t('kbaseApp.contexto.loginMessage')" for="contexto-loginMessage"
                  >loginMessage</label
                >
                <input
                  type="text"
                  class="form-control"
                  name="loginMessage"
                  id="contexto-loginMessage"
                  :class="{ valid: !$v.contexto.loginMessage.$invalid, invalid: $v.contexto.loginMessage.$invalid }"
                  v-model="$v.contexto.loginMessage.$model"
                />
              </div>
              <div class="form-group">
                <label class="form-control-label" v-text="$t('kbaseApp.contexto.loginMessageEn')" for="contexto-loginMessageEn"
                  >loginMessageEn</label
                >
                <input
                  type="text"
                  class="form-control"
                  name="loginMessageEn"
                  id="contexto-loginMessageEn"
                  :class="{ valid: !$v.contexto.loginMessageEn.$invalid, invalid: $v.contexto.loginMessageEn.$invalid }"
                  v-model="$v.contexto.loginMessageEn.$model"
                />
              </div>
              <div class="form-group">
                <label class="form-control-label" v-text="$t('kbaseApp.contexto.welcomeMessage')" for="contexto-welcomeMessage"
                  >welcomeMessage</label
                >
                <input
                  type="text"
                  class="form-control"
                  name="welcomeMessage"
                  id="contexto-welcomeMessage"
                  :class="{ valid: !$v.contexto.welcomeMessage.$invalid, invalid: $v.contexto.welcomeMessage.$invalid }"
                  v-model="$v.contexto.welcomeMessage.$model"
                />
              </div>
              <div class="form-group">
                <label class="form-control-label" v-text="$t('kbaseApp.contexto.welcomeMessageEn')" for="contexto-welcomeMessageEn"
                  >welcomeMessageEn</label
                >
                <input
                  type="text"
                  class="form-control"
                  name="welcomeMessageEn"
                  id="contexto-welcomeMessageEn"
                  :class="{ valid: !$v.contexto.welcomeMessageEn.$invalid, invalid: $v.contexto.welcomeMessageEn.$invalid }"
                  v-model="$v.contexto.welcomeMessageEn.$model"
                />
              </div>
            </b-tab>
            <b-tab title="Código fuente">
              <template #title>
                <div id="datos-codigo-id">Código fuente</div>
              </template>
              <b-card
                border-variant="light"
                title="Instalación del chatbot"
                :sub-title="`Configuración del contexto: << ` + contexto.clave + ` >>`"
              >
                <b-card-text>
                  Utilice el siguiente código como referencia para instalar su chatbot <b>{{ contexto.clave }}</b>
                </b-card-text>
                <code-highlight id="headcode-id" language="html" :key="headcodeKey">
                  {{ headcode }}
                </code-highlight>

                En la sección de Body, agregue la siguiente configuración

                <code-highlight id="bodycode-id" language="html" :key="bodycodeKey">
                  {{ bodycode }}
                </code-highlight>
              </b-card>
            </b-tab>
          </b-tabs>
          <div>
            <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
              <span v-text="$t('entity.action.cancel')">Cancel</span>
            </button>
            <button type="submit" id="save-entity" :disabled="$v.contexto.$invalid || isSaving" class="btn btn-primary">
              <span v-text="$t('entity.action.save')">Save</span>
            </button>
          </div>
        </b-card>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./contexto-update.component.ts">
</script>
