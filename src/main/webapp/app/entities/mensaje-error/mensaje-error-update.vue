<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="kbaseApp.mensajeError.home.createOrEditLabel" v-text="$t('kbaseApp.mensajeError.home.createOrEditLabel')">
          Create or edit a MensajeError
        </h2>
        <div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('kbaseApp.mensajeError.tipo')" for="mensaje-error-tipo">Tipo</label>
            <div>
              <el-radio v-model="$v.mensajeError.tipo.$model" label="general" border>General</el-radio>
              <el-radio v-model="$v.mensajeError.tipo.$model" label="error" border>Código de Error</el-radio>
            </div>
            <div v-if="$v.mensajeError.tipo.$invalid">
              <small class="form-text text-danger" v-if="!$v.mensajeError.tipo.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label for="tags-basic">Registrar disparadores <a target="_blank" href="https://regex101.com/">(ver validador)</a></label>
            <b-form-tags
              input-id="tags-basic"
              placeholder="Agregar disparadores..."
              v-model="mensajeError.hears"
              remove-on-delete
              tag-pills
              add-button-text="agregar"
              size="lg"
              tag-variant="primary"
            ></b-form-tags>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('kbaseApp.mensajeError.clave')" for="mensaje-error-clave">Clave</label>
            <b-form-input
              name="clave"
              id="mensaje-error-clave"
              :state="!$v.mensajeError.clave.$invalid"
              :placeholder="$t('kbaseApp.mensajeError.detail.codigoPlaceHolder')"
              v-model.trim="clave"
            ></b-form-input>
            <div v-if="$v.mensajeError.clave.$invalid">
              <small class="form-text text-danger" v-if="!$v.mensajeError.clave.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.mensajeError.clave.minLength">
                {{ $t('entity.validation.minlength', { min: '4' }) }}
              </small>
              <small class="form-text text-danger" v-if="!$v.mensajeError.clave.maxLength">
                {{ $t('entity.validation.maxlength', { max: '50' }) }}
              </small>
              <small class="form-text text-danger" v-if="!$v.mensajeError.clave.isUnique"> el usuario ya existe </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('kbaseApp.mensajeError.desc')" for="mensaje-error-desc">Desc</label>
            <b-form-textarea
              :state="!$v.mensajeError.desc.$invalid"
              name="desc"
              rows="5"
              class="form-control"
              id="mensaje-error-desc"
              :placeholder="$t('kbaseApp.mensajeError.detail.descripcionPlaceHolder')"
              v-model.trim="$v.mensajeError.desc.$model"
            >
            </b-form-textarea>
            <div v-if="$v.mensajeError.desc.$invalid">
              <small class="form-text text-danger" v-if="!$v.mensajeError.desc.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.mensajeError.desc.minLength">
                {{ $t('entity.validation.minlength', { min: '4' }) }}
              </small>
              <small class="form-text text-danger" v-if="!$v.mensajeError.desc.maxLength">
                {{ $t('entity.validation.maxlength', { max: '500' }) }}
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('kbaseApp.mensajeError.descEn')" for="mensaje-error-descEn">Desc</label>
            <b-form-textarea
              :state="!$v.mensajeError.descEn.$invalid"
              name="descEn"
              rows="5"
              class="form-control"
              id="mensaje-error-descEn"
              :placeholder="$t('kbaseApp.mensajeError.detail.descripcionPlaceHolder')"
              v-model.trim="$v.mensajeError.descEn.$model"
            >
            </b-form-textarea>
            <div v-if="$v.mensajeError.descEn.$invalid">
              <small class="form-text text-danger" v-if="!$v.mensajeError.descEn.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.mensajeError.descEn.minLength">
                {{ $t('entity.validation.minlength', { min: '4' }) }}
              </small>
              <small class="form-text text-danger" v-if="!$v.mensajeError.descEn.maxLength">
                {{ $t('entity.validation.maxlength', { max: '500' }) }}
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('kbaseApp.mensajeError.instruccion')" for="mensaje-error-desc">Desc</label>
            <b-form-textarea
              :state="!$v.mensajeError.instruccion.desc.$invalid"
              name="desc"
              rows="5"
              class="form-control"
              id="mensaje-error-desc"
              :placeholder="$t('kbaseApp.mensajeError.detail.descripcionPlaceHolder')"
              v-model.trim="$v.mensajeError.instruccion.desc.$model"
            >
            </b-form-textarea>
            <div v-if="$v.mensajeError.instruccion.desc.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.mensajeError.instruccion.desc.required"
                v-text="$t('entity.validation.required')"
              >
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.mensajeError.instruccion.desc.minLength">
                {{ $t('entity.validation.minlength', { min: '4' }) }}
              </small>
              <small class="form-text text-danger" v-if="!$v.mensajeError.instruccion.desc.maxLength">
                {{ $t('entity.validation.maxlength', { max: '500' }) }}
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('kbaseApp.mensajeError.instruccionEn')" for="mensaje-error-descEn">DescEn</label>
            <b-form-textarea
              :state="!$v.mensajeError.instruccion.descEn.$invalid"
              name="descEn"
              rows="5"
              class="form-control"
              id="mensaje-error-descEn"
              :placeholder="$t('kbaseApp.mensajeError.detail.descripcionPlaceHolder')"
              v-model.trim="$v.mensajeError.instruccion.descEn.$model"
            >
            </b-form-textarea>
            <div v-if="$v.mensajeError.instruccion.descEn.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.mensajeError.instruccion.descEn.required"
                v-text="$t('entity.validation.required')"
              >
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.mensajeError.instruccion.descEn.minLength">
                {{ $t('entity.validation.minlength', { min: '4' }) }}
              </small>
              <small class="form-text text-danger" v-if="!$v.mensajeError.instruccion.descEn.maxLength">
                {{ $t('entity.validation.maxlength', { max: '500' }) }}
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('kbaseApp.mensajeError.contextos')" for="mensaje-error-contextos">Contextos</label>
            <el-checkbox-group v-model="mensajeError.contextos">
              <el-checkbox v-for="(contexto, index) in contextos" :key="index" :label="contexto.clave">{{ contexto.nombre }}</el-checkbox>
            </el-checkbox-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('kbaseApp.mensajeError.pasos')" for="mensaje-error-pasos">Pasos</label>
          </div>
          <div class="container">
            <draggable
              class="container"
              v-if="mensajeError.instruccion"
              v-model.trim="mensajeError.instruccion.pasos"
              @change="orderSteps"
              @start="drag = true"
              @end="drag = false"
              v-bind="dragOptions"
            >
              <transition-group type="transition" name="flip-list">
                <div class="row pb-1" v-for="step in mensajeError.instruccion.pasos" :key="step.paso">
                  <div class="col mr-1 step-row text-center">
                    {{ step.paso }}
                  </div>
                  <div class="col-sm-9 step-row">
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
                        title="¿Estás seguro de eliminar este paso?"
                      >
                        <el-button slot="reference" type="danger" icon="el-icon-delete" circle></el-button>
                      </el-popconfirm>
                    </div>
                  </div>
                </div>
              </transition-group>
            </draggable>
            <div>
              <b-button block v-on:click="prepareToAddStep()" class="btn btn-sm" variant="outline-info">Agregar Paso </b-button>
            </div>
          </div>
        </div>
        <br /><br />
        <div>
          <button type="button" id="cancel-save" class="btn btn-outline-danger" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button type="submit" id="save-entity" :disabled="$v.mensajeError.$invalid || isSaving" class="btn btn-primary">
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
    <b-modal header-text-variant="light" header-bg-variant="primary" ref="addStep" id="addStep">
      <span slot="modal-title"><span id="kbaseApp.mensajeError.delete.question">Agregar Paso</span></span>
      <div class="form-group">
        <label class="form-control-label" for="mensaje-error-desc">Descripción</label>
        <b-form-textarea
          :state="!$v.newStep.desc.$invalid"
          name="desc"
          rows="5"
          class="form-control"
          id="mensaje-error-desc"
          v-model.trim="newStep.desc"
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
          v-model.trim="newStep.descEn"
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
        ><span id="kbaseApp.mensajeError.delete.question">Editar Paso {{ newStep.paso }}</span></span
      >
      <div class="form-group">
        <label class="form-control-label" for="mensaje-error-desc">Descripción</label>
        <b-form-textarea
          :state="!$v.newStep.desc.$invalid"
          name="desc"
          rows="5"
          class="form-control"
          id="mensaje-error-desc"
          v-model.trim="newStep.desc"
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
          v-model.trim="newStep.descEn"
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
<script lang="ts" src="./mensaje-error-update.component.ts">
</script>

<style scoped>
.step-row {
  background-color: #f8f9fa;
}
</style>