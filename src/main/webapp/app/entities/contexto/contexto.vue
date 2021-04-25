<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('kbaseApp.contexto.home.title')" id="contexto-heading">Contextos</span>
            <router-link :to="{name: 'ContextoCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-contexto">
                <span  v-text="$t('kbaseApp.contexto.home.createLabel')">
                    Create a new Contexto
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
        <div class="alert alert-warning" v-if="!isFetching && contextos && contextos.length === 0">
            <span v-text="$t('kbaseApp.contexto.home.notFound')">No contextos found</span>
        </div>
        <div class="table-responsive" v-if="contextos && contextos.length > 0">
            <table class="table">
                <thead>
                <tr>
                    <th class="text-left"><span v-text="$t('kbaseApp.contexto.nombre')">Nombre</span> </th>
                    <th class="text-left"><span v-text="$t('kbaseApp.contexto.clave')">Clave</span> </th>
                    <th class="text-left"><span v-text="$t('kbaseApp.contexto.desc')">Desc</span> </th>
                    <th>Acciones</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="contexto in contextos"
                    :key="contexto.id">
                    <td class="text-left">{{contexto.nombre}}</td>
                    <td class="text-left">{{contexto.clave}}</td>
                    <td class="text-left">{{contexto.desc}}</td>
                    <td class="text-left">
                        <div class="btn-group">
                            <router-link v-if="false" :to="{name: 'ContextoView', params: {contextoId: contexto.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'ContextoEdit', params: {contextoId: contexto.id}}"  tag="button" class="btn btn-outline-primary btn-sm edit">
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(contexto)"
                                   variant="outline-danger"
                                   class="btn btn-sm"
                                   v-b-modal.removeEntity>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                            </b-button>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <b-modal header-text-variant="light" header-bg-variant="primary" ref="removeEntity" id="removeEntity" >
            <span slot="modal-title"><span id="kbaseApp.contexto.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-contexto-heading" v-bind:title="$t('kbaseApp.contexto.delete.question')">Are you sure you want to delete this Contexto?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-contexto" v-text="$t('entity.action.delete')" v-on:click="removeContexto()">Delete</button>
            </div>
        </b-modal>
        <div v-show="contextos && contextos.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./contexto.component.ts">
</script>
