<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="kbaseApp.archivo.home.createOrEditLabel" v-text="$t('kbaseApp.archivo.home.createOrEditLabel')">Create or edit a Archivo</h2>
                <div>
                    <div class="form-group" v-if="archivo.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="archivo.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kbaseApp.archivo.nombre')" for="archivo-nombre">Nombre</label>
                        <input type="text" class="form-control" name="nombre" id="archivo-nombre"
                            :class="{'valid': !$v.archivo.nombre.$invalid, 'invalid': $v.archivo.nombre.$invalid }" v-model="$v.archivo.nombre.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kbaseApp.archivo.dato')" for="archivo-dato">Dato</label>
                        <div>
                            <div v-if="archivo.dato" class="form-text text-danger clearfix">
                                <a class="pull-left" v-on:click="openFile(archivo.datoContentType, archivo.dato)" v-text="$t('entity.action.open')">open</a><br>
                                <span class="pull-left">{{archivo.datoContentType}}, {{byteSize(archivo.dato)}}</span>
                                <button type="button" v-on:click="archivo.dato=null;archivo.datoContentType=null;"
                                        class="btn btn-secondary btn-xs pull-right">
                                    <font-awesome-icon icon="times"></font-awesome-icon>
                                </button>
                            </div>
                            <input type="file" ref="file_dato" id="file_dato" v-on:change="setFileData($event, archivo, 'dato', false)" v-text="$t('entity.action.addblob')"/>
                        </div>
                        <input type="hidden" class="form-control" name="dato" id="archivo-dato"
                            :class="{'valid': !$v.archivo.dato.$invalid, 'invalid': $v.archivo.dato.$invalid }" v-model="$v.archivo.dato.$model" />
                        <input type="hidden" class="form-control" name="datoContentType" id="archivo-datoContentType"
                            v-model="archivo.datoContentType" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.archivo.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./archivo-update.component.ts">
</script>
