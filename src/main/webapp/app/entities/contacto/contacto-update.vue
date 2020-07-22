<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="kbaseApp.contacto.home.createOrEditLabel" v-text="$t('kbaseApp.contacto.home.createOrEditLabel')">Create or edit a Contacto</h2>
                <div>
                    <div class="form-group" v-if="contacto.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="contacto.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kbaseApp.contacto.context')" for="contacto-context">Context</label>
                        <input type="text" class="form-control" name="context" id="contacto-context"
                            :class="{'valid': !$v.contacto.context.$invalid, 'invalid': $v.contacto.context.$invalid }" v-model="$v.contacto.context.$model" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kbaseApp.contacto.correo')" for="contacto-correo" >Correo</label>
                        <input type="text" class="form-control" name="correo" id="contacto-correo"
                            :class="{'valid': !$v.contacto.correo.$invalid, 'invalid': $v.contacto.correo.$invalid }" v-model="$v.contacto.correo.$model" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kbaseApp.contacto.rfc')" for="contacto-rfc">Rfc</label>
                        <input type="text" class="form-control" name="rfc" id="contacto-rfc"
                            :class="{'valid': !$v.contacto.rfc.$invalid, 'invalid': $v.contacto.rfc.$invalid }" v-model="$v.contacto.rfc.$model" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kbaseApp.contacto.desc')" for="contacto-desc">Desc</label>
                        <input type="text" class="form-control" name="desc" id="contacto-desc"
                            :class="{'valid': !$v.contacto.desc.$invalid, 'invalid': $v.contacto.desc.$invalid }" v-model="$v.contacto.desc.$model" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kbaseApp.contacto.lastModified')" for="contacto-lastModified">Last Modified</label>
                        <div class="input-group">
                            <input id="contacto-lastModified" type="date" class="form-control" name="lastModified"  :class="{'valid': !$v.contacto.lastModified.$invalid, 'invalid': $v.contacto.lastModified.$invalid }"
                            v-model="$v.contacto.lastModified.$model"  readonly />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kbaseApp.contacto.estado')" for="contacto-estado">Estado</label>
                        <select class="form-control" name="estado" :class="{'valid': !$v.contacto.estado.$invalid, 'invalid': $v.contacto.estado.$invalid }" v-model="$v.contacto.estado.$model" id="contacto-estado" >
                            <option value="NUEVO" v-bind:label="$t('kbaseApp.EstadoContacto.NUEVO')">NUEVO</option>
                            <option value="PENDIENTE" v-bind:label="$t('kbaseApp.EstadoContacto.PENDIENTE')">PENDIENTE</option>
                            <option value="CONTACTADO" v-bind:label="$t('kbaseApp.EstadoContacto.CONTACTADO')">CONTACTADO</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.contacto.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./contacto-update.component.ts">
</script>
