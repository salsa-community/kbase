<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="kbaseApp.evento.home.createOrEditLabel" v-text="$t('kbaseApp.evento.home.createOrEditLabel')">Create or edit a Evento</h2>
                <div>
                    <div class="form-group" v-if="evento.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="evento.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kbaseApp.evento.nombre')" for="evento-nombre">Nombre</label>
                        <input type="text" class="form-control" name="nombre" id="evento-nombre"
                            :class="{'valid': !$v.evento.nombre.$invalid, 'invalid': $v.evento.nombre.$invalid }" v-model="$v.evento.nombre.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kbaseApp.evento.desc')" for="evento-desc">Desc</label>
                        <input type="text" class="form-control" name="desc" id="evento-desc"
                            :class="{'valid': !$v.evento.desc.$invalid, 'invalid': $v.evento.desc.$invalid }" v-model="$v.evento.desc.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.evento.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./evento-update.component.ts">
</script>
