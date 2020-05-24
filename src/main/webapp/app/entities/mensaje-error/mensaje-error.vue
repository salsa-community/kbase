<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('kbaseApp.mensajeError.home.title')" id="mensaje-error-heading">Mensaje Errors</span>
            <router-link :to="{name: 'MensajeErrorCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-mensaje-error">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('kbaseApp.mensajeError.home.createLabel')">
                    Create a new Mensaje Error
                </span>
            </router-link>
        </h2>
        <b-alert :show="dismissCountDown"
            dismissible
            :variant="alertType"
            @dismissed="dismissCountDown=0"
            @dismiss-count-down="countDownChanged">
            {{alertMessage}}
        </b-alert>
        <br/>
        <div class="alert alert-warning" v-if="!isFetching && mensajeErrors && mensajeErrors.length === 0">
            <span v-text="$t('kbaseApp.mensajeError.home.notFound')">No mensajeErrors found</span>
        </div>
        <div class="table-responsive" v-if="mensajeErrors && mensajeErrors.length > 0">
            <table class="table table-hover">
                <thead>
                <tr class="text-center">
                    <th v-on:click="changeOrder('clave')"><span v-text="$t('kbaseApp.mensajeError.clave')">Clave</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th ><span v-text="$t('kbaseApp.mensajeError.contextos')">Contextos</span></th>
                    <th v-on:click="changeOrder('desc')"><span v-text="$t('kbaseApp.mensajeError.desc')">Desc</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th> Acciones </th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="mensajeError in mensajeErrors" :key="mensajeError.id">
                    <td class="text-center">{{mensajeError.clave}}</td>
                    <td>
                        <div class="p-1" v-for="(contexto, index) in mensajeError.contextos" :key="index">
                            <el-tag effect="plain" size="mini" :type='resolveTagType(index)'>{{ contexto }}</el-tag>
                        </div>
                    </td>
                    <td>{{mensajeError.desc}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'MensajeErrorView', params: {mensajeErrorId: mensajeError.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'MensajeErrorEdit', params: {mensajeErrorId: mensajeError.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(mensajeError)"
                                   variant="danger"
                                   class="btn btn-sm">
                                <font-awesome-icon icon="times"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                            </b-button>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <b-modal ref="removeEntity" id="removeEntity" >
            <span slot="modal-title"><span id="kbaseApp.mensajeError.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-mensajeError-heading">{{ $t('kbaseApp.mensajeError.delete.question', {id: currentClave}) }}</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-mensajeError" v-text="$t('entity.action.delete')" v-on:click="removeMensajeError()">Delete</button>
            </div>
        </b-modal>
        <div v-show="mensajeErrors && mensajeErrors.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./mensaje-error.component.ts">
</script>
