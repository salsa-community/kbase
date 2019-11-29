<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('kbaseApp.link.home.title')" id="link-heading">Links</span>
            <router-link :to="{name: 'LinkCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-link">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('kbaseApp.link.home.createLabel')">
                    Create a new Link
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
        <div class="alert alert-warning" v-if="!isFetching && links && links.length === 0">
            <span v-text="$t('kbaseApp.link.home.notFound')">No links found</span>
        </div>
        <div class="table-responsive" v-if="links && links.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('url')"><span v-text="$t('kbaseApp.link.url')">Url</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('mensajeErrorId')"><span v-text="$t('kbaseApp.link.mensajeError')">Mensaje Error</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="link in links"
                    :key="link.id">
                    <td>
                        <router-link :to="{name: 'LinkView', params: {linkId: link.id}}">{{link.id}}</router-link>
                    </td>
                    <td>{{link.url}}</td>
                    <td>
                        <div v-if="link.mensajeErrorId">
                            <router-link :to="{name: 'MensajeErrorView', params: {mensajeErrorId: link.mensajeErrorId}}">{{link.mensajeErrorId}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'LinkView', params: {linkId: link.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'LinkEdit', params: {linkId: link.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(link)"
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
            <span slot="modal-title"><span id="kbaseApp.link.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-link-heading" v-bind:title="$t('kbaseApp.link.delete.question')">Are you sure you want to delete this Link?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-link" v-text="$t('entity.action.delete')" v-on:click="removeLink()">Delete</button>
            </div>
        </b-modal>
        <div v-show="links && links.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./link.component.ts">
</script>
