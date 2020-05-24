<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('kbaseApp.unknowIntents.home.title')" id="unknow-intents-heading">Unknow Intents</span>
            <router-link :to="{name: 'UnknowIntentsCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-unknow-intents">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('kbaseApp.unknowIntents.home.createLabel')">
                    Create a new Unknow Intents
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
        <div class="alert alert-warning" v-if="!isFetching && unknowIntents && unknowIntents.length === 0">
            <span v-text="$t('kbaseApp.unknowIntents.home.notFound')">No unknowIntents found</span>
        </div>
        <div class="table-responsive" v-if="unknowIntents && unknowIntents.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th><span v-text="$t('global.field.id')">ID</span></th>
                    <th><span v-text="$t('kbaseApp.unknowIntents.word')">Word</span></th>
                    <th><span v-text="$t('kbaseApp.unknowIntents.lastModified')">Last Modified</span></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="unknowIntents in unknowIntents"
                    :key="unknowIntents.id">
                    <td>
                        <router-link :to="{name: 'UnknowIntentsView', params: {unknowIntentsId: unknowIntents.id}}">{{unknowIntents.id}}</router-link>
                    </td>
                    <td>{{unknowIntents.word}}</td>
                    <td>{{unknowIntents.lastModified}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link v-if="false" :to="{name: 'UnknowIntentsView', params: {unknowIntentsId: unknowIntents.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link v-if="false" :to="{name: 'UnknowIntentsEdit', params: {unknowIntentsId: unknowIntents.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(unknowIntents)"
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
            <span slot="modal-title"><span id="kbaseApp.unknowIntents.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-unknowIntents-heading" v-bind:title="$t('kbaseApp.unknowIntents.delete.question')">Are you sure you want to delete this Unknow Intents?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-unknowIntents" v-text="$t('entity.action.delete')" v-on:click="removeUnknowIntents()">Delete</button>
            </div>
        </b-modal>
        <div v-show="unknowIntents && unknowIntents.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./unknow-intents.component.ts">
</script>
