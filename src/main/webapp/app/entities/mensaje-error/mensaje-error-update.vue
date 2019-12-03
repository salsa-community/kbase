<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="kbaseApp.mensajeError.home.createOrEditLabel" v-text="$t('kbaseApp.mensajeError.home.createOrEditLabel')">Create or edit a MensajeError</h2>
                <div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kbaseApp.mensajeError.clave')" for="mensaje-error-clave">Clave</label>
                        <b-form-input 
                            name="clave" 
                            id="mensaje-error-clave" 
                            :state="!$v.mensajeError.clave.$invalid" 
                            placeholder="Valid input" v-model="$v.mensajeError.clave.$model"></b-form-input>
                        <div v-if="$v.mensajeError.clave.$anyDirty && $v.mensajeError.clave.$invalid">
                            <small class="form-text text-danger" v-if="!$v.mensajeError.clave.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kbaseApp.mensajeError.desc')" for="mensaje-error-desc">Desc</label>
                        <b-form-textarea 
                            :state="!$v.mensajeError.desc.$invalid"
                            name="desc" 
                            class="form-control" 
                            id="mensaje-error-desc" 
                            v-model="$v.mensajeError.desc.$model">
                        </b-form-textarea>
                        <div v-if="$v.mensajeError.desc.$anyDirty && $v.mensajeError.desc.$invalid">
                            <small class="form-text text-danger" v-if="!$v.mensajeError.desc.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kbaseApp.mensajeError.pasos')" for="mensaje-error-pasos">Pasos</label>
                        <draggable v-if="mensajeError.instruccion" v-model="mensajeError.instruccion.pasos" group="people" @start="drag=true" @end="drag=false">
                            <div class="list-group-item list-group-item-info" v-for="step in mensajeError.instruccion.pasos" :key="step.paso">
                                {{step.desc}}
                            </div>  
                        </draggable>
                    </div>
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
