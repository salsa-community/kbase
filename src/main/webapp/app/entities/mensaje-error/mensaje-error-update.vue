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
                        <div class="table-responsive">
                            <router-link slot="header" :to="{name: 'MensajeErrorCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-left jh-create-entity create-mensaje-error">
                                <font-awesome-icon icon="plus"></font-awesome-icon>
                                <span >
                                    Crear nuevo Paso
                                </span>
                            </router-link>
                            <table class="table">
                                <draggable v-if="mensajeError.instruccion" v-model="mensajeError.instruccion.pasos" group="people" @start="drag=true" @end="drag=false">
                                    <tr v-for="step in mensajeError.instruccion.pasos" :key="step.paso">
                                        <td>{{step.paso}}</td>
                                        <td>{{step.desc}}</td>
                                        <td class="text-right">
                                            <div class="btn-group">
                                                <router-link :to="{name: 'MensajeErrorEdit', params: {mensajeErrorId: mensajeError.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                                </router-link>
                                                <b-button v-on:click="prepareRemove(mensajeError)"
                                                    variant="danger"
                                                    class="btn btn-sm"
                                                    v-b-modal.removeEntity>
                                                    <font-awesome-icon icon="times"></font-awesome-icon>
                                                </b-button>
                                            </div>
                                        </td>
                                    </tr>
                                </draggable>
                            </table>
                        </div>
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
