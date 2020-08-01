<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('kbaseApp.evento.home.title')" id="evento-heading">Eventos</span>
            <router-link :to="{name: 'EventoCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-evento">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('kbaseApp.evento.home.createLabel')">
                    Create a new Evento
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
        <div class="alert alert-warning" v-if="!isFetching && eventos && eventos.length === 0">
            <span v-text="$t('kbaseApp.evento.home.notFound')">No eventos found</span>
        </div>
        <div class="table-responsive" v-if="eventos && eventos.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('nombre')"><span v-text="$t('kbaseApp.evento.nombre')">Nombre</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('desc')"><span v-text="$t('kbaseApp.evento.desc')">Desc</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="evento in eventos"
                    :key="evento.id">
                    <td>
                        <router-link :to="{name: 'EventoView', params: {eventoId: evento.id}}">{{evento.id}}</router-link>
                    </td>
                    <td>{{evento.nombre}}</td>
                    <td>{{evento.desc}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'EventoView', params: {eventoId: evento.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'EventoEdit', params: {eventoId: evento.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(evento)"
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
            <span slot="modal-title"><span id="kbaseApp.evento.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-evento-heading" v-bind:title="$t('kbaseApp.evento.delete.question')">Are you sure you want to delete this Evento?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-evento" v-text="$t('entity.action.delete')" v-on:click="removeEvento()">Delete</button>
            </div>
        </b-modal>
        <div v-show="eventos && eventos.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./evento.component.ts">
</script>
