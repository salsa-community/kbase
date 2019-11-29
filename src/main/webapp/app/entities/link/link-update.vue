<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="kbaseApp.link.home.createOrEditLabel" v-text="$t('kbaseApp.link.home.createOrEditLabel')">Create or edit a Link</h2>
                <div>
                    <div class="form-group" v-if="link.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="link.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('kbaseApp.link.url')" for="link-url">Url</label>
                        <input type="text" class="form-control" name="url" id="link-url"
                            :class="{'valid': !$v.link.url.$invalid, 'invalid': $v.link.url.$invalid }" v-model="$v.link.url.$model"  required/>
                        <div v-if="$v.link.url.$anyDirty && $v.link.url.$invalid">
                            <small class="form-text text-danger" v-if="!$v.link.url.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-bind:value="$t('kbaseApp.link.mensajeError')" for="link-mensajeError">Mensaje Error</label>
                        <select class="form-control" id="link-mensajeError" name="mensajeError" v-model="link.mensajeErrorId">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="mensajeErrorOption.id" v-for="mensajeErrorOption in mensajeErrors" :key="mensajeErrorOption.id">{{mensajeErrorOption.id}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.link.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./link-update.component.ts">
</script>
