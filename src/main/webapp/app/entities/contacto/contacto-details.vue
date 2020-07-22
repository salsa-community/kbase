<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="contacto">
                <h2 class="jh-entity-heading"><span v-text="$t('kbaseApp.contacto.detail.title')">Contacto</span> {{contacto.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('kbaseApp.contacto.context')">Context</span>
                    </dt>
                    <dd>
                        <span>{{contacto.context}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kbaseApp.contacto.correo')">Correo</span>
                    </dt>
                    <dd>
                        <span>{{contacto.correo}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kbaseApp.contacto.rfc')">Rfc</span>
                    </dt>
                    <dd>
                        <span>{{contacto.rfc}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kbaseApp.contacto.estado')">Estado</span>
                    </dt>
                    <dd>
                        <span v-text="$t('kbaseApp.EstadoContacto.' + contacto.estado)">{{contacto.estado}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kbaseApp.contacto.desc')">Desc</span>
                    </dt>
                    <dd>
                        <span>{{contacto.desc}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('kbaseApp.contacto.lastModified')">Last Modified</span>
                    </dt>
                    <dd>
                        <span>{{contacto.lastModified}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="contacto.id" :to="{name: 'ContactoEdit', params: {contactoId: contacto.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./contacto-details.component.ts">
</script>
