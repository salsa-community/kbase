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
                <b-form-input name="nombre" :state="!$v.contexto.nombre.$invalid" v-model.trim="$v.contexto.nombre.$model"></b-form-input>
                <div v-if="$v.contexto.nombre.$invalid">
                  <small class="form-text text-danger" v-if="!$v.contexto.nombre.required" v-text="$t('entity.validation.required')">
                    This field is required.
                  </small>
                  <small class="form-text text-danger" v-if="!$v.contexto.nombre.minLength">
                    {{ $t('entity.validation.minlength', { min: $v.contexto.nombre.$params.minLength.min }) }}
                  </small>
                  <small class="form-text text-danger" v-if="!$v.contexto.nombre.maxLength">
                    {{ $t('entity.validation.maxlength', { max: $v.contexto.nombre.$params.maxLength.max }) }}
                  </small>
                </div>
              </div>

              <b-form-group
                description="Con esta clave se puede registrar un bot en un sitio web"
                :label="$t('kbaseApp.contexto.clave')"
                label-for="contexto-clave"
              >
                <b-form-input
                  id="contexto-clave"
                  name="clave"
                  :state="!$v.contexto.clave.$invalid"
                  v-model.trim="$v.contexto.clave.$model"
                ></b-form-input>

                <div v-if="$v.contexto.clave.$invalid">
                  <small class="form-text text-danger" v-if="!$v.contexto.clave.required" v-text="$t('entity.validation.required')">
                    This field is required.
                  </small>
                  <small class="form-text text-danger" v-if="!$v.contexto.clave.minLength">
                    {{ $t('entity.validation.minlength', { min: $v.contexto.clave.$params.minLength.min }) }}
                  </small>
                  <small class="form-text text-danger" v-if="!$v.contexto.clave.maxLength">
                    {{ $t('entity.validation.maxlength', { max: $v.contexto.clave.$params.maxLength.max }) }}
                  </small>
                </div>
              </b-form-group>

              <div class="form-group">
                <label class="form-control-label" v-text="$t('kbaseApp.contexto.desc')" for="contexto-desc">Desc</label>
                <b-form-input name="desc" :state="!$v.contexto.desc.$invalid" v-model.trim="$v.contexto.desc.$model"></b-form-input>

                <div v-if="$v.contexto.desc.$invalid">
                  <small class="form-text text-danger" v-if="!$v.contexto.desc.required" v-text="$t('entity.validation.required')">
                    This field is required.
                  </small>
                  <small class="form-text text-danger" v-if="!$v.contexto.desc.minLength">
                    {{ $t('entity.validation.minlength', { min: $v.contexto.desc.$params.minLength.min }) }}
                  </small>
                  <small class="form-text text-danger" v-if="!$v.contexto.desc.maxLength">
                    {{ $t('entity.validation.maxlength', { max: $v.contexto.desc.$params.maxLength.max }) }}
                  </small>
                </div>
              </div>

              <b-form-group :label="$t('kbaseApp.contexto.descEn')" label-for="contexto-descEn">
                <b-form-input name="descEn" :state="!$v.contexto.descEn.$invalid" v-model.trim="$v.contexto.descEn.$model"></b-form-input>

                <div v-if="$v.contexto.descEn.$invalid">
                  <small class="form-text text-danger" v-if="!$v.contexto.descEn.required" v-text="$t('entity.validation.required')">
                    This field is required.
                  </small>
                  <small class="form-text text-danger" v-if="!$v.contexto.descEn.minLength">
                    {{ $t('entity.validation.minlength', { min: $v.contexto.descEn.$params.minLength.min }) }}
                  </small>
                  <small class="form-text text-danger" v-if="!$v.contexto.descEn.maxLength">
                    {{ $t('entity.validation.maxlength', { max: $v.contexto.descEn.$params.maxLength.max }) }}
                  </small>
                </div>
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
                <b-form-input
                  name="organizacion"
                  :state="!$v.contexto.organizacion.$invalid"
                  v-model.trim="$v.contexto.organizacion.$model"
                ></b-form-input>

                <div v-if="$v.contexto.organizacion.$invalid">
                  <small class="form-text text-danger" v-if="!$v.contexto.organizacion.required" v-text="$t('entity.validation.required')">
                    This field is required.
                  </small>
                  <small class="form-text text-danger" v-if="!$v.contexto.organizacion.minLength">
                    {{ $t('entity.validation.minlength', { min: $v.contexto.organizacion.$params.minLength.min }) }}
                  </small>
                  <small class="form-text text-danger" v-if="!$v.contexto.organizacion.maxLength">
                    {{ $t('entity.validation.maxlength', { max: $v.contexto.organizacion.$params.maxLength.max }) }}
                  </small>
                </div>
              </div>
              <div class="form-group">
                <label class="form-control-label" v-text="$t('kbaseApp.contexto.objetivo')" for="contexto-objetivo">objetivo</label>
                <b-form-input
                  name="objetivo"
                  :state="!$v.contexto.objetivo.$invalid"
                  v-model.trim="$v.contexto.objetivo.$model"
                ></b-form-input>

                <div v-if="$v.contexto.objetivo.$invalid">
                  <small class="form-text text-danger" v-if="!$v.contexto.objetivo.required" v-text="$t('entity.validation.required')">
                    This field is required.
                  </small>
                  <small class="form-text text-danger" v-if="!$v.contexto.objetivo.minLength">
                    {{ $t('entity.validation.minlength', { min: $v.contexto.objetivo.$params.minLength.min }) }}
                  </small>
                  <small class="form-text text-danger" v-if="!$v.contexto.objetivo.maxLength">
                    {{ $t('entity.validation.maxlength', { max: $v.contexto.objetivo.$params.maxLength.max }) }}
                  </small>
                </div>
              </div>
              <div class="form-group">
                <label class="form-control-label" v-text="$t('kbaseApp.contexto.objetivoEn')" for="contexto-objetivoEn">objetivoEn</label>

                <b-form-input
                  name="objetivoEn"
                  :state="!$v.contexto.objetivoEn.$invalid"
                  v-model.trim="$v.contexto.objetivoEn.$model"
                ></b-form-input>

                <div v-if="$v.contexto.objetivoEn.$invalid">
                  <small class="form-text text-danger" v-if="!$v.contexto.objetivoEn.required" v-text="$t('entity.validation.required')">
                    This field is required.
                  </small>
                  <small class="form-text text-danger" v-if="!$v.contexto.objetivoEn.minLength">
                    {{ $t('entity.validation.minlength', { min: $v.contexto.objetivoEn.$params.minLength.min }) }}
                  </small>
                  <small class="form-text text-danger" v-if="!$v.contexto.objetivoEn.maxLength">
                    {{ $t('entity.validation.maxlength', { max: $v.contexto.objetivoEn.$params.maxLength.max }) }}
                  </small>
                </div>
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
                <b-form-input
                  name="loginMessage"
                  :state="!$v.contexto.loginMessage.$invalid"
                  v-model.trim="$v.contexto.loginMessage.$model"
                ></b-form-input>

                <div v-if="$v.contexto.loginMessage.$invalid">
                  <small class="form-text text-danger" v-if="!$v.contexto.loginMessage.required" v-text="$t('entity.validation.required')">
                    This field is required.
                  </small>
                  <small class="form-text text-danger" v-if="!$v.contexto.loginMessage.minLength">
                    {{ $t('entity.validation.minlength', { min: $v.contexto.loginMessage.$params.minLength.min }) }}
                  </small>
                  <small class="form-text text-danger" v-if="!$v.contexto.loginMessage.maxLength">
                    {{ $t('entity.validation.maxlength', { max: $v.contexto.loginMessage.$params.maxLength.max }) }}
                  </small>
                </div>
              </div>
              <div class="form-group">
                <label class="form-control-label" v-text="$t('kbaseApp.contexto.loginMessageEn')" for="contexto-loginMessageEn"
                  >loginMessageEn</label
                >
                <b-form-input
                  name="loginMessageEn"
                  :state="!$v.contexto.loginMessageEn.$invalid"
                  v-model.trim="$v.contexto.loginMessageEn.$model"
                ></b-form-input>

                <div v-if="$v.contexto.loginMessageEn.$invalid">
                  <small
                    class="form-text text-danger"
                    v-if="!$v.contexto.loginMessageEn.required"
                    v-text="$t('entity.validation.required')"
                  >
                    This field is required.
                  </small>
                  <small class="form-text text-danger" v-if="!$v.contexto.loginMessageEn.minLength">
                    {{ $t('entity.validation.minlength', { min: $v.contexto.loginMessageEn.$params.minLength.min }) }}
                  </small>
                  <small class="form-text text-danger" v-if="!$v.contexto.loginMessageEn.maxLength">
                    {{ $t('entity.validation.maxlength', { max: $v.contexto.loginMessageEn.$params.maxLength.max }) }}
                  </small>
                </div>
              </div>
              <div class="form-group">
                <label class="form-control-label" v-text="$t('kbaseApp.contexto.welcomeMessage')" for="contexto-welcomeMessage"
                  >welcomeMessage</label
                >
                <b-form-input
                  name="welcomeMessage"
                  :state="!$v.contexto.welcomeMessage.$invalid"
                  v-model.trim="$v.contexto.welcomeMessage.$model"
                ></b-form-input>

                <div v-if="$v.contexto.welcomeMessage.$invalid">
                  <small
                    class="form-text text-danger"
                    v-if="!$v.contexto.welcomeMessage.required"
                    v-text="$t('entity.validation.required')"
                  >
                    This field is required.
                  </small>
                  <small class="form-text text-danger" v-if="!$v.contexto.welcomeMessage.minLength">
                    {{ $t('entity.validation.minlength', { min: $v.contexto.welcomeMessage.$params.minLength.min }) }}
                  </small>
                  <small class="form-text text-danger" v-if="!$v.contexto.welcomeMessage.maxLength">
                    {{ $t('entity.validation.maxlength', { max: $v.contexto.welcomeMessage.$params.maxLength.max }) }}
                  </small>
                </div>
              </div>
              <div class="form-group">
                <label class="form-control-label" v-text="$t('kbaseApp.contexto.welcomeMessageEn')" for="contexto-welcomeMessageEn"
                  >welcomeMessageEn</label
                >
                <b-form-input
                  name="welcomeMessageEn"
                  :state="!$v.contexto.welcomeMessageEn.$invalid"
                  v-model.trim="$v.contexto.welcomeMessageEn.$model"
                ></b-form-input>

                <div v-if="$v.contexto.welcomeMessageEn.$invalid">
                  <small
                    class="form-text text-danger"
                    v-if="!$v.contexto.welcomeMessageEn.required"
                    v-text="$t('entity.validation.required')"
                  >
                    This field is required.
                  </small>
                  <small class="form-text text-danger" v-if="!$v.contexto.welcomeMessageEn.minLength">
                    {{ $t('entity.validation.minlength', { min: $v.contexto.welcomeMessageEn.$params.minLength.min }) }}
                  </small>
                  <small class="form-text text-danger" v-if="!$v.contexto.welcomeMessageEn.maxLength">
                    {{ $t('entity.validation.maxlength', { max: $v.contexto.welcomeMessageEn.$params.maxLength.max }) }}
                  </small>
                </div>
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
