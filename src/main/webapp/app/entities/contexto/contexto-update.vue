<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="kbaseApp.contexto.home.createOrEditLabel" v-text="$t('kbaseApp.contexto.home.createOrEditLabel')">Create or edit a Contexto</h2>
                <div>
                    <div class="form-group" v-if="contexto.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="contexto.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kbaseApp.contexto.nombre')" for="contexto-nombre">Nombre</label>
                        <input type="text" class="form-control" name="nombre" id="contexto-nombre"
                            :class="{'valid': !$v.contexto.nombre.$invalid, 'invalid': $v.contexto.nombre.$invalid }" v-model="$v.contexto.nombre.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kbaseApp.contexto.clave')" for="contexto-clave">Clave</label>
                        <input type="text" class="form-control" name="clave" id="contexto-clave"
                            :class="{'valid': !$v.contexto.clave.$invalid, 'invalid': $v.contexto.clave.$invalid }" v-model="$v.contexto.clave.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kbaseApp.contexto.desc')" for="contexto-desc">Desc</label>
                        <input type="text" class="form-control" name="desc" id="contexto-desc"
                            :class="{'valid': !$v.contexto.desc.$invalid, 'invalid': $v.contexto.desc.$invalid }" v-model="$v.contexto.desc.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.contexto.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./contexto-update.component.ts">
</script>
