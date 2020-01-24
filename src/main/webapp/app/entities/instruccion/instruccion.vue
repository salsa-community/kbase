<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('kbaseApp.instruccion.home.title')" id="instruccion-heading">Instruccions</span>
            <router-link :to="{name: 'InstruccionCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-instruccion">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('kbaseApp.instruccion.home.createLabel')">
                    Create a new Instruccion
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
        <div class="alert alert-warning" v-if="!isFetching && instruccions && instruccions.length === 0">
            <span v-text="$t('kbaseApp.instruccion.home.notFound')">No instruccions found</span>
        </div>
        <div class="table-responsive" v-if="instruccions && instruccions.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('desc')"><span v-text="$t('kbaseApp.instruccion.desc')">Desc</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="instruccion in instruccions"
                    :key="instruccion.id">
                    <td>
                        <router-link :to="{name: 'InstruccionView', params: {instruccionId: instruccion.id}}">{{instruccion.id}}</router-link>
                    </td>
                    <td>{{instruccion.desc}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'InstruccionView', params: {instruccionId: instruccion.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'InstruccionEdit', params: {instruccionId: instruccion.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(instruccion)"
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
            <span slot="modal-title"><span id="kbaseApp.instruccion.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-instruccion-heading" v-bind:title="$t('kbaseApp.instruccion.delete.question')">Are you sure you want to delete this Instruccion?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-instruccion" v-text="$t('entity.action.delete')" v-on:click="removeInstruccion()">Delete</button>
            </div>
        </b-modal>
        <div v-show="instruccions && instruccions.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./instruccion.component.ts">
</script>
