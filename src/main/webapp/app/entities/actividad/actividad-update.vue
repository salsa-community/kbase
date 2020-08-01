<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="kbaseApp.actividad.home.createOrEditLabel" v-text="$t('kbaseApp.actividad.home.createOrEditLabel')">Create or edit a Actividad</h2>
                <div>
                    <div class="form-group" v-if="actividad.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="actividad.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kbaseApp.actividad.contexto')" for="actividad-contexto">Contexto</label>
                        <input type="text" class="form-control" name="contexto" id="actividad-contexto"
                            :class="{'valid': !$v.actividad.contexto.$invalid, 'invalid': $v.actividad.contexto.$invalid }" v-model="$v.actividad.contexto.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kbaseApp.actividad.valor')" for="actividad-valor">Valor</label>
                        <input type="text" class="form-control" name="valor" id="actividad-valor"
                            :class="{'valid': !$v.actividad.valor.$invalid, 'invalid': $v.actividad.valor.$invalid }" v-model="$v.actividad.valor.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kbaseApp.actividad.desc')" for="actividad-desc">Desc</label>
                        <input type="text" class="form-control" name="desc" id="actividad-desc"
                            :class="{'valid': !$v.actividad.desc.$invalid, 'invalid': $v.actividad.desc.$invalid }" v-model="$v.actividad.desc.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kbaseApp.actividad.fecha')" for="actividad-fecha">Fecha</label>
                        <div class="d-flex">
                            <input id="actividad-fecha" type="datetime-local" class="form-control" name="fecha" :class="{'valid': !$v.actividad.fecha.$invalid, 'invalid': $v.actividad.fecha.$invalid }"
                            
                            :value="convertDateTimeFromServer($v.actividad.fecha.$model)"
                            @change="updateInstantField('fecha', $event)"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-bind:value="$t('kbaseApp.actividad.evento')" for="actividad-evento">Evento</label>
                        <select class="form-control" id="actividad-evento" name="evento" v-model="actividad.eventoId">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="eventoOption.id" v-for="eventoOption in eventos" :key="eventoOption.id">{{eventoOption.id}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.actividad.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./actividad-update.component.ts">
</script>
