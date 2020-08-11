<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('kbaseApp.archivo.home.title')" id="archivo-heading">Archivos</span>
            <router-link :to="{name: 'ArchivoCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-archivo">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('kbaseApp.archivo.home.createLabel')">
                    Create a new Archivo
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
        <div class="alert alert-warning" v-if="!isFetching && archivos && archivos.length === 0">
            <span v-text="$t('kbaseApp.archivo.home.notFound')">No archivos found</span>
        </div>
        <div class="table-responsive" v-if="archivos && archivos.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('nombre')"><span v-text="$t('kbaseApp.archivo.nombre')">Nombre</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('dato')"><span v-text="$t('kbaseApp.archivo.dato')">Dato</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="archivo in archivos"
                    :key="archivo.id">
                    <td>
                        <router-link :to="{name: 'ArchivoView', params: {archivoId: archivo.id}}">{{archivo.id}}</router-link>
                    </td>
                    <td>{{archivo.nombre}}</td>
                    <td>
                        <a v-if="archivo.dato" v-on:click="openFile(archivo.datoContentType, archivo.dato)" v-text="$t('entity.action.open')">open</a>
                        <span v-if="archivo.dato">{{archivo.datoContentType}}, {{byteSize(archivo.dato)}}</span>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'ArchivoView', params: {archivoId: archivo.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'ArchivoEdit', params: {archivoId: archivo.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(archivo)"
                                   variant="danger"
                                   class="btn btn-sm"
                                   v-b-modal.removeEntity>
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
            <span slot="modal-title"><span id="kbaseApp.archivo.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-archivo-heading" v-bind:title="$t('kbaseApp.archivo.delete.question')">Are you sure you want to delete this Archivo?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-archivo" v-text="$t('entity.action.delete')" v-on:click="removeArchivo()">Delete</button>
            </div>
        </b-modal>
        <div v-show="archivos && archivos.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./archivo.component.ts">
</script>
