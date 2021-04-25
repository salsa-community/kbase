<template>
    <div>
        <b-alert :show="dismissCountDown"
            dismissible
            :variant="alertType"
            @dismissed="dismissCountDown=0"
            @dismiss-count-down="countDownChanged">
            {{alertMessage}}
        </b-alert>
        <br/>
        <div class="alert alert-warning" v-if="!isFetching && contactos && contactos.length === 0">
            <span v-text="$t('kbaseApp.contacto.home.notFound')">No contactos found</span>
        </div>
        <div class="table-responsive" v-if="contactos && contactos.length > 0">
            <table class="table">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('context')"><span v-text="$t('kbaseApp.contacto.context')">Context</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('correo')"><span v-text="$t('kbaseApp.contacto.correo')">Correo</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('rfc')"><span v-text="$t('kbaseApp.contacto.rfc')">Rfc</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('estado')"><span v-text="$t('kbaseApp.contacto.estado')">Estado</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('desc')"><span v-text="$t('kbaseApp.contacto.desc')">Desc</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('lastModified')"><span v-text="$t('kbaseApp.contacto.lastModified')">Last Modified</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="contacto in contactos"
                    :key="contacto.id">
                    <td class="text-left">{{contacto.context}}</td>
                    <td class="text-left">{{contacto.correo}}</td>
                    <td class="text-left">{{contacto.rfc}}</td>
                    <td class="text-left">
                        <el-tag effect="plain" size="mini" :type="resolveTagType(contacto.estado)">{{contacto.estado}}</el-tag>
                    </td>
                    <td class="text-left">{{contacto.desc}}</td>
                    <td class="text-left">
                        <i class="el-icon-time"></i>
                        <span style="margin-left: 10px">
                            <em>{{ contacto.lastModified | timeElapsed}}</em>
                        </span>                        
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'ContactoEdit', params: {contactoId: contacto.id}}"  tag="button" class="btn btn-outline-primary btn-sm edit">
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(contacto)"
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
            <span slot="modal-title"><span id="kbaseApp.contacto.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-contacto-heading" v-bind:title="$t('kbaseApp.contacto.delete.question')">Are you sure you want to delete this Contacto?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-contacto" v-text="$t('entity.action.delete')" v-on:click="removeContacto()">Delete</button>
            </div>
        </b-modal>
        <div v-show="contactos && contactos.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./contacto.component.ts">
</script>
