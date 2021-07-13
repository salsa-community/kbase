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
            </b-tab>
            <b-tab title="Mensajes de inicio chatbot">
              <template #title>
                <div id="datos-chatbot-id">Mensajes del chatbot</div>
              </template>
              <div class="form-group">
                <label class="form-control-label" v-text="$t('kbaseApp.contexto.mensajes.title')" for="mensaje-error-pasos">Mensajes</label>
              </div>
              <div class="container">
                <draggable
                  class="container"
                  v-if="contexto.mensajes"
                  v-model.trim="contexto.mensajes"
                  @change="orderSteps"
                  @start="drag = true"
                  @end="drag = false"
                  v-bind="dragOptions"
                >
                  <transition-group type="transition" name="flip-list">
                    <div class="row pb-1" v-for="step in contexto.mensajes" :key="step.orden">
                      <div class="draggable col mr-1 step-row text-center"></div>
                      <div class="mensaje col-sm-9 step-row">
                        <b-form-textarea name="step" plaintext rows="2" no-resize v-model.trim="step.desc"> </b-form-textarea>
                      </div>
                      <div class="col-sm-2">
                        <div class="btn-group">
                          <el-button v-on:click="prepareToSave(step)" type="primary" icon="el-icon-edit" circle></el-button>
                          <el-popconfirm
                            v-on:confirm="removeStep(step)"
                            confirmButtonText="Aceptar"
                            cancelButtonText="Cancelar"
                            icon="el-icon-warning"
                            iconColor="red"
                            title="¿Estás seguro de eliminar este mensaje?"
                          >
                            <el-button slot="reference" type="danger" icon="el-icon-delete" circle></el-button>
                          </el-popconfirm>
                        </div>
                      </div>
                    </div>
                  </transition-group>
                </draggable>
                <div>
                  <b-button block v-on:click="prepareToAddStep()" class="btn btn-sm" variant="outline-info">Agregar Mensaje </b-button>
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
            <div v-if="$v.contexto.$invalid">
              <small class="form-text text-danger" v-text="$t('entity.validation.anyerror')"> This field is required. </small>
            </div>
          </div>
        </b-card>
      </form>
    </div>
    <b-modal header-text-variant="light" header-bg-variant="primary" ref="addStep" id="addStep">
      <span slot="modal-title"><span id="kbaseApp.mensajeError.delete.question">Agregar Mensaje</span></span>

      <variables :elements="['CONTEXTO', 'ORGANIZACION', 'USUARIO']"></variables>
      <div class="form-group">
        <label class="form-control-label" for="mensaje-error-desc">Descripción</label>
        <b-form-textarea
          :state="!$v.newStep.desc.$invalid"
          name="desc"
          rows="5"
          class="form-control"
          id="mensaje-error-desc"
          v-model="newStep.desc"
        >
        </b-form-textarea>
        <div v-if="$v.newStep.desc.$invalid">
          <small class="form-text text-danger" v-if="!$v.newStep.desc.required" v-text="$t('entity.validation.required')">
            This field is required.
          </small>
          <small class="form-text text-danger" v-if="!$v.newStep.desc.minLength">
            {{ $t('entity.validation.minlength', { min: '4' }) }}
          </small>
          <small class="form-text text-danger" v-if="!$v.newStep.desc.maxLength">
            {{ $t('entity.validation.maxlength', { max: '500' }) }}
          </small>
        </div>
      </div>
      <div class="form-group">
        <label class="form-control-label" for="mensaje-error-descEn">Descripción en Inglés</label>
        <b-form-textarea
          :state="!$v.newStep.descEn.$invalid"
          name="descEn"
          rows="5"
          class="form-control"
          id="mensaje-error-descEn"
          v-model="newStep.descEn"
        >
        </b-form-textarea>
        <div v-if="$v.newStep.descEn.$invalid">
          <small class="form-text text-danger" v-if="!$v.newStep.descEn.required" v-text="$t('entity.validation.required')">
            This field is required.
          </small>
          <small class="form-text text-danger" v-if="!$v.newStep.descEn.minLength">
            {{ $t('entity.validation.minlength', { min: '4' }) }}
          </small>
          <small class="form-text text-danger" v-if="!$v.newStep.descEn.maxLength">
            {{ $t('entity.validation.maxlength', { max: '500' }) }}
          </small>
        </div>
      </div>
      <div class="form-group">
        <label for="tags-basic">Registrar ayudas</label>
        <b-form-tags
          input-id="tags-basic"
          placeholder="Agregar ayudas"
          v-model="newStep.replies"
          remove-on-delete
          tag-pills
          add-button-text="agregar"
          size="lg"
          tag-variant="primary"
        ></b-form-tags>
      </div>
      <div slot="modal-footer">
        <b-button variant="outline-dark" v-text="$t('entity.action.cancel')" v-on:click="closeAddStepDialog()"></b-button>
        <b-button
          :disabled="$v.newStep.desc.$invalid"
          variant="outline-primary"
          v-text="$t('entity.action.save')"
          v-on:click="addStep()"
        ></b-button>
      </div>
    </b-modal>
    <b-modal header-text-variant="light" header-bg-variant="primary" ref="editStep" id="editStep">
      <span slot="modal-title"
        ><span id="kbaseApp.contexto.mensajes.question">Editar mensaje {{ newStep.paso }}</span></span
      >
      <variables :elements="['CONTEXTO', 'ORGANIZACION', 'USUARIO']"></variables>
      <div class="form-group">
        <label class="form-control-label" for="mensaje-error-desc">Descripción</label>
        <b-form-textarea
          :state="!$v.newStep.desc.$invalid"
          name="desc"
          rows="5"
          class="form-control"
          id="mensaje-error-desc"
          v-model="newStep.desc"
        >
        </b-form-textarea>
        <div v-if="$v.newStep.desc.$invalid">
          <small class="form-text text-danger" v-if="!$v.newStep.desc.required" v-text="$t('entity.validation.required')">
            This field is required.
          </small>
          <small class="form-text text-danger" v-if="!$v.newStep.desc.minLength">
            {{ $t('entity.validation.minlength', { min: '4' }) }}
          </small>
          <small class="form-text text-danger" v-if="!$v.newStep.desc.maxLength">
            {{ $t('entity.validation.maxlength', { max: '500' }) }}
          </small>
        </div>
      </div>
      <div class="form-group">
        <label class="form-control-label" for="mensaje-error-descEn">Descripción en Inglés</label>
        <b-form-textarea
          :state="!$v.newStep.descEn.$invalid"
          name="descEn"
          rows="5"
          class="form-control"
          id="mensaje-error-descEn"
          v-model="newStep.descEn"
        >
        </b-form-textarea>
        <div v-if="$v.newStep.descEn.$invalid">
          <small class="form-text text-danger" v-if="!$v.newStep.descEn.required" v-text="$t('entity.validation.required')">
            This field is required.
          </small>
          <small class="form-text text-danger" v-if="!$v.newStep.descEn.minLength">
            {{ $t('entity.validation.minlength', { min: '4' }) }}
          </small>
          <small class="form-text text-danger" v-if="!$v.newStep.descEn.maxLength">
            {{ $t('entity.validation.maxlength', { max: '500' }) }}
          </small>
        </div>
      </div>
      <div class="form-group" v-if="showReplies">
        <label for="tags-basic">Registrar ayudas</label>
        <b-form-tags
          input-id="tags-basic"
          placeholder="Agregar ayudas"
          v-model="newStep.replies"
          remove-on-delete
          tag-pills
          add-button-text="agregar"
          size="lg"
          tag-variant="primary"
        ></b-form-tags>
      </div>
      <div slot="modal-footer">
        <b-button variant="outline-danger" v-text="$t('entity.action.cancel')" v-on:click="closeEditStepDialog()"></b-button>
        <b-button
          :disabled="$v.newStep.desc.$invalid"
          variant="outline-primary"
          v-text="$t('entity.action.save')"
          v-on:click="saveStep()"
        ></b-button>
      </div>
    </b-modal>
    <b-modal header-text-variant="light" header-bg-variant="primary" ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="kbaseApp.mensajeError.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span
      >
      <div class="modal-body">
        <p id="jhi-delete-mensajeError-heading" v-bind:title="$t('kbaseApp.mensajeError.delete.question')">
          Are you sure you want to delete this Mensaje Error?
        </p>
      </div>
      <div slot="modal-footer">
        <b-button variant="outline-dark" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Danger</b-button>
        <b-button variant="outline-danger" v-text="$t('entity.action.delete')" v-on:click="removeStep()">Danger</b-button>
      </div>
    </b-modal>
  </div>
</template>
<script lang="ts" src="./contexto-update.component.ts">
</script>

<style scoped>
.mensaje:before {
  font-family: FontAwesome;
  content: '\f00a';
  position: absolute;
  top: 11px;
  left: -30px;
  transition: all 300ms ease;
}

.draggable:hover ~ .mensaje {
}
</style>
