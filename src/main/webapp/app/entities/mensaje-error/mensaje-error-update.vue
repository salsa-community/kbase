<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="kbaseApp.mensajeError.home.createOrEditLabel" v-text="$t('kbaseApp.mensajeError.home.createOrEditLabel')">Create or edit a MensajeError</h2>
                <div>
                    <div class="form-group" v-if="mensajeError.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="mensajeError.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kbaseApp.mensajeError.clave')" for="mensaje-error-clave">Clave</label>
                        <input type="text" class="form-control" name="clave" id="mensaje-error-clave"
                            :class="{'valid': !$v.mensajeError.clave.$invalid, 'invalid': $v.mensajeError.clave.$invalid }" v-model="$v.mensajeError.clave.$model"  required/>
                        <div v-if="$v.mensajeError.clave.$anyDirty && $v.mensajeError.clave.$invalid">
                            <small class="form-text text-danger" v-if="!$v.mensajeError.clave.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kbaseApp.mensajeError.desc')" for="mensaje-error-desc">Desc</label>
                        <input type="text" class="form-control" name="desc" id="mensaje-error-desc"
                            :class="{'valid': !$v.mensajeError.desc.$invalid, 'invalid': $v.mensajeError.desc.$invalid }" v-model="$v.mensajeError.desc.$model"  required/>
                        <div v-if="$v.mensajeError.desc.$anyDirty && $v.mensajeError.desc.$invalid">
                            <small class="form-text text-danger" v-if="!$v.mensajeError.desc.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kbaseApp.mensajeError.instruccion')" for="mensaje-error-instruccion">Instruccion</label>
                        <select class="form-control" id="mensaje-error-instruccion" name="instruccion" v-model="mensajeError.instruccionId">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="instruccionOption.id" v-for="instruccionOption in instruccions" :key="instruccionOption.id">{{instruccionOption.id}}</option>
                        </select>
                    </div>

                    <draggable v-model="mensajeError.instruccion.pasos">
                        <transition-group>
                        <div v-for="step in mensajeError.instruccion.pasos" :key="step.paso">
                            {{step.desc}}
                        </div>
                        </transition-group>
                </draggable>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.mensajeError.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./mensaje-error-update.component.ts">
</script>
